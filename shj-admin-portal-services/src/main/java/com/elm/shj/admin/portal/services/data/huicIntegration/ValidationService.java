/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.huicIntegration;

import com.elm.dcc.foundation.commons.core.mapper.CycleAvoidingMappingContext;
import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealthDisease;
import com.elm.shj.admin.portal.orm.repository.ApplicantHealthDiseaseRepository;
import com.elm.shj.admin.portal.services.applicant.*;
import com.elm.shj.admin.portal.services.data.validators.CheckFirst;
import com.elm.shj.admin.portal.services.data.validators.CheckSecond;
import com.elm.shj.admin.portal.services.digitalid.DigitalIdService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Service for validating data received from third party
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ValidationService {
    private final Validator validator;
    private final MessageSource messageSource;
    private final ApplicantService applicantService;
    private final ApplicantContactService applicantContactService;
    private final ApplicantRitualService applicantRitualService;
    private final ApplicantLiteService applicantLiteService;
    private final DigitalIdService digitalIdService;
    private final ApplicantPackageService applicantPackageService;
    private final ApplicantRelativeService applicantRelativeService;
    private final ApplicantHealthService applicantHealthService;
    private final ChatContactService chatContactService;
    private final ApplicantHealthDiseaseRepository applicantHealthDiseaseRepository;
    private final CycleAvoidingMappingContext mappingContext;
    private final ApplicationContext context;

    @Transactional
    public <T> List<ErrorResponse> validateData(List<T> items) {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {

            Set<ConstraintViolation<T>> violations = validator.validate(items.get(i));
            violations.addAll(validator.validate(items.get(i), CheckFirst.class));
            violations.addAll(validator.validate(items.get(i), CheckSecond.class));
            if (!violations.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponse();
                int rowNumber = i + 1;
                errorResponse.setRowNumber(rowNumber);
                violations.forEach(constraintViolation -> {
                    errorResponse.getErrors().add(new ErrorItem(rowNumber, getValidMessage(constraintViolation.getPropertyPath().toString()), getValidMessage(constraintViolation.getMessage()), messageSource.getMessage(constraintViolation.getMessage(), null, Locale.ENGLISH))
                    );
                });
                errorResponses.add(errorResponse);

            } else {
                if (items.get(i).getClass().isAssignableFrom(ApplicantDto.class)) {
                    saveApplicantsMainData((ApplicantDto) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(ApplicantRitualDto.class)) {
                    saveApplicantRitual((ApplicantRitualDto) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(ApplicantHealthDto.class)) {
                    saveApplicantHealth((ApplicantHealthDto) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(ApplicantRelativeDto.class)) {
                    saveApplicantRelative((ApplicantRelativeDto) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(ApplicantHealthDiseaseDto.class)) {
                    saveApplicantHealthDisease((ApplicantHealthDiseaseDto) items.get(i));
                }
            }

        }

        return errorResponses;
    }

    private void saveApplicantHealthDisease(ApplicantHealthDiseaseDto applicantHealthDiseaseDto) {
        ApplicantLiteDto applicantLite = applicantLiteService.findByBasicInfo(applicantHealthDiseaseDto.getApplicantBasicInfo());
        Long applicantId = applicantLite.getId();
        if (applicantLite == null) {
            return;
        }
        Long savedApplicantHealthId = applicantHealthService.findIdByApplicantIdAndPackageReferenceNumber(applicantId, applicantHealthDiseaseDto.getPackageReferenceNumber(), null, false);
        applicantHealthDiseaseDto.setApplicantHealth(ApplicantHealthDto.builder().id(savedApplicantHealthId).build());
        applicantHealthDiseaseRepository.save((JpaApplicantHealthDisease) findMapper(ApplicantHealthDiseaseDto.class).toEntity(applicantHealthDiseaseDto, mappingContext));

    }

    private void saveApplicantRelative(ApplicantRelativeDto applicantRelative) {
        ApplicantLiteDto applicantLite = applicantLiteService.findByBasicInfo(applicantRelative.getApplicantBasicInfo());
        if (applicantLite == null) {
            return;
        }
        Long applicantId = applicantLite.getId();
        String applicantUin = digitalIdService.findApplicantUin(applicantId);
        Long savedApplicantRitualId = applicantRitualService.findAndUpdate(applicantId, applicantRelative.getPackageReferenceNumber(), null, false);
        ApplicantLiteDto relativeApplicantLite = applicantLiteService.findByBasicInfo(ApplicantBasicInfoDto.fromRelative(applicantRelative));
        applicantRelative.setRelativeApplicant(ApplicantDto.fromApplicantLite(relativeApplicantLite));
        applicantRelative.setApplicant(ApplicantDto.fromApplicantLite(applicantLite));
        applicantRelative.setApplicantRitual(ApplicantRitualDto.builder().id(savedApplicantRitualId).build());

        String relativeApplicantUin = (relativeApplicantLite == null || CollectionUtils.isEmpty(relativeApplicantLite.getDigitalIds())) ? null : relativeApplicantLite.getDigitalIds().get(0).getUin();

        applicantRelativeService.save(applicantRelative);
        // if the applicant digital id and the relative applicant digital id and the applicant ritual are created then add chat contacts
        // check if digital ids are created for the applicant and the relative applicant and applicant ritual is already created.
        if (applicantUin == null || relativeApplicantUin == null || savedApplicantRitualId == null) {
            return;
        }
        //TODO: try to get the relative applicant ritual id as it is needed to create the chat contact
        chatContactService.createApplicantRelativesChatContacts(applicantRelative, savedApplicantRitualId);
    }

    private void saveApplicantHealth(ApplicantHealthDto applicantHealth) {
        ApplicantLiteDto applicantLite = applicantLiteService.findByBasicInfo(applicantHealth.getApplicantBasicInfo());
        if (applicantLite == null) {
            return;
        }
        Long applicantId = applicantLite.getId();
        Long applicantRitualId = applicantRitualService.findIdByApplicantIdAndPackageReferenceNumber(applicantId, applicantHealth.getPackageReferenceNumber());
        Long savedApplicantHealthId = applicantHealthService.findIdByApplicantIdAndPackageReferenceNumber(applicantId, applicantHealth.getPackageReferenceNumber(), null, false);
        if (savedApplicantHealthId != null) {
            applicantHealth.setId(savedApplicantHealthId);
            applicantHealth.setUpdateDate(new Date());
        }
        applicantHealth.setApplicant(ApplicantDto.builder().id(applicantId).build());
        applicantHealth.setApplicantRitual(ApplicantRitualDto.builder().id(applicantRitualId).build());
        if (CollectionUtils.isNotEmpty(applicantHealth.getSpecialNeeds())) {
            applicantHealth.setSpecialNeeds(Arrays.stream(applicantHealth.getSpecialNeeds().get(0).getSpecialNeedTypeCode().split(",")).map(sn ->
                    ApplicantHealthSpecialNeedsDto.builder().applicantHealth(applicantHealth).specialNeedTypeCode(sn).build()
            ).collect(Collectors.toList()));
        }

        applicantHealthService.save(applicantHealth);
    }

    private void saveApplicantsMainData(ApplicantDto applicant) {
        if (applicant.getDateOfBirthHijri() == null || applicant.getDateOfBirthHijri() == 0) {
            Calendar cl = Calendar.getInstance();
            cl.setTime(applicant.getDateOfBirthGregorian());
            HijrahDate islamyDate = HijrahChronology.INSTANCE.date(LocalDate.of(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH) + 1, cl.get(Calendar.DATE)));
            applicant.setDateOfBirthHijri(Long.parseLong(islamyDate.toString().substring(islamyDate.toString().indexOf("AH") + 3).replace("-", "")));
        }

        Long existingApplicantId = applicantService.findIdByBasicInfo(ApplicantBasicInfoDto.fromApplicant(applicant));
        // if record exists already in DB we need to update it
        if (existingApplicantId != null) {
            applicant.setId(existingApplicantId);
            applicant.setUpdateDate(new Date());
            //TODO: need refactoring, the below line should be replaced by deleting the old contact as a new one will be added
            applicant.getContacts().addAll(applicantContactService.findByApplicantId(existingApplicantId));
        }
        if (CollectionUtils.isNotEmpty(applicant.getContacts())) {
            applicant.getContacts().forEach(ac -> {
                ac.setApplicant(applicant);
            });
        }

        applicantService.save(applicant);


    }

    private void saveApplicantRitual(ApplicantRitualDto applicantRitualDto) {
        ApplicantLiteDto applicantLite = applicantLiteService.findByBasicInfo(applicantRitualDto.getApplicantBasicInfo());
        if (applicantLite == null) {
            return;
        }
        Long applicantId = applicantLite.getId();
        String packageReferenceNumber = applicantRitualDto.getPackageReferenceNumber();
        Long savedApplicantRitualId = applicantRitualService.findAndUpdate(applicantId, packageReferenceNumber, null, false);
        String applicantUin = digitalIdService.findApplicantUin(applicantId);
        if (savedApplicantRitualId != null) {
            applicantRitualDto.setApplicant(ApplicantDto.builder().id(applicantId).build());
            applicantRitualDto.setId(savedApplicantRitualId);
            applicantRitualDto.setUpdateDate(new Date());
            Long applicantPackageId = applicantPackageService.findLatestIdByApplicantUIN(applicantUin);
            applicantRitualDto.setApplicantPackage(ApplicantPackageDto.builder().id(applicantPackageId).build());
            //set applicant ritual id for applicant contacts, applicant health (if exist) and applicant relatives (if exist)
            applicantHealthService.updateApplicantHealthApplicantRitual(applicantRitualDto.getId(), applicantId, applicantRitualDto.getPackageReferenceNumber());
            applicantRelativeService.updateApplicantRelativeApplicantRitual(applicantRitualDto.getId(), applicantId, applicantRitualDto.getPackageReferenceNumber());
        } else {
            // applicant ritual not created yet, check if digital id is exists,
            // if yes then create a new applicant package and link it with the applicant ritual
            if (applicantUin != null && !applicantUin.isEmpty()) {
                ApplicantPackageDto createdApplicantPackage = applicantPackageService.createApplicantPackage(applicantRitualDto.getPackageReferenceNumber(), Long.parseLong(applicantUin), null, null);
                applicantRitualDto.setApplicantPackage(createdApplicantPackage);
            }
        }
        applicantRitualService.save(applicantRitualDto);

    }

    /**
     * remove unnecessary text from error message
     *
     * @param message
     * @return
     */
    private String getValidMessage(String message) {
        return message.substring(message.lastIndexOf(".") + 1);
    }

    private IGenericMapper findMapper(Class clazz) {
        List<IGenericMapper> foundMappers = this.context.getBeansOfType(IGenericMapper.class).values().stream().filter(mapper -> Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(mapper.getClass(), IGenericMapper.class))[0].getSimpleName().equals(clazz.getSimpleName())).collect(Collectors.toList());
        return CollectionUtils.size(foundMappers) == 1 ? foundMappers.get(0) : null;
    }

}

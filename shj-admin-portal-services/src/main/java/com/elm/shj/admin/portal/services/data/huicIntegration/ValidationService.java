/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.huicIntegration;

import com.elm.dcc.foundation.commons.core.mapper.CycleAvoidingMappingContext;
import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.*;
import com.elm.shj.admin.portal.orm.repository.*;
import com.elm.shj.admin.portal.services.applicant.*;
import com.elm.shj.admin.portal.services.card.CompanyStaffCardService;
import com.elm.shj.admin.portal.services.company.CompanyRitualSeasonService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.data.validators.CheckFirst;
import com.elm.shj.admin.portal.services.data.validators.CheckSecond;
import com.elm.shj.admin.portal.services.digitalid.CompanyStaffDigitalIdService;
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
    private final ApplicantHealthImmunizationRepository applicantHealthImmunizationRepository;
    private final ApplicantHealthRepository applicantHealthRepository;
    private final CompanyStaffService companyStaffService;
    private final CompanyStaffRepository companyStaffRepository;
    private final CompanyStaffDigitalIdService companyStaffDigitalIdService;
    private final CompanyStaffCardService companyStaffCardService;
    private final CompanyRitualSeasonService companyRitualSeasonService;
    private final RitualSeasonRepository ritualSeasonRepository;
    private final PackageHousingRepository packageHousingRepository;
    private final CompanyRepository companyRepository;
    private final CompanyRitualSeasonRepository companyRitualSeasonRepository;

    @Transactional
    public <T> List<ErrorResponse> validateData(List<T> items) {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Set<ConstraintViolation<T>> violations = validator.validate(items.get(i));
            violations.addAll(validator.validate(items.get(i), CheckFirst.class));
            if (!items.get(i).getClass().isAssignableFrom(ApplicantHealthDto.class)) {
                violations.addAll(validator.validate(items.get(i), CheckSecond.class));
            }
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
                    saveApplicantsMainData((ApplicantDto) items.get(i), errorResponses, i);
                }
                if (items.get(i).getClass().isAssignableFrom(ApplicantRitualDto.class)) {
                    saveApplicantRitual((ApplicantRitualDto) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(ApplicantHealthDto.class)) {
                    saveApplicantHealth((ApplicantHealthDto) items.get(i), errorResponses, i);
                }
                if (items.get(i).getClass().isAssignableFrom(ApplicantRelativeDto.class)) {
                    saveApplicantRelative((ApplicantRelativeDto) items.get(i), errorResponses, i);
                }
                if (items.get(i).getClass().isAssignableFrom(ApplicantHealthDiseaseDto.class)) {
                    saveApplicantHealthDisease((ApplicantHealthDiseaseDto) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(ApplicantHealthImmunizationDto.class)) {
                    saveApplicantHealthImmunization((ApplicantHealthImmunizationDto) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(CompanyStaffDto.class)) {
                    saveCompanyStaffMainData((CompanyStaffDto) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(CompanyStaffRitualDto.class)) {
                    saveCompanyStaffRitualData((CompanyStaffRitualDto) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(RitualSeasonDto.class)) {
                    saveRitualSeasons((RitualSeasonDto) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(PackageHousingDto.class)) {
                    savePackageHousings((PackageHousingDto) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(CompanyDto.class)) {
                    saveCompanies((CompanyDto) items.get(i));
                }
            }

        }


        return errorResponses;
    }

    private void saveCompanies(CompanyDto companyDto) {
        companyDto.setCode(companyDto.getCode() + "_" + companyDto.getTypeCode());
        JpaCompany savedCompany = companyRepository.save((JpaCompany) findMapper(CompanyDto.class).toEntity(companyDto, mappingContext));
        Optional<JpaRitualSeason> ritualSeason = ritualSeasonRepository.findByRitualTypeCodeAndSeasonYear(companyDto.getRitualTypeCode(), companyDto.getSeason());
        if (ritualSeason.isPresent()) {
            JpaCompanyRitualSeason companyRitualSeason = new JpaCompanyRitualSeason();
            companyRitualSeason.setCompany(savedCompany);
            companyRitualSeason.setRitualSeason(ritualSeason.get());
            companyRitualSeason.setActive(true);
            companyRitualSeason.setSeasonStart(ritualSeason.get().getSeasonStart());
            companyRitualSeason.setSeasonEnd(ritualSeason.get().getSeasonEnd());
            companyRitualSeasonRepository.save(companyRitualSeason);

        }

    }

    private void savePackageHousings(PackageHousingDto packageHousingDto) {
        packageHousingDto.setLat(packageHousingDto.getGeoLocation().getLat());
        packageHousingDto.setLng(packageHousingDto.getGeoLocation().getLng());
        packageHousingRepository.save((JpaPackageHousing) findMapper(PackageHousingDto.class).toEntity(packageHousingDto, mappingContext));


    }

    private void saveRitualSeasons(RitualSeasonDto ritualSeasonDto) {
        ritualSeasonDto.setActivated(true);
        ritualSeasonRepository.save((JpaRitualSeason) findMapper(RitualSeasonDto.class).toEntity(ritualSeasonDto, mappingContext));

    }

    private void saveCompanyStaffRitualData(CompanyStaffRitualDto companyStaffRitualDto) {
        saveStaffRitual(companyStaffRitualDto);
    }

    private void saveCompanyStaffMainData(CompanyStaffDto staff) {
        updateDate(staff.getDateOfBirthGregorian());
        CompanyStaffDto existingStaff = companyStaffService.findByBasicInfo(staff.getIdNumber(), staff.getPassportNumber(), staff.getDateOfBirthGregorian(), staff.getDateOfBirthHijri());
        if (existingStaff != null) {
            updateExistingStaff(staff, existingStaff);
        }
        companyStaffRepository.save((JpaCompanyStaff) findMapper(CompanyStaffDto.class).toEntity(staff, mappingContext));
    }


    private void saveApplicantHealthImmunization(ApplicantHealthImmunizationDto applicantHealthImmunization) {
        ApplicantLiteDto applicantLite = applicantLiteService.findByBasicInfo(applicantHealthImmunization.getApplicantBasicInfo());
        Long applicantId = applicantLite.getId();
        if (applicantLite == null) {
            return;
        }
        Long savedApplicantHealthId = applicantHealthService.findIdByApplicantIdAndPackageReferenceNumber(applicantId, applicantHealthImmunization.getPackageReferenceNumber(), null, false);
        if (savedApplicantHealthId != null) {
            applicantHealthImmunization.setApplicantHealth(ApplicantHealthDto.builder().id(savedApplicantHealthId).build());
            Optional<JpaApplicantHealthImmunization> existByBasicInfoAndImmunizationCode = applicantHealthImmunizationRepository.findByApplicantHealthApplicantIdAndImmunizationCodeAndApplicantHealthPackageReferenceNumber(applicantId, applicantHealthImmunization.getImmunizationCode(), applicantHealthImmunization.getPackageReferenceNumber());
            if (existByBasicInfoAndImmunizationCode.isPresent()) {
                applicantHealthImmunization.setId(existByBasicInfoAndImmunizationCode.get().getId());
            }
            applicantHealthImmunizationRepository.save((JpaApplicantHealthImmunization) findMapper(ApplicantHealthImmunizationDto.class).toEntity(applicantHealthImmunization, mappingContext));
        }
    }

    private void saveApplicantHealthDisease(ApplicantHealthDiseaseDto applicantHealthDiseaseDto) {
        applicantHealthDiseaseDto.getApplicantBasicInfo().setDateOfBirthGregorian(updateDate(applicantHealthDiseaseDto.getApplicantBasicInfo().getDateOfBirthGregorian()));
        ApplicantLiteDto applicantLite = applicantLiteService.findByBasicInfo(applicantHealthDiseaseDto.getApplicantBasicInfo());
        if (applicantLite == null) {
            return;
        }
        Long applicantId = applicantLite.getId();
        Long savedApplicantHealthId = applicantHealthService.findIdByApplicantIdAndPackageReferenceNumber(applicantId, applicantHealthDiseaseDto.getPackageReferenceNumber(), null, false);
        if (savedApplicantHealthId == null) {
            return;
        }
        applicantHealthDiseaseDto.setApplicantHealth(ApplicantHealthDto.builder().id(savedApplicantHealthId).build());
        applicantHealthDiseaseRepository.save((JpaApplicantHealthDisease) findMapper(ApplicantHealthDiseaseDto.class).toEntity(applicantHealthDiseaseDto, mappingContext));

    }

    private void saveApplicantRelative(ApplicantRelativeDto applicantRelative, List<ErrorResponse> errorResponses, int rowNumber) {
        applicantRelative.getApplicantBasicInfo().setDateOfBirthGregorian(updateDate(applicantRelative.getApplicantBasicInfo().getDateOfBirthGregorian()));
        ApplicantLiteDto applicantLite = applicantLiteService.findByBasicInfo(applicantRelative.getApplicantBasicInfo());
        if (applicantLite == null) {
            return;
        }
        Long applicantId = applicantLite.getId();
        String applicantUin = digitalIdService.findApplicantUin(applicantId);
        Long savedApplicantRitualId = applicantRitualService.findAndUpdate(applicantId, applicantRelative.getPackageReferenceNumber(), null, false);
        if (savedApplicantRitualId == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setRowNumber(rowNumber + 1);
            errorResponse.getErrors().add(new ErrorItem(rowNumber + 1, "", "20001", "No Applicant ritual found"));
            errorResponses.add(errorResponse);
            return;
        }
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

    private void saveApplicantHealth(ApplicantHealthDto applicantHealth, List<ErrorResponse> errorResponses, int rowNumber) {
        applicantHealth.getApplicantBasicInfo().setDateOfBirthGregorian(updateDate(applicantHealth.getApplicantBasicInfo().getDateOfBirthGregorian()));
        ApplicantLiteDto applicantLite = applicantLiteService.findByBasicInfo(applicantHealth.getApplicantBasicInfo());
        if (applicantLite == null) {
            return;
        }
        Long applicantId = applicantLite.getId();
        Long applicantRitualId = applicantRitualService.findIdByApplicantIdAndPackageReferenceNumber(applicantId, applicantHealth.getPackageReferenceNumber());
        Long savedApplicantHealthId = applicantHealthService.findIdByApplicantIdAndPackageReferenceNumber(applicantId, applicantHealth.getPackageReferenceNumber(), null, false);
        updateApplicantHealth(applicantHealth, savedApplicantHealthId, errorResponses, rowNumber);
        if (!errorResponses.isEmpty()) {
            return;
        }
        applicantHealth.setApplicant(ApplicantDto.builder().id(applicantId).build());
        applicantHealth.setApplicantRitual(ApplicantRitualDto.builder().id(applicantRitualId).build());
        applicantHealthRepository.save((JpaApplicantHealth) findMapper(ApplicantHealthDto.class).toEntity(applicantHealth, mappingContext));

    }

    private void saveApplicantsMainData(ApplicantDto applicant, List<ErrorResponse> errorResponses, int rowNumber) {
        updateApplicantBirthDate(applicant);
        applicant.setDateOfBirthGregorian(updateDate(applicant.getDateOfBirthGregorian()));
        Long existingApplicantId = applicantService.findIdByBasicInfo(ApplicantBasicInfoDto.fromApplicant(applicant));
        if (applicant.getStatus() == null) {
            applicant.setStatus(1);
        }
        // if record exists already in DB we need to update it
        if (existingApplicantId != null) {
            if (applicant.getStatus() == 2) {
                updateExistingApplicant(applicant, existingApplicantId);
            } else if (applicant.getStatus() == 3) {
                applicant.setDeleted(true);
                applicantService.save(applicant);
                return;
            } else if (applicant.getStatus() == 1) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setRowNumber(rowNumber + 1);
                errorResponse.getErrors().add(new ErrorItem(rowNumber + 1, "", "30001", "Please enter a valid value. Same value cannot be repeated within the previous registered records"));
                errorResponses.add(errorResponse);
                return;
            }
        } else {
            if (applicant.getStatus() == 2 || applicant.getStatus() == 3) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setRowNumber(rowNumber + 1);
                errorResponse.getErrors().add(new ErrorItem(rowNumber + 1, "", "30002", "No main record found for the input data to update the related sub record"));
                errorResponses.add(errorResponse);
                return;
            }
        }

        //TODO: unify this for both data upload and huic
        addApplicantToContact(applicant);

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
        updateApplicantRitual(applicantRitualDto, savedApplicantRitualId, applicantId, applicantUin);
        applicantRitualDto.setApplicant(ApplicantDto.builder().id(applicantId).build());
        applicantRitualService.save(applicantRitualDto);

    }

    /**
     * remove hours from date
     *
     * @param date
     * @return
     */
    Date updateDate(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            return calendar.getTime();
        }
        return null;
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

    /**
     * Finds a mapper for a given dto class
     *
     * @param clazz the dto class to find mapper for
     * @return the found mapper
     */
    public IGenericMapper findMapper(Class clazz) {
        List<IGenericMapper> foundMappers = this.context.getBeansOfType(IGenericMapper.class).values().stream().filter(mapper -> Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(mapper.getClass(), IGenericMapper.class))[0].getSimpleName().equals(clazz.getSimpleName())).collect(Collectors.toList());
        return CollectionUtils.size(foundMappers) == 1 ? foundMappers.get(0) : null;
    }

    /**
     * update date of birth hijri in case of null
     *
     * @param applicant
     */
    public void updateApplicantBirthDate(ApplicantDto applicant) {
        if (applicant.getDateOfBirthHijri() == null || applicant.getDateOfBirthHijri() == 0) {
            Calendar cl = Calendar.getInstance();
            cl.setTime(applicant.getDateOfBirthGregorian());
            HijrahDate islamyDate = HijrahChronology.INSTANCE.date(LocalDate.of(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH) + 1, cl.get(Calendar.DATE)));
            applicant.setDateOfBirthHijri(Long.parseLong(islamyDate.toString().substring(islamyDate.toString().indexOf("AH") + 3).replace("-", "")));
        }
    }

    public void updateExistingApplicant(ApplicantDto applicant, long existingApplicantId) {
        applicant.setId(existingApplicantId);
        applicant.setUpdateDate(new Date());
    }

    public void updateApplicantRitual(ApplicantRitualDto applicantRitualDto, Long savedApplicantRitualId, long applicantId, String applicantUin) {
        if (savedApplicantRitualId != null) {
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
    }

    public void addApplicantToContact(ApplicantDto applicant) {
        if (CollectionUtils.isNotEmpty(applicant.getContacts())) {
            applicant.getContacts().forEach(ac -> {
                ac.setApplicant(applicant);
            });
        }
    }

    public void updateApplicantHealth(ApplicantHealthDto applicantHealth, Long savedApplicantHealthId, List<ErrorResponse> errorResponses, int rowNumber) {
        if (savedApplicantHealthId != null) {
            log.debug("Update existing applicant health in applicant health segment for {} applicant id and {} package reference number.");
            applicantHealth.setId(savedApplicantHealthId);
            applicantHealth.setUpdateDate(new Date());
        }
        if (!applicantHealth.getHasSpecialNeeds()) {
            applicantHealth.setSpecialNeeds(null);
            return;
        }
        if (CollectionUtils.isNotEmpty(applicantHealth.getSpecialNeeds())) {
            if (applicantHealth.getSpecialNeeds().get(0).getSpecialNeedTypeCode() != null) {
                applicantHealth.setSpecialNeeds(Arrays.stream(applicantHealth.getSpecialNeeds().get(0).getSpecialNeedTypeCode().split(",")).map(sn ->
                        ApplicantHealthSpecialNeedsDto.builder().applicantHealth(applicantHealth).specialNeedTypeCode(sn).build()
                ).collect(Collectors.toList()));
            } else {
                // applicantHealth.setSpecialNeeds(null);
                if (errorResponses != null) {
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setRowNumber(rowNumber + 1);
                    errorResponse.getErrors().add(new ErrorItem(rowNumber + 1, "specialNeedList", "20001", "Please fill a value. This field cannot be empty"));
                    errorResponses.add(errorResponse);
                    return;
                }

            }
        } else {
            if (errorResponses != null) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setRowNumber(rowNumber + 1);
                errorResponse.getErrors().add(new ErrorItem(rowNumber + 1, "specialNeedList", "20001", "Please fill a value. This field cannot be empty"));
                errorResponses.add(errorResponse);
                return;
            }
        }
    }

    public void updateExistingStaff(CompanyStaffDto staff, CompanyStaffDto existingStaff) {
        staff.setId(existingStaff.getId());
        staff.setDigitalIds(existingStaff.getDigitalIds());
        staff.setDataRequestRecordId(existingStaff.getDataRequestRecordId());
        staff.setApplicantGroups(existingStaff.getApplicantGroups());
    }

    public void saveStaffRitual(CompanyStaffRitualDto companyStaffRitual) {
        CompanyStaffDto existingStaff = companyStaffService.findByBasicInfo(companyStaffRitual.getIdNumber(), companyStaffRitual.getPassportNumber(), companyStaffRitual.getDateOfBirthGregorian(), companyStaffRitual.getDateOfBirthHijri());
        CompanyStaffDigitalIdDto companyStaffDigitalId = companyStaffDigitalIdService.findByBasicInfo(existingStaff.getId(), companyStaffRitual.getSeason());
        CompanyRitualSeasonDto companyRitualSeasonDto = companyRitualSeasonService.getLatestCompanyRitualSeasonByRitualSeason(companyStaffRitual.getCompanyCode(), companyStaffRitual.getTypeCode(), companyStaffRitual.getSeason());
        //existingStaff.setCompanyRitualSeason(companyRitualSeasonDto);
        if (companyStaffDigitalId != null) {
            // if he has a digital id for that same season
            List<CompanyStaffCardDto> companyStaffCardDtos = companyStaffCardService.findByDigitalId(companyStaffDigitalId.getSuin());
            // if no cards for digitalId and SEASON
            if (companyStaffCardDtos.isEmpty()) {
                CompanyStaffCardDto companyStaffCardDto = new CompanyStaffCardDto();
                companyStaffCardDto.setCompanyStaffDigitalId(companyStaffDigitalId);
                companyStaffCardDto.setStatusCode(ECardStatus.READY_TO_PRINT.name());
                companyStaffCardDto.setCompanyRitualSeason(companyRitualSeasonDto);
                companyStaffCardService.save(companyStaffCardDto);
                return;

            }

            //find staff cards for different company or different ritual
            List<CompanyStaffCardDto> companyStaffCards2 = companyStaffCardService.findByDigitalIdAndDifferentCompanyOrRitual(companyStaffDigitalId.getSuin(), companyStaffRitual.getCompanyCode(), companyStaffRitual.getTypeCode());
            if (CollectionUtils.isNotEmpty(companyStaffCards2)) {
                companyStaffCards2.forEach(c -> {
                    c.setStatusCode(ECardStatus.EXPIRED.name());
                });
                companyStaffCardService.saveAll(companyStaffCards2);
                return;
            }

            // find staff cards for same company and same ritual
            List<CompanyStaffCardDto> companyStaffCards = companyStaffCardService.findByDigitalIdCompanyCodeRitualType(companyStaffDigitalId.getSuin(), companyStaffRitual.getCompanyCode(), companyStaffRitual.getTypeCode());
            if (companyStaffCards.isEmpty()) {
                CompanyStaffCardDto companyStaffCardDto = new CompanyStaffCardDto();
                companyStaffCardDto.setCompanyStaffDigitalId(companyStaffDigitalId);
                companyStaffCardDto.setStatusCode(ECardStatus.READY_TO_PRINT.name());
                companyStaffCardDto.setCompanyRitualSeason(companyRitualSeasonDto);
                companyStaffCardService.save(companyStaffCardDto);
                return;
            }


        } else {
            // create new digital id for that staff in case he has no digital id for that same season
            CompanyStaffDigitalIdDto staffDigitalId = new CompanyStaffDigitalIdDto();
            staffDigitalId.setCompanyStaff(existingStaff);
            staffDigitalId.setSeasonYear(companyStaffRitual.getSeason());
            staffDigitalId.setSuin(companyStaffDigitalIdService.generate(existingStaff, companyStaffRitual.getSeason()));
            staffDigitalId.setStatusCode(EStaffDigitalIdStatus.VALID.name());
            CompanyStaffDigitalIdDto savedDigitalId = companyStaffDigitalIdService.save(staffDigitalId);
            CompanyStaffCardDto companyStaffCardDto = new CompanyStaffCardDto();
            companyStaffCardDto.setCompanyStaffDigitalId(savedDigitalId);
            companyStaffCardDto.setStatusCode(ECardStatus.READY_TO_PRINT.name());
            companyStaffCardDto.setCompanyRitualSeason(companyRitualSeasonDto);
            companyStaffCardService.save(companyStaffCardDto);

        }
    }
}

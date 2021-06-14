/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.writer;

import com.elm.dcc.foundation.commons.core.mapper.CycleAvoidingMappingContext;
import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.repository.*;
import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.digitalid.DigitalIdService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Generic Item writer to save read items based on their data segment
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ItemWriter {

    @SuppressWarnings("rawtypes")
    private final Map<EDataSegment, Class> repositoryRegistry = new HashMap<>();
    @SuppressWarnings("rawtypes")
    private final Map<EDataSegment, IGenericMapper> mapperRegistry = new HashMap<>();

    private final CycleAvoidingMappingContext mappingContext;
    private final ApplicationContext context;
    private final ApplicantService applicantService;
    private final DigitalIdService digitalIdService;
    private final DataRequestRecordRepository dataRequestRecordRepository;

    /**
     * Populates the registry
     */
    @PostConstruct
    private void init() {
        // repository registry initialization
        repositoryRegistry.put(EDataSegment.APPLICANT_DATA, ApplicantRepository.class);
        repositoryRegistry.put(EDataSegment.APPLICANT_RELATIVES_DATA, ApplicantRelativeRepository.class);
        repositoryRegistry.put(EDataSegment.APPLICANT_HEALTH_DATA, ApplicantHealthRepository.class);
        repositoryRegistry.put(EDataSegment.APPLICANT_IMMUNIZATION_DATA, ApplicantHealthImmunizationRepository.class);
        repositoryRegistry.put(EDataSegment.APPLICANT_DISEASE_DATA, ApplicantHealthDiseaseRepository.class);
        repositoryRegistry.put(EDataSegment.APPLICANT_RITUAL_DATA, ApplicantRitualRepository.class);

        // mapper registry initialization
        mapperRegistry.put(EDataSegment.APPLICANT_DATA, Objects.requireNonNull(findMapper(ApplicantDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_RELATIVES_DATA, Objects.requireNonNull(findMapper(ApplicantRelativeDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_HEALTH_DATA, Objects.requireNonNull(findMapper(ApplicantHealthDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_IMMUNIZATION_DATA, Objects.requireNonNull(findMapper(ApplicantHealthImmunizationDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_DISEASE_DATA, Objects.requireNonNull(findMapper(ApplicantHealthDiseaseDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_RITUAL_DATA, Objects.requireNonNull(findMapper(ApplicantRitualDto.class)));
    }

    /**
     * Saves items in the database based on their data segment
     *
     * @param items         the items to be saved
     * @param dataSegment   the data segment related to the items
     * @param dataRequestId the data request id
     * @param <T>           the items class type
     */
    @Transactional
    @SuppressWarnings({"rawtypes", "unchecked"})
    public <T, S> void write(List<AbstractMap.SimpleEntry<Row, T>> items, DataSegmentDto dataSegment, long dataRequestId) {
        // update applicant related attributes
        items.forEach(entry -> updateNestedApplicantInfo(entry.getValue(), items.stream().map(AbstractMap.SimpleEntry::getValue).collect(Collectors.toList())));
        // save all items and build data records
        List<DataRequestRecordDto> dataRequestRecords = new ArrayList<>();
        List<S> savedItems = new ArrayList<>();
        JpaRepository repository = (JpaRepository) context.getBean(repositoryRegistry.get(EDataSegment.fromId(dataSegment.getId())));
        items.forEach(entry -> {
            S savedItem = (S) repository.save(mapperRegistry.get(EDataSegment.fromId(dataSegment.getId())).toEntity(entry.getValue(), mappingContext));
            savedItems.add(savedItem);
            try {
                dataRequestRecords.add(DataRequestRecordDto.builder()
                        .createDataRequestId(dataRequestId)
                        .lastUpdateDataRequestId(dataRequestId)
                        .createDataRequestRowNum((long) entry.getKey().getRowNum())
                        .lastUpdateDataRequestRowNum((long) entry.getKey().getRowNum())
                        .itemId(Long.parseLong(BeanUtils.getProperty(savedItem, "id")))
                        .build());
            } catch (Exception e) {
                ReflectionUtils.handleReflectionException(e);
            }
        });
        // save all records
        List savedRecords = dataRequestRecordRepository.saveAll(Objects.requireNonNull(findMapper(DataRequestRecordDto.class)).toEntityList(dataRequestRecords, mappingContext));
        // update all items with record ids
        savedItems.forEach(s -> {
            savedRecords.stream().filter(r -> {
                try {
                    return StringUtils.equals(BeanUtils.getProperty(r, "itemId"), BeanUtils.getProperty(s, "id"));
                } catch (Exception e) {
                    ReflectionUtils.handleReflectionException(e);
                    return false;
                }
            }).forEach(r -> {
                try {
                    BeanUtils.setProperty(s, "dataRequestRecord", r);
                } catch (Exception e) {
                    ReflectionUtils.handleReflectionException(e);
                }
            });
        });
        // update saved items
        repository.saveAll(savedItems);
    }

    /**
     * Updates related applicant properties
     *
     * @param item the item to update
     */
    @SuppressWarnings("unchecked")
    private <T> void updateNestedApplicantInfo(T item, List<T> bulkList) {
        // Special treatment for ApplicantDto and contact info as they come in the same sheet
        if (item != null && item.getClass().isAssignableFrom(ApplicantDto.class)) {
            ApplicantDto applicant = (ApplicantDto) item;
            ApplicantDto existingApplicant = applicantService.findByBasicInfo(ApplicantBasicInfoDto.fromApplicant(applicant));
            // if record exists already in DB we need to update it
            if (existingApplicant != null) {
                applicant.setId(existingApplicant.getId());
            }
            if (CollectionUtils.isNotEmpty(applicant.getContacts())) {
                applicant.getContacts().forEach(sn -> sn.setApplicant(applicant));
            }
            // digital id will bw generated automatically by the scheduler
        }
        // Special treatment for ApplicantHealthDto and special needs as they come in the same sheet
        if (item != null && item.getClass().isAssignableFrom(ApplicantHealthDto.class)) {
            ApplicantHealthDto applicantHealth = (ApplicantHealthDto) item;
            if (CollectionUtils.isNotEmpty(applicantHealth.getSpecialNeeds())) {
                // get the special needs and if it is a list then create a list of special needs dtos
                applicantHealth.setSpecialNeeds(Arrays.stream(applicantHealth.getSpecialNeeds().get(0).getSpecialNeedTypeCode().split(",")).map(sn ->
                        ApplicantHealthSpecialNeedsDto.builder().applicantHealth(applicantHealth).specialNeedTypeCode(sn).build()
                ).collect(Collectors.toList()));
            }
        }
        // special treatment for applicant relative
        if (item != null && item.getClass().isAssignableFrom(ApplicantRelativeDto.class)) {
            ApplicantRelativeDto applicantRelative = (ApplicantRelativeDto) item;
            applicantRelative.setRelativeApplicant(applicantService.findByBasicInfo(ApplicantBasicInfoDto.fromRelative(applicantRelative)));
        }
        // Special treatment for ApplicantRitualDto and special needs as they come in the same sheet
        if (item != null && item.getClass().isAssignableFrom(ApplicantRitualDto.class)) {
            ApplicantRitualDto applicantRitual = (ApplicantRitualDto) item;
            long ritualDateStartHijri = applicantRitual.getDateStartHijri() != null ? applicantRitual.getDateStartHijri() : DateUtils.toHijri(applicantRitual.getDateStartGregorian());
            applicantRitual.setHijriSeason(Integer.parseInt(Long.toString(ritualDateStartHijri).substring(0, 4)));
        }

        Field applicantBasicInfoField = ReflectionUtils.findField(item.getClass(), "applicantBasicInfo");
        Field applicantHealthField = ReflectionUtils.findField(item.getClass(), "applicantHealth");
        Field applicantField = ReflectionUtils.findField(item.getClass(), "applicant");
        if (item == null || applicantBasicInfoField == null || (applicantField == null && applicantHealthField == null)) {
            return;
        }
        try {
            // make fields accessible
            ReflectionUtils.makeAccessible(applicantBasicInfoField);
            // get applicant basic info from the current object
            ApplicantBasicInfoDto applicantBasicInfo = (ApplicantBasicInfoDto) applicantBasicInfoField.get(item);
            // search applicant by his basic info from the database
            ApplicantDto applicant = applicantService.findByBasicInfo(applicantBasicInfo);
            if (applicant != null) {
                ApplicantHealthDto applicantHealth = applicant.getApplicantHealth() == null ? new ApplicantHealthDto() : applicant.getApplicantHealth();

                if (applicantField != null) {
                    // make fields accessible
                    ReflectionUtils.makeAccessible(applicantField);
                    // set the found applicant into the object
                    applicantField.set(item, applicant);
                }
                if (applicantHealthField != null) {
                    // make fields accessible
                    ReflectionUtils.makeAccessible(applicantHealthField);
                    applicantHealth.setApplicant(applicant);
                    // set the found applicant health into the object
                    applicantHealthField.set(item, applicantHealth);
                }
            }
        } catch (IllegalAccessException e) {
            ReflectionUtils.handleReflectionException(e);
        }
    }

    /**
     * Finds a mapper for a given dto class
     *
     * @param clazz the dto class to find mapper for
     * @return the found mapper
     */
    @SuppressWarnings("rawtypes")
    private IGenericMapper findMapper(Class clazz) {
        List<IGenericMapper> foundMappers = this.context.getBeansOfType(IGenericMapper.class).values().stream().filter(mapper -> Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(mapper.getClass(), IGenericMapper.class))[0].getSimpleName().equals(clazz.getSimpleName())).collect(Collectors.toList());
        return CollectionUtils.size(foundMappers) == 1 ? foundMappers.get(0) : null;
    }

}

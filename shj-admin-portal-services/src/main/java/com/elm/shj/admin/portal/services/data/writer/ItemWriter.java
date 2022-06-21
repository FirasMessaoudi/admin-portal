/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.writer;

import com.elm.dcc.foundation.commons.core.mapper.CycleAvoidingMappingContext;
import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicant;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantGroup;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStep;
import com.elm.shj.admin.portal.orm.repository.*;
import com.elm.shj.admin.portal.services.applicant.*;
import com.elm.shj.admin.portal.services.company.CompanyRitualSeasonService;
import com.elm.shj.admin.portal.services.company.CompanyRitualStepService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.data.huic.ValidationService;
import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.reader.EExcelItemReaderErrorType;
import com.elm.shj.admin.portal.services.data.validators.DataValidationResult;
import com.elm.shj.admin.portal.services.data.validators.JobTitleCode;
import com.elm.shj.admin.portal.services.data.validators.RitualTypeCode;
import com.elm.shj.admin.portal.services.data.validators.WithGroupReferenceNumber;
import com.elm.shj.admin.portal.services.digitalid.DigitalIdService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.lookup.CompanyRitualStepLookupService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
import com.elm.shj.admin.portal.services.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

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
    private final Map<String, Long> uniqueGroupLeader = new HashMap<>();
    private final CycleAvoidingMappingContext mappingContext;
    private final ApplicationContext context;
    private final ApplicantService applicantService;
    private final ApplicantGroupService applicantGroupService;
    private final ApplicantHealthSpecialNeedsRepository applicantHealthSpecialNeedsRepository;
    private final DataRequestRecordRepository dataRequestRecordRepository;
    private final ApplicantPackageService applicantPackageService;
    private final GroupApplicantListService groupApplicantListService;
    private final ApplicantRitualService applicantRitualService;
    private final ApplicantContactService applicantContactService;
    private final CompanyStaffService companyStaffService;
    private final CompanyRitualSeasonService companyRitualSeasonService;
    private final ChatContactService chatContactService;
    private final ApplicantRelativeService applicantRelativeService;
    private final ApplicantHealthService applicantHealthService;
    private final ApplicantEmergencyDataUploadService applicantEmergencyDataUploadService;
    private final DigitalIdService digitalIdService;
    private final ApplicantLiteService applicantLiteService;
    private final ValidationService validationService;
    private final CompanyRitualStepRepository companyRitualStepRepository;
    private final CompanyRitualStepService companyRitualStepService;
    private final CompanyRitualStepLookupService companyRitualStepLookupService;
    private final PackageHousingService packageHousingService;
    private final ApplicantPackageHousingService applicantPackageHousingService;
    @Value("${ritual.season.year}")
    private int seasonYear;

    private final static String DEFAULT_AVATAR_MALE = "avatar/staff-male.png";
    private final static String DEFAULT_AVATAR_FEMALE = "avatar/applicant-staff-female.png";

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
        repositoryRegistry.put(EDataSegment.STAFF_MAIN_DATA, CompanyStaffRepository.class);
        repositoryRegistry.put(EDataSegment.STAFF_FULL_MAIN_DATA, CompanyStaffRepository.class);
        repositoryRegistry.put(EDataSegment.MAIN_GROUP_DATA, ApplicantGroupRepository.class);
        repositoryRegistry.put(EDataSegment.GROUP_DATA, GroupApplicantListRepository.class);
        repositoryRegistry.put(EDataSegment.APPLICANT_HOUSING_DATA, ApplicantPackageHousingRepository.class);

        // mapper registry initialization
        mapperRegistry.put(EDataSegment.APPLICANT_DATA, Objects.requireNonNull(validationService.findMapper(ApplicantDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_RELATIVES_DATA, Objects.requireNonNull(validationService.findMapper(ApplicantRelativeDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_HEALTH_DATA, Objects.requireNonNull(validationService.findMapper(ApplicantHealthDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_IMMUNIZATION_DATA, Objects.requireNonNull(validationService.findMapper(ApplicantHealthImmunizationDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_DISEASE_DATA, Objects.requireNonNull(validationService.findMapper(ApplicantHealthDiseaseDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_RITUAL_DATA, Objects.requireNonNull(validationService.findMapper(ApplicantRitualDto.class)));
        mapperRegistry.put(EDataSegment.STAFF_MAIN_DATA, Objects.requireNonNull(validationService.findMapper(CompanyStaffDto.class)));
        mapperRegistry.put(EDataSegment.STAFF_FULL_MAIN_DATA, Objects.requireNonNull(validationService.findMapper(CompanyStaffDto.class)));
        mapperRegistry.put(EDataSegment.MAIN_GROUP_DATA, Objects.requireNonNull(validationService.findMapper(ApplicantGroupDto.class)));
        mapperRegistry.put(EDataSegment.GROUP_DATA, Objects.requireNonNull(validationService.findMapper(GroupApplicantListDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_HOUSING_DATA, Objects.requireNonNull(validationService.findMapper(ApplicantPackageHousingDto.class)));

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
    public <T, S> List<DataValidationResult> write(List<AbstractMap.SimpleEntry<Row, T>> items, DataSegmentDto dataSegment, long dataRequestId, String... companyRefCode) {
        log.info("Confirming Item writer #{}", companyRefCode);
        if (dataSegment.getId() == EDataSegment.APPLICANT_EMERGENCY_DATA.getId()) {
            JpaRepository applicantRepository = (JpaRepository) context.getBean(repositoryRegistry.get(EDataSegment.APPLICANT_DATA));
            List<DataRequestRecordDto> dataRequestRecords = new ArrayList<>();
            List<S> savedItems = new ArrayList<>();
            items.forEach(entry -> {
                ApplicantEmergencyDto emergencyDto = (ApplicantEmergencyDto) entry.getValue();
                ApplicantDto applicantDto = getApplicantFromEmergency(emergencyDto);
                ApplicantRitualEmergencyDto applicantRitualEmergencyDto = emergencyDto.getApplicantRitualEmergencyDto();

                updateNestedApplicantInfo(applicantDto);
                // find old ald applicant rituals for existing applicant
                if (applicantDto.getId() > 0) {
                    List<ApplicantRitualDto> applicantRituals = applicantRitualService.findAllByApplicantId(applicantDto.getId());
                    if (!applicantRituals.isEmpty()) {
                        applicantDto.setRituals(applicantRituals);
                    }
                }

                String applicantUin = applicantDto.getId() > 0 ? digitalIdService.findApplicantUin(applicantDto.getId()) : null;

                // create a new applicant ritual
                ApplicantRitualDto applicantRitual = ApplicantRitualDto.builder().applicant(applicantDto).packageReferenceNumber(emergencyDto.getPackageReferenceNumber()).build();
                // update applicant ritual from the emergency data and update applicant with the new ritual.
                updateApplicantRitualInfo(applicantRitual, applicantRitualEmergencyDto);

                // check if digital id is exists, if yes then create a new applicant package and link it with the applicant ritual
                if (applicantUin != null && !applicantUin.isEmpty()) {
                    ApplicantPackageDto createdApplicantPackage = applicantPackageService.createApplicantPackage(applicantRitual.getPackageReferenceNumber(),
                            Long.parseLong(applicantUin), applicantRitualEmergencyDto.getBusNumber(), applicantRitualEmergencyDto.getSeatNumber());
                    applicantRitual.setApplicantPackage(createdApplicantPackage);
                } else {
                    // create an applicant emergency data upload record to persist transportation details (bus number and seat number) which will be used in digital id scheduler
                    applicantEmergencyDataUploadService.save(ApplicantEmergencyDataUploadDto.builder().idNumber(applicantDto.getIdNumber())
                            .passportNumber(applicantDto.getPassportNumber()).dateOfBirthHijri(applicantDto.getDateOfBirthHijri())
                            .dateOfBirthGregorian(applicantDto.getDateOfBirthGregorian()).packageReferenceNumber(emergencyDto.getPackageReferenceNumber())
                            .busNumber(applicantRitualEmergencyDto.getBusNumber()).seatNumber(applicantRitualEmergencyDto.getSeatNumber()).build());
                }

                if (CollectionUtils.isNotEmpty(applicantDto.getRituals())) {
                    applicantDto.getRituals().add(applicantRitual);
                } else {
                    applicantDto.setRituals(Collections.singletonList(applicantRitual));
                }

                S savedApplicant = (S) applicantRepository.save(mapperRegistry.get(EDataSegment.APPLICANT_DATA).toEntity(applicantDto, mappingContext));
                savedItems.add(savedApplicant);
                try {
                    dataRequestRecords.add(DataRequestRecordDto.builder()
                            .createDataRequestId(dataRequestId)
                            .lastUpdateDataRequestId(dataRequestId)
                            .createDataRequestRowNum((long) entry.getKey().getRowNum())
                            .lastUpdateDataRequestRowNum((long) entry.getKey().getRowNum())
                            .itemId(Long.parseLong(BeanUtils.getProperty(savedApplicant, "id")))
                            .build());
                } catch (Exception e) {
                    ReflectionUtils.handleReflectionException(e);
                }
            });
            List savedRecords = dataRequestRecordRepository.saveAll(Objects.requireNonNull(validationService.findMapper(DataRequestRecordDto.class)).toEntityList(dataRequestRecords, mappingContext));
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
                        //set data request record for the saved applicants and applicants rituals
                        Long dataRequestRecordId = Long.parseLong(BeanUtils.getProperty(r, "id"));
                        BeanUtils.setProperty(s, "dataRequestRecordId", dataRequestRecordId);
                        JpaApplicantRitual lastApplicantRitual = ((JpaApplicant) s).getRituals().get(((JpaApplicant) s).getRituals().size() - 1);
                        applicantService.updateDataRequestRecordId(dataRequestRecordId, ((JpaApplicant) s).getId(), lastApplicantRitual.getId());
                    } catch (Exception e) {
                        ReflectionUtils.handleReflectionException(e);
                    }
                });
            });
            return Collections.emptyList();
        }

        if (dataSegment.getId() == EDataSegment.STAFF_APPLICANT_GROUP_DATA.getId()) {
            List<DataValidationResult> dataValidationResults = new ArrayList<>();
            items.forEach(entry -> {
                //mark row with error
                Class clazz = StaffApplicantGroupDto.class;
                int cellIndex = 0;
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(WithGroupReferenceNumber.class))
                        cellIndex = field.getAnnotation(CellIndex.class).index();
                }
                StaffApplicantGroupDto staffApplicantGroupDto = (StaffApplicantGroupDto) entry.getValue();
                ApplicantBasicInfoDto applicantBasicInfoDto = new ApplicantBasicInfoDto();
                applicantBasicInfoDto.setIdNumber(staffApplicantGroupDto.getIdNumber());
                applicantBasicInfoDto.setPassportNumber(staffApplicantGroupDto.getPassportNumber());
                applicantBasicInfoDto.setDateOfBirthGregorian(staffApplicantGroupDto.getDateOfBirthGregorian());
                applicantBasicInfoDto.setDateOfBirthHijri(staffApplicantGroupDto.getDateOfBirthHijri());
                CompanyRitualSeasonDto companyRitualSeasonDto = companyRitualSeasonService.getLatestCompanyRitualSeasonByRitualSeason(staffApplicantGroupDto.getCompanyCode(), staffApplicantGroupDto.getRitualTypeCode(), staffApplicantGroupDto.getSeason());
                if (companyRitualSeasonDto != null) {
                    ApplicantGroupDto applicantGroupDto = applicantGroupService.getApplicantGroupByReferenceNumberAndCompanyRitualSeasonId(staffApplicantGroupDto.getGroupReferenceNumber(), companyRitualSeasonDto.getId());
                    if (applicantGroupDto != null) {
                        CompanyStaffDto groupLeader = companyStaffService.findGroupLeaderByBasicInfo(staffApplicantGroupDto.getStaffIdNumber(), staffApplicantGroupDto.getStaffPassportNumber(), staffApplicantGroupDto.getStaffDateOfBirthGregorian(), staffApplicantGroupDto.getStaffDateOfBirthHijri());
                        applicantGroupDto.setGroupLeader(groupLeader);
                        if (uniqueGroupLeader.containsKey(applicantGroupDto.getReferenceNumber())) {
                            if (uniqueGroupLeader.get(applicantGroupDto.getReferenceNumber()) != groupLeader.getId()) {
                                dataValidationResults.add(DataValidationResult.builder().valid(false).cell(entry.getKey().getCell(cellIndex + 1)).errorMessages(Collections.singletonList(EExcelItemReaderErrorType.NOT_GROUP_WITH_DIFFERENT_LEADERS.getMessage())).valid(false).build());
                            }
                        } else {
                            uniqueGroupLeader.put(applicantGroupDto.getReferenceNumber(), groupLeader.getId());
                        }
                        //TODO: fetch UIN instead of the whole applicant object
                        ApplicantDto applicantDto = applicantService.findByBasicInfo(applicantBasicInfoDto);
                        if (applicantDto != null) {
                            chatContactService.createGroupLeaderContact(applicantDto.getDigitalIds().get(0).getUin(), groupLeader, staffApplicantGroupDto.getSeason());
                            groupApplicantListService.registerUserToGroup(applicantDto.getDigitalIds().get(0).getUin(), staffApplicantGroupDto.getGroupReferenceNumber());
                        } else {
                            //this applicant not found in db in time of processing
                            log.error("this applicant not found in db in time of processing");
                        }
                        applicantGroupService.save(applicantGroupDto);
                    } else {
                        //this applicant group not found in db in time of processing
                        log.error("this applicant group not found in db in time of processing");
                    }

                } else {
                    //no companyRitualSeason Record is found with these details  in db in time of processing
                    log.error("no companyRitualSeason Record is found with these details  in db in time of processing");
                    return;
                }

            });
            return dataValidationResults;
        }

        if (dataSegment.getId() == EDataSegment.STAFF_RITUAL_DATA.getId()) {
            //Temporary for testing only : staff ritual
            items.forEach(entry -> updateCompanyStaffRitualData(entry.getValue()));
            return Collections.emptyList();
        }

        if (dataSegment.getId() == EDataSegment.GROUP_DATA.getId()) {
            List<DataRequestRecordDto> dataRequestRecords = new ArrayList<>();
            List<S> savedItems = new ArrayList<>();
            JpaRepository repository = (JpaRepository) context.getBean(repositoryRegistry.get(EDataSegment.fromId(dataSegment.getId())));
            List<DataValidationResult> dataValidationResults = new ArrayList<>();
            items.forEach(entry -> {
                S savedItem;
                GroupDataDto groupDataDto = (GroupDataDto) entry.getValue();
                CompanyRitualSeasonDto companyRitualSeasonDto = companyRitualSeasonService.getLatestCompanyRitualSeasonByRitualSeason(companyRefCode[0], groupDataDto.getRitualTypeCode(), seasonYear);
                if (companyRitualSeasonDto == null) {
                    dataValidationResults.add(DataValidationResult.builder().valid(false).cell(entry.getKey().getCell(5)).errorMessages(Collections.singletonList(EExcelItemReaderErrorType.NOT_APPLICANT_GROUP_FOUND.getMessage())).valid(false).build());
                    return;
                }
                // String companyCode = companyRefCode[0].contains("_") ? companyRefCode[0].substring(0, companyRefCode[0].indexOf("_")) : companyRefCode[0];
                ApplicantGroupDto applicantGroupDto = applicantGroupService.getApplicantGroupByReferenceNumberAndCompanyRitualSeasonId(groupDataDto.getGroupReferenceNumber(), companyRitualSeasonDto.getId());
                if (applicantGroupDto == null) {
                    dataValidationResults.add(DataValidationResult.builder().valid(false).cell(entry.getKey().getCell(5)).errorMessages(Collections.singletonList(EExcelItemReaderErrorType.NOT_APPLICANT_GROUP_FOUND.getMessage())).valid(false).build());
                    return;
                }
                ApplicantLiteDto applicantLiteDto = applicantLiteService.findByBasicInfo(groupDataDto.getIdNumber(), groupDataDto.getPassportNumber(), groupDataDto.getNationalityCode());

                // validate applicant is belong to loggend in  user company
                if(!validationService.isValidApplicant(applicantLiteDto, companyRefCode[0])){
                    dataValidationResults.add(DataValidationResult.builder().valid(false).cell(entry.getKey().getCell(1)).errorMessages(Collections.singletonList(EExcelItemReaderErrorType.NOT_APPLICANT_FOUND.getMessage())).valid(false).build());
                    return;
                }

                // check applicant is already exist for that group then no need to add new update the existin applicant
                GroupApplicantListDto existingGroupApplicant = groupApplicantListService.findByUin(applicantLiteDto.getDigitalIds().get(0).getUin(), applicantGroupDto.getId());

                GroupApplicantListDto groupApplicantListDto = GroupApplicantListDto.builder()
                        .applicantGroup(applicantGroupDto)
                        .applicantUin(applicantLiteDto.getDigitalIds().get(0).getUin())
                        .build();

                if(existingGroupApplicant != null){
                    validationService.updateGroupApplicantList(groupApplicantListDto, existingGroupApplicant);
                    savedItem = (S) repository.save(mapperRegistry.get(EDataSegment.fromId(dataSegment.getId())).toEntity(groupApplicantListDto, mappingContext));
                } else {
                    savedItem = (S) repository.save(mapperRegistry.get(EDataSegment.fromId(dataSegment.getId())).toEntity(groupApplicantListDto, mappingContext));
                }
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
                List savedRecords = dataRequestRecordRepository.saveAll(Objects.requireNonNull(validationService.findMapper(DataRequestRecordDto.class)).toEntityList(dataRequestRecords, mappingContext));
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
                            BeanUtils.setProperty(s, "dataRequestRecordId", BeanUtils.getProperty(r, "id"));
                        } catch (Exception e) {
                            ReflectionUtils.handleReflectionException(e);
                        }
                    });
                });


            });
            // update saved items
            repository.saveAll(savedItems);
            return dataValidationResults;
        }

        if (dataSegment.getId() == EDataSegment.MAIN_GROUP_DATA.getId()) {
            List<DataRequestRecordDto> dataRequestRecords = new ArrayList<>();
            List<S> savedItems = new ArrayList<>();
            JpaRepository repository = (JpaRepository) context.getBean(repositoryRegistry.get(EDataSegment.fromId(dataSegment.getId())));
            List<DataValidationResult> dataValidationResults = new ArrayList<>();
            items.forEach(entry -> {
                S savedItem;
                Class clazz = GroupMainDataDto.class;
                int ritualTypeCodeCellIndex = 0;
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(RitualTypeCode.class))
                        ritualTypeCodeCellIndex = field.getAnnotation(CellIndex.class).index();
                }
                GroupMainDataDto groupMainDataDto = (GroupMainDataDto) entry.getValue();
                CompanyRitualSeasonDto companyRitualSeasonDto = companyRitualSeasonService.getLatestCompanyRitualSeasonByRitualSeason(companyRefCode[0], groupMainDataDto.getRitualTypeCode(), seasonYear);
                if (companyRitualSeasonDto == null) {
                    dataValidationResults.add(DataValidationResult.builder().valid(false).cell(entry.getKey().getCell(ritualTypeCodeCellIndex)).errorMessages(Collections.singletonList(EExcelItemReaderErrorType.NOT_RITUAL_TYPE_FOUND.getMessage())).valid(false).build());
                    return;
                }
                CompanyStaffDto existingStaff = companyStaffService.findByBasicInfo(groupMainDataDto.getStaffIdNumber(), groupMainDataDto.getStaffPassportNumber(), groupMainDataDto.getNationalityCode());
                // String companyCode = companyRefCode[0].contains("_") ? companyRefCode[0].substring(0, companyRefCode[0].indexOf("_")) : companyRefCode[0];
                ApplicantGroupDto existingGroup = applicantGroupService.getApplicantGroupByReferenceNumberAndCompanyRitualSeasonId(groupMainDataDto.getGroupReferenceNumber(), companyRitualSeasonDto.getId());
                ApplicantGroupDto applicantGroupDto = ApplicantGroupDto.builder()
                        .groupLeader(existingStaff)
                        .groupName(groupMainDataDto.getGroupName())
                        .companyRitualSeason(companyRitualSeasonDto)
                        .referenceNumber(groupMainDataDto.getGroupReferenceNumber())
                        .build();
                if (existingGroup != null) {
                    applicantGroupDto.setId(existingGroup.getId());
                    applicantGroupDto.setCompanyRitualSteps(existingGroup.getCompanyRitualSteps());
                    applicantGroupDto.setGroupApplicantLists(existingGroup.getGroupApplicantLists());

                }

                savedItem = (S) repository.save(mapperRegistry.get(EDataSegment.fromId(dataSegment.getId())).toEntity(applicantGroupDto, mappingContext));
                if (existingGroup == null) {
                    List<CompanyRitualStepLookupDto> companyRitualStepLookupDtos = companyRitualStepLookupService.findAllWithLang();
                    companyRitualStepLookupDtos.forEach(companyRitualStepLookupDto -> {
                        JpaCompanyRitualStep companyRitualStep = new JpaCompanyRitualStep();
                        companyRitualStep.setStepCode(companyRitualStepLookupDto.getCode());
                        companyRitualStep.setStepIndex(companyRitualStepLookupDto.getStepIndex());
                        companyRitualStep.setLocationLat(companyRitualStepLookupDto.getLocationLat());
                        companyRitualStep.setLocationLng(companyRitualStepLookupDto.getLocationLng());
                        //TODO: to be checked
                        companyRitualStep.setLocationNameAr("");
                        companyRitualStep.setLocationNameEn("");
                        companyRitualStep.setTime(new Date());
                        companyRitualStep.setApplicantGroup((JpaApplicantGroup) savedItem);
                        companyRitualStepRepository.save(companyRitualStep);

                    });
                }

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
                List savedRecords = dataRequestRecordRepository.saveAll(Objects.requireNonNull(validationService.findMapper(DataRequestRecordDto.class)).toEntityList(dataRequestRecords, mappingContext));
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
                            BeanUtils.setProperty(s, "dataRequestRecordId", BeanUtils.getProperty(r, "id"));
                        } catch (Exception e) {
                            ReflectionUtils.handleReflectionException(e);
                        }
                    });
                });


            });
            // update saved items
            repository.saveAll(savedItems);
            return dataValidationResults;
        }

        if (dataSegment.getId() == EDataSegment.APPLICANT_HOUSING_DATA.getId()) {
            List<DataValidationResult> dataValidationResults = new ArrayList<>();
            items.forEach(entry -> {
                ApplicantHousingDataDto applicantHousingDataDto = (ApplicantHousingDataDto) entry.getValue();
                Long applicantId = applicantService.findIdByBasicInfo(applicantHousingDataDto.getIdNumber(), applicantHousingDataDto.getPassportNumber(), applicantHousingDataDto.getNationalityCode());
                if (applicantId == null) {
                    return;
                }
                ApplicantPackageDto applicantPackageDto = applicantPackageService.findJpaApplicantPackageByApplicantId(applicantId);
                if (applicantPackageDto == null) {
                    dataValidationResults.add(DataValidationResult.builder().valid(false).cell(entry.getKey().getCell(1)).errorMessages(Collections.singletonList(EExcelItemReaderErrorType.APPLICANT_PACKAGE_NOT_FOUND.getMessage())).valid(false).build());
                    return;
                }

                PackageHousingDto menaHousing = packageHousingService.findByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.MENA.name());
                PackageHousingDto arafetHousing = packageHousingService.findByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.ARAFAT.name());

                if (menaHousing == null && arafetHousing == null) {
                    dataValidationResults.add(DataValidationResult.builder().valid(false).cell(entry.getKey().getCell(1)).errorMessages(Collections.singletonList(EExcelItemReaderErrorType.PACKAGE_HOUSING_NOT_FOUND.getMessage())).valid(false).build());
                    return;
                }

                if (menaHousing != null) {
                    ApplicantPackageHousingDto applicantPackageHousingMena = applicantPackageHousingService.findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), menaHousing.getId());
                    if (applicantPackageHousingMena != null) {
                        applicantPackageHousingMena.setSiteCampRefCode(applicantHousingDataDto.getMenaCampRefCode());
                        applicantPackageHousingMena.setSiteBedNumber(applicantHousingDataDto.getMenaBedNumber());
                        applicantPackageHousingMena.setSiteCorridor(applicantHousingDataDto.getMenaCorridor());
                        applicantPackageHousingMena.setSiteFloor(applicantHousingDataDto.getMenaFloor());
                        applicantPackageHousingMena.setSiteRoom(applicantHousingDataDto.getMenaRoom());
                        applicantPackageHousingMena.setSiteTent(applicantHousingDataDto.getMenaTent());
                        applicantPackageHousingService.save(applicantPackageHousingMena);

                    }

                }
                if (arafetHousing != null) {
                    ApplicantPackageHousingDto applicantPackageHousingArafet = applicantPackageHousingService.findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), arafetHousing.getId());
                    if (applicantPackageHousingArafet != null) {
                        applicantPackageHousingArafet.setSiteCampRefCode(applicantHousingDataDto.getArafetCampRefCode());
                        applicantPackageHousingArafet.setSiteBedNumber(applicantHousingDataDto.getArafetBedNumber());
                        applicantPackageHousingArafet.setSiteCorridor(applicantHousingDataDto.getArafetCorridor());
                        applicantPackageHousingArafet.setSiteFloor(applicantHousingDataDto.getArafetFloor());
                        applicantPackageHousingArafet.setSiteRoom(applicantHousingDataDto.getArafetRoom());
                        applicantPackageHousingArafet.setSiteTent(applicantHousingDataDto.getArafetTent());
                        applicantPackageHousingService.save(applicantPackageHousingArafet);

                    }

                }

            });
            // update saved items
            return dataValidationResults;
        }

        // saving staff full main data
        if (dataSegment.getId() == EDataSegment.STAFF_FULL_MAIN_DATA.getId()) {

            // save all items and build data records
            List<DataRequestRecordDto> dataRequestRecords = new ArrayList<>();
            List<S> savedItems = new ArrayList<>();
            JpaRepository repository = (JpaRepository) context.getBean(repositoryRegistry.get(EDataSegment.fromId(dataSegment.getId())));

            List<DataValidationResult> dataValidationResults = new ArrayList<>();
            items.forEach(entry -> {
                S savedItem;
                try {
                    Class clazz = CompanyStaffFullDataDto.class;
                    int ritualTypeCodeCellIndex = 0;
                    int jobTileCodeCellIndex = 0;
                    for (Field field : clazz.getDeclaredFields()) {
                        if (field.isAnnotationPresent(RitualTypeCode.class))
                            ritualTypeCodeCellIndex = field.getAnnotation(CellIndex.class).index();
                        if (field.isAnnotationPresent(JobTitleCode.class))
                            jobTileCodeCellIndex = field.getAnnotation(CellIndex.class).index();
                    }
                    CompanyStaffFullDataDto companyStaffFullData = (CompanyStaffFullDataDto) entry.getValue();

                    // set default avatar if the photo is null
                    if(companyStaffFullData.getPhoto() == null){

                        BufferedImage defaultImage;

                        if(companyStaffFullData.getGender().equals("M")) {
                            defaultImage = ImageUtils.loadFromClasspath(DEFAULT_AVATAR_MALE);
                        } else {
                            defaultImage = ImageUtils.loadFromClasspath(DEFAULT_AVATAR_FEMALE);
                        }

                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        final String defaultAvatar;

                        try {
                            ImageIO.write(defaultImage, "png", bos);
                            byte[] bytes = bos.toByteArray();

                            defaultAvatar = Base64.getEncoder().encodeToString(bytes).replace(System.lineSeparator(), "");
                        } catch (IOException e) {
                            throw new RuntimeException();
                        }

                        companyStaffFullData.setPhoto(defaultAvatar);
                    }

                    // check company ritual season exist for the ritual type, seasson and company
                    CompanyRitualSeasonDto companyRitualSeasonDto = companyRitualSeasonService.getCompanyRitualSeason(companyRefCode[0], companyStaffFullData.getTypeCode(), seasonYear);
                    if(companyRitualSeasonDto == null){
                        dataValidationResults.add(DataValidationResult.builder().valid(false).cell(entry.getKey().getCell(ritualTypeCodeCellIndex)).errorMessages(Collections.singletonList(EExcelItemReaderErrorType.NOT_RITUAL_TYPE_FOUND.getMessage())).valid(false).build());
                        return;
                    }

                    // In case of job title code is Others set the job tile code to tileCodeOther
                    if(companyStaffFullData.getTitleCode() != null){
                        if(companyStaffFullData.getTitleCode().equals("OTHERS")){
                            if(companyStaffFullData.getCustomJobTitle() == null) {
                                dataValidationResults.add(DataValidationResult.builder().valid(false).cell(entry.getKey().getCell(jobTileCodeCellIndex + 2)).errorMessages(Collections.singletonList(EExcelItemReaderErrorType.FIELD_REQUIRED.getMessage())).valid(false).build());
                                return;
                            }
                        }
                    }

                    CompanyStaffDto staff = mapCompanyStaffDto(companyStaffFullData);
                    // copy properties from company staff full data to company staff

                   // BeanUtils.copyProperties(staff, companyStaffFullData);
                    CompanyStaffDto existingStaff = companyStaffService.findByBasicInfo(staff.getIdNumber(), staff.getPassportNumber(), staff.getNationalityCode());
                    // if record exists already in DB we need to update it
                    if (existingStaff != null) {
                        validationService.updateExistingStaff(staff, existingStaff);
                        //companyStaffService.save(staff);
                        savedItem = (S) repository.save(mapperRegistry.get(EDataSegment.fromId(dataSegment.getId())).toEntity(staff, mappingContext));
                    } else {
                       // companyStaffService.save(staff);
                        savedItem = (S) repository.save(mapperRegistry.get(EDataSegment.fromId(dataSegment.getId())).toEntity(staff, mappingContext));
                    }
                    savedItems.add(savedItem);
                    dataRequestRecords.add(DataRequestRecordDto.builder()
                            .createDataRequestId(dataRequestId)
                            .lastUpdateDataRequestId(dataRequestId)
                            .createDataRequestRowNum((long) entry.getKey().getRowNum())
                            .lastUpdateDataRequestRowNum((long) entry.getKey().getRowNum())
                            .itemId(Long.parseLong(BeanUtils.getProperty(savedItem, "id")))
                            .build());

                    // start adding staff ritual data
                    CompanyStaffRitualDto companyStaffRitual = mapCompanyStaffRitualDto(companyStaffFullData, companyRefCode[0]);
                    //companyStaffRitual.setSeason(seasonYear);
                    //companyStaffRitual.setCompanyCode(companyRefCode[0]);
                    //BeanUtils.copyProperties(companyStaffRitual, companyStaffFullData);
                    updateCompanyStaffRitualData(companyStaffRitual, Long.parseLong(BeanUtils.getProperty(savedItem, "id")));

                }catch (Exception e){
                    log.error("Error while creating company staff full data");
                    ReflectionUtils.handleReflectionException(e);

                }

            });

            // save all records
            List savedRecords = dataRequestRecordRepository.saveAll(Objects.requireNonNull(validationService.findMapper(DataRequestRecordDto.class)).toEntityList(dataRequestRecords, mappingContext));
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
                        BeanUtils.setProperty(s, "dataRequestRecordId", BeanUtils.getProperty(r, "id"));
                    } catch (Exception e) {
                        ReflectionUtils.handleReflectionException(e);
                    }
                });
            });

            // update saved items
            repository.saveAll(savedItems);
            return dataValidationResults;
        }

        if (dataSegment.getId() == EDataSegment.APPLICANT_DATA.getId() || dataSegment.getId() == EDataSegment.APPLICANT_HEALTH_DATA.getId() ||
                dataSegment.getId() == EDataSegment.APPLICANT_IMMUNIZATION_DATA.getId() || dataSegment.getId() == EDataSegment.APPLICANT_DISEASE_DATA.getId() ||
                dataSegment.getId() == EDataSegment.APPLICANT_RITUAL_DATA.getId() || dataSegment.getId() == EDataSegment.APPLICANT_RELATIVES_DATA.getId()) {
            // update applicant related attributes
            items.forEach(entry -> updateNestedApplicantInfo(entry.getValue()));
        }

        // save all items and build data records
        List<DataRequestRecordDto> dataRequestRecords = new ArrayList<>();
        List<S> savedItems = new ArrayList<>();
        JpaRepository repository = (JpaRepository) context.getBean(repositoryRegistry.get(EDataSegment.fromId(dataSegment.getId())));
        items.forEach(entry -> {
            S savedItem;
            //this part is to handle staff main data
            if (dataSegment.getId() == EDataSegment.STAFF_MAIN_DATA.getId()) {
                CompanyStaffDto staff = (CompanyStaffDto) entry.getValue();
                CompanyStaffDto existingStaff = companyStaffService.findByBasicInfo(staff.getIdNumber(), staff.getPassportNumber(), staff.getDateOfBirthGregorian(), staff.getDateOfBirthHijri());
                // if record exists already in DB we need to update it
                if (existingStaff != null) {
                    validationService.updateExistingStaff(staff, existingStaff);
                    savedItem = (S) repository.save(mapperRegistry.get(EDataSegment.fromId(dataSegment.getId())).toEntity(staff, mappingContext));
                } else {
                    savedItem = (S) repository.save(mapperRegistry.get(EDataSegment.fromId(dataSegment.getId())).toEntity(entry.getValue(), mappingContext));
                }
            } else {
                if (dataSegment.getId() == EDataSegment.APPLICANT_HEALTH_DATA.getId()) {
                    ApplicantHealthDto applicantHealth = (ApplicantHealthDto) entry.getValue();
                    // find existing (if inserted by other segments like diseases and immunizations) and update or save the new one
                    Long applicantHealthId = applicantHealthService.findIdByApplicantIdAndPackageReferenceNumber(applicantHealth.getApplicant().getId(), applicantHealth.getPackageReferenceNumber());
                    if (applicantHealthId != null) {
                        applicantHealth.setId(applicantHealthId);
                        applicantHealth.setUpdateDate(new Date());
                    }
                }
                if (dataSegment.getId() == EDataSegment.APPLICANT_DATA.getId()) {
                    ApplicantDto applicantDto = ((ApplicantDto) entry.getValue());
                    if (applicantDto.getPhoto() != null) {
                        applicantDto.setPhoto(applicantDto.getPhoto().replaceAll("(\r\n|\n\r|\r|\n)", ""));
                    }
                }
                savedItem = (S) repository.save(mapperRegistry.get(EDataSegment.fromId(dataSegment.getId())).toEntity(entry.getValue(), mappingContext));
            }
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
        List savedRecords = dataRequestRecordRepository.saveAll(Objects.requireNonNull(validationService.findMapper(DataRequestRecordDto.class)).toEntityList(dataRequestRecords, mappingContext));
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
                    BeanUtils.setProperty(s, "dataRequestRecordId", BeanUtils.getProperty(r, "id"));
                } catch (Exception e) {
                    ReflectionUtils.handleReflectionException(e);
                }
            });
        });

        // update saved items
        repository.saveAll(savedItems);

        return Collections.emptyList();
    }

    private CompanyStaffDto mapCompanyStaffDto(CompanyStaffFullDataDto companyStaffFullData){
        CompanyStaffDto companyStaff = CompanyStaffDto.builder()
                .idNumber(companyStaffFullData.getIdNumber())
                .passportNumber(companyStaffFullData.getPassportNumber())
                .dateOfBirthGregorian(companyStaffFullData.getDateOfBirthGregorian())
                .dateOfBirthHijri(companyStaffFullData.getDateOfBirthHijri())
                .fullNameAr(companyStaffFullData.getFullNameAr())
                .fullNameEn(companyStaffFullData.getFullNameEn())
                .fullNameOrigin(companyStaffFullData.getFullNameOrigin())
                .gender(companyStaffFullData.getGender())
                .nationalityCode(companyStaffFullData.getNationalityCode())
                .idNumberOriginal(companyStaffFullData.getIdNumberOriginal())
                .titleCode(companyStaffFullData.getTitleCode())
                .customJobTitle(companyStaffFullData.getCustomJobTitle())
                .email(companyStaffFullData.getEmail())
                .mobileNumber(companyStaffFullData.getMobileNumber())
                .mobileNumberIntl(companyStaffFullData.getMobileNumberIntl())
                .photo(companyStaffFullData.getPhoto())
                .build();
        return companyStaff;
    }

    private CompanyStaffRitualDto mapCompanyStaffRitualDto(CompanyStaffFullDataDto companyStaffFullData, String companyCode){
        CompanyStaffRitualDto companyStaffRitual = CompanyStaffRitualDto.builder()
                .idNumber(companyStaffFullData.getIdNumber())
                .passportNumber(companyStaffFullData.getPassportNumber())
                .dateOfBirthGregorian(companyStaffFullData.getDateOfBirthGregorian())
                .dateOfBirthHijri(companyStaffFullData.getDateOfBirthHijri())
                .companyCode(companyCode)
                .typeCode(companyStaffFullData.getTypeCode())
                .season(seasonYear)
                .build();
        return companyStaffRitual;
    }


    /**
     * update related applicant ritual from applicant emergency
     *
     * @param applicantRitualDto
     * @param applicantRitualEmergencyDto
     */

    private void updateApplicantRitualInfo(ApplicantRitualDto applicantRitualDto, ApplicantRitualEmergencyDto applicantRitualEmergencyDto) {
        applicantRitualDto.setBorderNumber(applicantRitualEmergencyDto.getBorderNumber());
        applicantRitualDto.setVisaNumber(applicantRitualEmergencyDto.getVisaNumber());
        applicantRitualDto.setPermitNumber(applicantRitualEmergencyDto.getPermitNumber());
        applicantRitualDto.setInsuranceNumber(applicantRitualEmergencyDto.getInsuranceNumber());
        applicantRitualDto.setBusNumber(applicantRitualEmergencyDto.getBusNumber());
        applicantRitualDto.setSeatNumber(applicantRitualEmergencyDto.getSeatNumber());
        applicantRitualDto.setGroupReferenceNumber(applicantRitualEmergencyDto.getGroupReferenceNumber());
    }

    /**
     * get related applicant properties from emergencyDto
     *
     * @param emergencyDto
     */
    private ApplicantDto getApplicantFromEmergency(ApplicantEmergencyDto emergencyDto) {
        ApplicantDto applicantDto = new ApplicantDto();
        applicantDto.setGender(emergencyDto.getGender());
        applicantDto.setNationalityCode(emergencyDto.getNationalityCode());
        applicantDto.setIdNumber(emergencyDto.getIdNumber());
        applicantDto.setIdNumberOriginal(emergencyDto.getIdNumberOriginal());
        applicantDto.setPassportNumber(emergencyDto.getPassportNumber());
        applicantDto.setDateOfBirthGregorian(emergencyDto.getDateOfBirthGregorian());
        applicantDto.setDateOfBirthHijri(emergencyDto.getDateOfBirthHijri());
        applicantDto.setFullNameAr(emergencyDto.getFullNameAr());
        applicantDto.setFullNameEn(emergencyDto.getFullNameEn());
        applicantDto.setFullNameOrigin(emergencyDto.getFullNameOrigin());
        applicantDto.setMaritalStatusCode(emergencyDto.getMaritalStatusCode());
        applicantDto.setPhoto(emergencyDto.getPhoto());
        applicantDto.setBiometricDataFace(emergencyDto.getBiometricDataFace());
        applicantDto.setBiometricDataFinger(emergencyDto.getBiometricDataFinger());
        applicantDto.setEducationLevelCode(emergencyDto.getEducationLevelCode());
        applicantDto.setPackageReferenceNumber(emergencyDto.getPackageReferenceNumber());
        applicantDto.setContacts(emergencyDto.getContacts());
        return applicantDto;
    }

    /**
     * Updates related applicant properties
     *
     * @param item the item to update
     */
    @SuppressWarnings("unchecked")
    private <T> void updateNestedApplicantInfo(T item) {

        if (item == null) return;

        // Special treatment for ApplicantDto and contact info as they come in the same sheet
        if (item.getClass().isAssignableFrom(ApplicantDto.class)) {
            ApplicantDto applicant = (ApplicantDto) item;
            validationService.updateApplicantBirthDate(applicant);
            Long existingApplicantId = applicantService.findIdByBasicInfo(ApplicantBasicInfoDto.fromApplicant(applicant));
            // if record exists already in DB we need to update it
            if (existingApplicantId != null) {
                validationService.updateExistingApplicant(applicant, existingApplicantId);
            }
            // this case is for applicant data upload
            validationService.addApplicantToContact(applicant);
        }

        Field applicantBasicInfoField = ReflectionUtils.findField(item.getClass(), "applicantBasicInfo");
        Field applicantHealthField = ReflectionUtils.findField(item.getClass(), "applicantHealth");
        Field applicantField = ReflectionUtils.findField(item.getClass(), "applicant");
        Field packageReferenceNumberField = ReflectionUtils.findField(item.getClass(), "packageReferenceNumber");
        Field applicantRitualField = ReflectionUtils.findField(item.getClass(), "applicantRitual");

        if (applicantBasicInfoField == null || packageReferenceNumberField == null || (applicantField == null && applicantHealthField == null)) {
            return;
        }

        try {
            // make fields accessible
            ReflectionUtils.makeAccessible(applicantBasicInfoField);
            // get applicant basic info from the current object
            ApplicantBasicInfoDto applicantBasicInfo = (ApplicantBasicInfoDto) applicantBasicInfoField.get(item);
            // search applicant by his basic info from the database
            ApplicantLiteDto applicantLite = applicantLiteService.findByBasicInfo(applicantBasicInfo);
            Long applicantId = applicantLite.getId();
            if (applicantLite == null) {
                return;
            }

            if (applicantField != null) {
                // make fields accessible
                ReflectionUtils.makeAccessible(applicantField);
                // set the found applicant into the object
                applicantField.set(item, ApplicantDto.builder().id(applicantId).build());
            }

            ReflectionUtils.makeAccessible(packageReferenceNumberField);
            String packageReferenceNumber = (String) packageReferenceNumberField.get(item);

            // retrieve the applicant ritual id if the applicant ritual is saved before by the digital id scheduler.
            Long savedApplicantRitualId = applicantRitualService.findAndUpdate(applicantId, packageReferenceNumber, null, false);

            if (applicantRitualField != null && savedApplicantRitualId != null) {
                ReflectionUtils.makeAccessible(applicantRitualField);
                applicantRitualField.set(item, ApplicantRitualDto.builder().id(savedApplicantRitualId).build());
            }

            String applicantUin = digitalIdService.findApplicantUin(applicantId);

            if (item.getClass().isAssignableFrom(ApplicantRitualDto.class)) {
                ApplicantRitualDto applicantRitual = (ApplicantRitualDto) item;
                validationService.updateApplicantRitual(applicantRitual, savedApplicantRitualId, applicantId, applicantUin);
            }

            if (item.getClass().isAssignableFrom(ApplicantRelativeDto.class)) {
                ApplicantRelativeDto applicantRelative = (ApplicantRelativeDto) item;
                ApplicantLiteDto relativeApplicantLite = applicantLiteService.findByBasicInfo(ApplicantBasicInfoDto.fromRelative(applicantRelative));
                applicantRelative.setRelativeApplicant(ApplicantDto.fromApplicantLite(relativeApplicantLite));
                applicantRelative.setApplicant(ApplicantDto.fromApplicantLite(applicantLite));

                String relativeApplicantUin = (relativeApplicantLite == null || CollectionUtils.isEmpty(relativeApplicantLite.getDigitalIds())) ? null : relativeApplicantLite.getDigitalIds().get(0).getUin();

                // if the applicant digital id and the relative applicant digital id and the applicant ritual are created then add chat contacts
                // check if digital ids are created for the applicant and the relative applicant and applicant ritual is already created.
                if (applicantUin == null || relativeApplicantUin == null || savedApplicantRitualId == null) {
                    return;
                }
                //TODO: try to get the relative applicant ritual id as it is needed to create the chat contact
                chatContactService.createApplicantRelativesChatContacts(applicantRelative, savedApplicantRitualId);
            }

            if (item.getClass().isAssignableFrom(ApplicantHealthDto.class)) {
                ApplicantHealthDto applicantHealth = (ApplicantHealthDto) item;
                Long savedApplicantHealthId = applicantHealthService.findIdByApplicantIdAndPackageReferenceNumber(applicantId, packageReferenceNumber, null, false);
                validationService.updateApplicantHealth(applicantHealth, savedApplicantHealthId, null, 0);
            }

            if (applicantHealthField != null) {
                Long applicantHealthId = applicantHealthService.findIdByApplicantIdAndPackageReferenceNumber(applicantId, packageReferenceNumber, savedApplicantRitualId, true);

                if (applicantHealthId != null) {
                    // make fields accessible
                    ReflectionUtils.makeAccessible(applicantHealthField);
                    // set the found applicant health into the object
                    applicantHealthField.set(item, ApplicantHealthDto.builder().id(applicantHealthId).build());
                }
            }
        } catch (IllegalAccessException e) {
            ReflectionUtils.handleReflectionException(e);
        }
    }

    /**
     * update company staff properties
     *
     * @param item
     * @param <T>
     */

    private <T> void updateCompanyStaffRitualData(T item) {
        if (item != null && item.getClass().isAssignableFrom(CompanyStaffRitualDto.class)) {
            CompanyStaffRitualDto companyStaffRitual = (CompanyStaffRitualDto) item;
            validationService.saveStaffRitual(companyStaffRitual);
        }
    }

    private  void updateCompanyStaffRitualData(CompanyStaffRitualDto companyStaffRitual, Long staffId) {
        validationService.saveStaffFullRitual(companyStaffRitual, staffId);
    }

}

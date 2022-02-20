/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.writer;

import com.elm.dcc.foundation.commons.core.mapper.CycleAvoidingMappingContext;
import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicant;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealthSpecialNeeds;
import com.elm.shj.admin.portal.orm.repository.*;
import com.elm.shj.admin.portal.services.applicant.*;
import com.elm.shj.admin.portal.services.card.CompanyStaffCardService;
import com.elm.shj.admin.portal.services.company.CompanyRitualSeasonService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.reader.EExcelItemReaderErrorType;
import com.elm.shj.admin.portal.services.data.validators.DataValidationResult;
import com.elm.shj.admin.portal.services.data.validators.WithGroupReferenceNumber;
import com.elm.shj.admin.portal.services.digitalid.CompanyStaffDigitalIdService;
import com.elm.shj.admin.portal.services.digitalid.DigitalIdService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
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
import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
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
    private final CompanyStaffDigitalIdService companyStaffDigitalIdService;
    private final CompanyStaffCardService companyStaffCardService;
    private final CompanyRitualSeasonService companyRitualSeasonService;
    private final ApplicantChatContactService applicantChatContactService;
    private final ApplicantRelativeService applicantRelativeService;
    private final ApplicantHealthService applicantHealthService;
    private final ApplicantEmergencyDataUploadService applicantEmergencyDataUploadService;
    private final DigitalIdService digitalIdService;
    private final ApplicantLiteService applicantLiteService;

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
        // mapper registry initialization
        mapperRegistry.put(EDataSegment.APPLICANT_DATA, Objects.requireNonNull(findMapper(ApplicantDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_RELATIVES_DATA, Objects.requireNonNull(findMapper(ApplicantRelativeDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_HEALTH_DATA, Objects.requireNonNull(findMapper(ApplicantHealthDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_IMMUNIZATION_DATA, Objects.requireNonNull(findMapper(ApplicantHealthImmunizationDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_DISEASE_DATA, Objects.requireNonNull(findMapper(ApplicantHealthDiseaseDto.class)));
        mapperRegistry.put(EDataSegment.APPLICANT_RITUAL_DATA, Objects.requireNonNull(findMapper(ApplicantRitualDto.class)));
        mapperRegistry.put(EDataSegment.STAFF_MAIN_DATA, Objects.requireNonNull(findMapper(CompanyStaffDto.class)));

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
    public <T, S> List<DataValidationResult> write(List<AbstractMap.SimpleEntry<Row, T>> items, DataSegmentDto dataSegment, long dataRequestId) {
        if (dataSegment.getId() == EDataSegment.APPLICANT_EMERGENCY_DATA.getId()) {
            JpaRepository applicantRepository = (JpaRepository) context.getBean(repositoryRegistry.get(EDataSegment.APPLICANT_DATA));
            List<DataRequestRecordDto> dataRequestRecords = new ArrayList<>();
            List<S> savedItems = new ArrayList<>();
            items.forEach(entry -> {
                ApplicantEmergencyDto emergencyDto = (ApplicantEmergencyDto) entry.getValue();
                ApplicantDto applicantDto = getApplicantFromEmergency(emergencyDto);
                ApplicantRitualEmergencyDto applicantRitualEmergencyDto = emergencyDto.getApplicantRitualEmergencyDto();

                updateNestedApplicantInfo(applicantDto);

                ApplicantDigitalIdDto applicantDigitalId = CollectionUtils.isNotEmpty(applicantDto.getDigitalIds()) ? applicantDto.getDigitalIds().get(0) : null;
                String applicantUin = applicantDigitalId != null ? applicantDigitalId.getUin() : null;

                // create a new applicant ritual
                ApplicantRitualDto applicantRitual = ApplicantRitualDto.builder().applicant(applicantDto).packageReferenceNumber(emergencyDto.getPackageReferenceNumber()).build();
                applicantRitual.setContacts(new HashSet<>(applicantDto.getContacts()));
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

                if (CollectionUtils.isNotEmpty(applicantRitual.getContacts())) {
                    applicantRitual.getContacts().forEach(ac -> {
                        ac.setApplicantRitual(applicantRitual);
                    });
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
            List savedRecords = dataRequestRecordRepository.saveAll(Objects.requireNonNull(findMapper(DataRequestRecordDto.class)).toEntityList(dataRequestRecords, mappingContext));
            //set data request record for the saved items (applicants)
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
                        //set data request record for the saved applicants ritual
                        ((JpaApplicant) s).getRituals().get(0).setDataRequestRecord(((JpaApplicant) s).getDataRequestRecord());
                        applicantRepository.save(s);
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
                        ApplicantDto applicantDto = applicantService.findByBasicInfo(applicantBasicInfoDto);
                        if (applicantDto != null) {
                            applicantChatContactService.createGroupLeaderContact(applicantDto.getDigitalIds().get(0).getUin(), groupLeader,staffApplicantGroupDto.getSeason());
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
                    staff.setId(existingStaff.getId());
                    staff.setDigitalIds(existingStaff.getDigitalIds());
                    // staff.setCompany(existingStaff.getCompany());
                    staff.setDataRequestRecord(existingStaff.getDataRequestRecord());
                    staff.setApplicantGroups(existingStaff.getApplicantGroups());
                    savedItem = (S) repository.save(mapperRegistry.get(EDataSegment.fromId(dataSegment.getId())).toEntity(staff, mappingContext));
                } else {
                    savedItem = (S) repository.save(mapperRegistry.get(EDataSegment.fromId(dataSegment.getId())).toEntity(entry.getValue(), mappingContext));
                }
            } else {
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

        return Collections.emptyList();
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

            // this case is for applicant data upload
            if (CollectionUtils.isNotEmpty(applicant.getContacts())) {
                applicant.getContacts().forEach(ac -> {
                    ac.setApplicant(applicant);
                });
            }
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
                if (savedApplicantRitualId != null) {
                    applicantRitual.setId(savedApplicantRitualId);
                    applicantRitual.setUpdateDate(new Date());
                    Long applicantPackageId = applicantPackageService.findLatestIdByApplicantUIN(applicantUin);
                    applicantRitual.setApplicantPackage(ApplicantPackageDto.builder().id(applicantPackageId).build());
                    //set applicant ritual id for applicant contacts, applicant health (if exist) and applicant relatives (if exist)
                    applicantContactService.updateContactApplicantRitual(applicantRitual.getId(), applicantId);
                    applicantHealthService.updateApplicantHealthApplicantRitual(applicantRitual.getId(), applicantId, applicantRitual.getPackageReferenceNumber());
                    applicantRelativeService.updateApplicantRelativeApplicantRitual(applicantRitual.getId(), applicantId, applicantRitual.getPackageReferenceNumber());
                } else {
                    // applicant ritual not created yet, check if digital id is exists,
                    // if yes then create a new applicant package and link it with the applicant ritual
                    if (applicantUin != null && !applicantUin.isEmpty()) {
                        ApplicantPackageDto createdApplicantPackage = applicantPackageService.createApplicantPackage(applicantRitual.getPackageReferenceNumber(), Long.parseLong(applicantUin), null, null);
                        applicantRitual.setApplicantPackage(createdApplicantPackage);
                    }
                }
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
                applicantChatContactService.createApplicantRelativesChatContacts(applicantRelative, savedApplicantRitualId);
            }

            if (item.getClass().isAssignableFrom(ApplicantHealthDto.class)) {
                ApplicantHealthDto applicantHealth = (ApplicantHealthDto) item;
                Long savedApplicantHealthId = applicantHealthService.findIdByApplicantIdAndPackageReferenceNumber(applicantId, packageReferenceNumber, null, false);
                if (savedApplicantHealthId != null) {
                    applicantHealth.setId(savedApplicantHealthId);
                    applicantHealth.setUpdateDate(new Date());
                    if (CollectionUtils.isNotEmpty(applicantHealth.getSpecialNeeds())) {
                        // get the special needs and if it is a list then create a list of special needs dtos
                        List<ApplicantHealthSpecialNeedsDto> applicantHealthSpecialNeeds = Arrays.stream(applicantHealth.getSpecialNeeds().get(0).getSpecialNeedTypeCode().split(",")).map(sn ->
                                ApplicantHealthSpecialNeedsDto.builder().applicantHealth(ApplicantHealthDto.builder().id(applicantHealth.getId()).build()).specialNeedTypeCode(sn).build()
                        ).collect(Collectors.toList());
                        IGenericMapper<ApplicantHealthSpecialNeedsDto, JpaApplicantHealthSpecialNeeds> mapper = findMapper(ApplicantHealthSpecialNeedsDto.class);
                        applicantHealthSpecialNeedsRepository.saveAll(mapper.toEntityList(applicantHealthSpecialNeeds, mappingContext));
                        applicantHealth.setSpecialNeeds(null);
                    }
                } else {
                    if (CollectionUtils.isNotEmpty(applicantHealth.getSpecialNeeds())) {
                        // get the special needs and if it is a list then create a list of special needs dtos
                        applicantHealth.setSpecialNeeds(Arrays.stream(applicantHealth.getSpecialNeeds().get(0).getSpecialNeedTypeCode().split(",")).map(sn ->
                                ApplicantHealthSpecialNeedsDto.builder().applicantHealth(applicantHealth).specialNeedTypeCode(sn).build()
                        ).collect(Collectors.toList()));
                    }
                }
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

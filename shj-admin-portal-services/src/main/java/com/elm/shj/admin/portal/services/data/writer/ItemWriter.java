/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.writer;

import com.elm.dcc.foundation.commons.core.mapper.CycleAvoidingMappingContext;
import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicant;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealth;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealthSpecialNeeds;
import com.elm.shj.admin.portal.orm.repository.*;
import com.elm.shj.admin.portal.services.applicant.*;
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

    private final CycleAvoidingMappingContext mappingContext;
    private final ApplicationContext context;
    private final ApplicantService applicantService;
    private final ApplicantHealthRepository applicantHealthRepository;
    private final ApplicantHealthSpecialNeedsRepository applicantHealthSpecialNeedsRepository;
    private final DataRequestRecordRepository dataRequestRecordRepository;
    private final DigitalIdService digitalIdService;
    private final ApplicantPackageService applicantPackageService;
    private final RitualPackageService ritualPackageService;
    private final ApplicantPackageTransportationService applicantPackageTransportationService;
    private final GroupApplicantListService groupApplicantListService;
    private final ApplicantRitualService applicantRitualService;
    private final ApplicantContactService applicantContactService;

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
    public <T, S> void write(List<AbstractMap.SimpleEntry<Row, T>> items, DataSegmentDto dataSegment, long dataRequestId) {
        if(dataSegment.getId()==7){
            JpaRepository applicantRepository = (JpaRepository) context.getBean(repositoryRegistry.get(EDataSegment.APPLICANT_DATA));
            List<DataRequestRecordDto> dataRequestRecords = new ArrayList<>();
            List<S> savedItems = new ArrayList<>();
            items.forEach(entry ->{
                ApplicantEmergencyDto emergencyDto = (ApplicantEmergencyDto) entry.getValue();
                ApplicantDto applicantDto = getApplicantFromEmergency(emergencyDto);
                ApplicantRitualEmergencyDto applicantRitualEmergencyDto = emergencyDto.getApplicantRitualEmergencyDto();
                updateNestedApplicantInfo(applicantDto, Collections.emptyList(), applicantRitualEmergencyDto.getBusNumber(), applicantRitualEmergencyDto.getSeatNumber());
                updateApplicantRitualInfo(applicantDto, applicantRitualEmergencyDto);
                S savedApplicant = (S) applicantRepository.save(mapperRegistry.get(EDataSegment.APPLICANT_DATA).toEntity(applicantDto, mappingContext));
                savedItems.add(savedApplicant);
                savedItems.forEach(item -> {
                    try {
                        dataRequestRecords.add(DataRequestRecordDto.builder()
                                .createDataRequestId(dataRequestId)
                                .lastUpdateDataRequestId(dataRequestId)
                                .createDataRequestRowNum((long) entry.getKey().getRowNum())
                                .lastUpdateDataRequestRowNum((long) entry.getKey().getRowNum())
                                .itemId(Long.parseLong(BeanUtils.getProperty(item, "id")))
                                .build());
                    }catch (Exception e){
                        ReflectionUtils.handleReflectionException(e);
                    }
                });

            });
            List savedRecords = dataRequestRecordRepository.saveAll(Objects.requireNonNull(findMapper(DataRequestRecordDto.class)).toEntityList(dataRequestRecords, mappingContext));
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
            savedItems.forEach(item -> {
                if (item.getClass().isAssignableFrom(JpaApplicant.class)){
                    ((JpaApplicant) item).getRituals().get(0).setDataRequestRecord(((JpaApplicant) item).getDataRequestRecord());
                    applicantRepository.save(item);
                }

            });
        }else {
            if (dataSegment.getId() < 8) {
                // update applicant related attributes
                items.forEach(entry -> updateNestedApplicantInfo(entry.getValue(), items.stream().map(AbstractMap.SimpleEntry::getValue).collect(Collectors.toList())));

            }

            // save all items and build data records
            if (dataSegment.getId() != 9) {
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
        }
    }

    /**
     * update related applicant ritual from applicant emergency
     *
     * @param applicantDto
     * @param applicantRitualEmergencyDto
     */

    private <T> void updateApplicantRitualInfo(ApplicantDto applicantDto, ApplicantRitualEmergencyDto applicantRitualEmergencyDto) {
        applicantDto.getRituals().get(applicantDto.getRituals().size() - 1).setBorderNumber(applicantRitualEmergencyDto.getBorderNumber());
        applicantDto.getRituals().get(applicantDto.getRituals().size() - 1).setVisaNumber(applicantRitualEmergencyDto.getVisaNumber());
        applicantDto.getRituals().get(applicantDto.getRituals().size() - 1).setPermitNumber(applicantRitualEmergencyDto.getPermitNumber());
        applicantDto.getRituals().get(applicantDto.getRituals().size() - 1).setInsuranceNumber(applicantRitualEmergencyDto.getInsuranceNumber());
        applicantDto.getRituals().get(applicantDto.getRituals().size() - 1).setBusNumber(applicantRitualEmergencyDto.getBusNumber());
        applicantDto.getRituals().get(applicantDto.getRituals().size() - 1).setSeatNumber(applicantRitualEmergencyDto.getSeatNumber());
        applicantDto.getRituals().get(applicantDto.getRituals().size() - 1).setGroupReferenceNumber(applicantRitualEmergencyDto.getGroupReferenceNumber());

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
    private <T> void updateNestedApplicantInfo(T item, List<T> bulkList, String... busAndSeatNumbers) {
        // Special treatment for ApplicantDto and contact info as they come in the same sheet
        if (item != null && item.getClass().isAssignableFrom(ApplicantDto.class)) {
            ApplicantDto applicant = (ApplicantDto) item;
            if (applicant.getDateOfBirthHijri() == null || applicant.getDateOfBirthHijri() == 0) {
                Calendar cl = Calendar.getInstance();
                cl.setTime(applicant.getDateOfBirthGregorian());
                HijrahDate islamyDate = HijrahChronology.INSTANCE.date(LocalDate.of(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH) + 1, cl.get(Calendar.DATE)));
                applicant.setDateOfBirthHijri(Long.parseLong(islamyDate.toString().substring(islamyDate.toString().indexOf("AH") + 3).replace("-", "")));
            }
            ApplicantDto existingApplicant = applicantService.findByBasicInfo(ApplicantBasicInfoDto.fromApplicant(applicant));
            // if record exists already in DB we need to update it
            if (existingApplicant != null) {
                applicant.setId(existingApplicant.getId());
                applicant.setDigitalIds(existingApplicant.getDigitalIds());
                applicant.setRituals(applicantRitualService.findAllByApplicantId(existingApplicant.getId()));
                applicant.setRelatives(existingApplicant.getRelatives());
                applicant.setCreationDate(existingApplicant.getCreationDate());

            } else {
                applicant.setDigitalIds(Arrays.asList(ApplicantDigitalIdDto.builder().uin(digitalIdService.generate(applicant)).applicant(applicant).build()));
                applicant.getDigitalIds().get(0).setStatusCode("VALID");
            }
            Long applicantUin = Long.parseLong(applicant.getDigitalIds().get(0).getUin());

            // this case is for emergency data to insert bus number and seat number in applicant package transportation
            if (busAndSeatNumbers.length > 0) {
                ApplicantRitualDto applicantRitualDto = addRitualPackageToApplicant(applicant, applicantUin, applicant.getPackageReferenceNumber(), busAndSeatNumbers[0], busAndSeatNumbers[1]);
                if (existingApplicant != null) {
                    applicant.getRituals().add(applicantRitualDto);
                } else {
                    applicant.setRituals(Arrays.asList(applicantRitualDto));
                }
                if (CollectionUtils.isNotEmpty(applicant.getContacts())) {
                    applicant.getContacts().forEach(sn -> {
                        sn.setApplicant(applicant);
                        sn.setApplicantRitual(applicantRitualDto);
                    });
                }
                // digital id will bw generated automatically by the scheduler

            }
            // this case is for applicant data upload
            else {
                ApplicantRitualDto applicantRitualDto = addRitualPackageToApplicant(applicant, applicantUin, applicant.getPackageReferenceNumber(), null, null);
                if (existingApplicant != null) {
                    applicant.getRituals().add(applicantRitualDto);
                } else {
                    applicant.setRituals(Arrays.asList(applicantRitualDto));
                }
                if (CollectionUtils.isNotEmpty(applicant.getContacts())) {
                    applicant.getContacts().forEach(sn -> {
                        sn.setApplicant(applicant);
                        sn.setApplicantRitual(applicantRitualDto);
                    });
                }
            }
            //add existing contacts to new contact
            if (existingApplicant != null) {
                applicant.getContacts().addAll(applicantContactService.findByApplicantId(existingApplicant.getId()));
            }
        }

        // special treatment for applicant relative
        if (item != null && item.getClass().isAssignableFrom(ApplicantRelativeDto.class)) {
            ApplicantRelativeDto applicantRelative = (ApplicantRelativeDto) item;
            applicantRelative.setRelativeApplicant(applicantService.findByBasicInfo(ApplicantBasicInfoDto.fromRelative(applicantRelative)));
        }

        Field applicantBasicInfoField = ReflectionUtils.findField(item.getClass(), "applicantBasicInfo");
        Field applicantHealthField = ReflectionUtils.findField(item.getClass(), "applicantHealth");
        Field applicantField = ReflectionUtils.findField(item.getClass(), "applicant");
        Field packageReferenceNumberField = ReflectionUtils.findField(item.getClass(), "packageReferenceNumber");
        Field applicantRitualField = ReflectionUtils.findField(item.getClass(), "applicantRitual");

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


                if (applicantField != null) {
                    // make fields accessible
                    ReflectionUtils.makeAccessible(applicantField);
                    // set the found applicant into the object
                    applicantField.set(item, applicant);

                }

                if (packageReferenceNumberField != null) {

                    String seatNumber = null;
                    String busNumber = null;

                    Field busNumberField = ReflectionUtils.findField(item.getClass(), "busNumber");
                    Field seatNumberField = ReflectionUtils.findField(item.getClass(), "seatNumber");

                    if (busNumberField != null) {
                        ReflectionUtils.makeAccessible(busNumberField);
                        busNumber = (String) busNumberField.get(item);
                    }

                    if (seatNumberField != null) {
                        ReflectionUtils.makeAccessible(seatNumberField);
                        seatNumber = (String) seatNumberField.get(item);
                    }

                    ReflectionUtils.makeAccessible(packageReferenceNumberField);
                    String packageReferenceNumber = (String) packageReferenceNumberField.get(item);
                    Long applicantUin = Long.parseLong(applicant.getDigitalIds().get(0).getUin());
                    ApplicantRitualDto generatedApplicantRitual = addRitualPackageToApplicant(applicant, applicantUin, packageReferenceNumber, busNumber, seatNumber);

                    if (item.getClass().isAssignableFrom(ApplicantRitualDto.class)) {

                        ApplicantRitualDto applicantRitual = (ApplicantRitualDto) item;
                        applicantRitual.setId(generatedApplicantRitual.getId());
                        applicantRitual.setApplicantPackage(generatedApplicantRitual.getApplicantPackage());
                        applicantRitual.setCreationDate(generatedApplicantRitual.getCreationDate());

                        if (applicantRitual.getGroupReferenceNumber() != null) {
                            groupApplicantListService.registerUserToGroup(applicantUin.toString(), applicantRitual.getGroupReferenceNumber());
                        }
                    }

                    if (applicantRitualField != null) {
                        ReflectionUtils.makeAccessible(applicantRitualField);
                        applicantRitualField.set(item, generatedApplicantRitual);
                    }

                    if (item.getClass().isAssignableFrom(ApplicantHealthDto.class)) {
                        ApplicantHealthDto curApplicantHealth = (ApplicantHealthDto) item;
                        JpaApplicantHealth jpaApplicantHealth = applicantHealthRepository.findByUinAndRitualId(applicantUin.toString(), generatedApplicantRitual.getId());
                        if (jpaApplicantHealth != null) {
                            curApplicantHealth.setId(jpaApplicantHealth.getId());
                            if (CollectionUtils.isNotEmpty(curApplicantHealth.getSpecialNeeds())) {
                                // get the special needs and if it is a list then create a list of special needs dtos
                                List<ApplicantHealthSpecialNeedsDto> applicantHealthSpecialNeeds = Arrays.stream(curApplicantHealth.getSpecialNeeds().get(0).getSpecialNeedTypeCode().split(",")).map(sn ->
                                        ApplicantHealthSpecialNeedsDto.builder().applicantHealth(ApplicantHealthDto.builder().id(curApplicantHealth.getId()).build()).specialNeedTypeCode(sn).build()
                                ).collect(Collectors.toList());
                                IGenericMapper<ApplicantHealthSpecialNeedsDto, JpaApplicantHealthSpecialNeeds> mapper = findMapper(ApplicantHealthSpecialNeedsDto.class);
                                applicantHealthSpecialNeedsRepository.saveAll(mapper.toEntityList(applicantHealthSpecialNeeds, mappingContext));
                                curApplicantHealth.setSpecialNeeds(null);
                                curApplicantHealth.setCreationDate(jpaApplicantHealth.getCreationDate());
                            }

                        } else {
                            if (CollectionUtils.isNotEmpty(curApplicantHealth.getSpecialNeeds())) {
                                // get the special needs and if it is a list then create a list of special needs dtos
                                curApplicantHealth.setSpecialNeeds(Arrays.stream(curApplicantHealth.getSpecialNeeds().get(0).getSpecialNeedTypeCode().split(",")).map(sn ->
                                        ApplicantHealthSpecialNeedsDto.builder().applicantHealth(curApplicantHealth).specialNeedTypeCode(sn).build()
                                ).collect(Collectors.toList()));
                            }
                        }

                    }
                    if (applicantHealthField != null) {
                        ApplicantHealthDto applicantHealth = null;
                        JpaApplicantHealth jpaApplicantHealth = applicantHealthRepository.findByUinAndRitualId(applicantUin.toString(), generatedApplicantRitual.getId());

                        IGenericMapper<ApplicantHealthDto, JpaApplicantHealth> mapper = findMapper(ApplicantHealthDto.class);
                        if (jpaApplicantHealth != null) {
                            applicantHealth = mapper.fromEntity(jpaApplicantHealth, mappingContext);

                        } else {
                            applicantHealth = ApplicantHealthDto.builder().applicantRitual(generatedApplicantRitual).applicant(applicant).build();
                            applicantHealth = mapper.fromEntity(applicantHealthRepository.save(mapper.toEntity(applicantHealth, mappingContext)), mappingContext);
                        }
                        // make fields accessible
                        ReflectionUtils.makeAccessible(applicantHealthField);

                        // set the found applicant health into the object
                        applicantHealthField.set(item, applicantHealth);
                    }
                }

            }
        } catch (IllegalAccessException e) {
            ReflectionUtils.handleReflectionException(e);
        }
    }

    private ApplicantRitualDto addRitualPackageToApplicant(ApplicantDto applicant, Long applicantUin, String packageReferenceNumber, String busNumber, String seatNumber) {

        ApplicantPackageDto applicantPackageDto = applicantPackageService.findApplicantPackageByUinAndReferenceNumber(applicantUin, packageReferenceNumber);
        ApplicantRitualDto applicantRitualDto = null;

        if (applicantPackageDto != null) {
            if (CollectionUtils.isNotEmpty(applicantPackageDto.getApplicantPackageTransportations()) && (busNumber != null || seatNumber != null)) {
                ApplicantPackageDto finalApplicantPackageDto1 = applicantPackageDto;
//                applicantPackageDto.getApplicantPackageTransportations().forEach(pt->{
//                    pt.setSeatNumber(seatNumber);
//                    pt.setVehicleNumber(busNumber);
//                    pt.setApplicantPackage(ApplicantPackageDto.builder().id(finalApplicantPackageDto1.getId()).build());
//
//                });

                List<ApplicantPackageTransportationDto> applicantPackageTransportations = applicantPackageDto.getApplicantPackageTransportations().stream().map(pt ->
                        ApplicantPackageTransportationDto.builder().id(pt.getId()).creationDate(pt.getCreationDate()).packageTransportation(PackageTransportationDto.builder().id(pt.getPackageTransportation().getId()).build())
                                .applicantPackage(ApplicantPackageDto.builder().id(finalApplicantPackageDto1.getId()).build()).vehicleNumber(busNumber).seatNumber(seatNumber).build()
                ).collect(Collectors.toList());

                applicantPackageTransportationService.saveAll(applicantPackageTransportations);
            }
            if (applicantPackageDto.getApplicantRituals() != null && !applicantPackageDto.getApplicantRituals().isEmpty()) {
                applicantRitualDto = applicantPackageDto.getApplicantRituals().get(0);
            } else {
                applicantRitualDto = ApplicantRitualDto.builder().applicantPackage(applicantPackageDto).applicant(applicant).build();
            }
        } else {
            RitualPackageDto ritualPackage = ritualPackageService.findRitualPackageByReferenceNumber(packageReferenceNumber);
            List<ApplicantPackageHousingDto> applicantPackageHousings = null;
            List<ApplicantPackageCateringDto> applicantPackageCaterings = new ArrayList<>();
            List<ApplicantPackageTransportationDto> applicantPackageTransportations = null;

            applicantPackageDto = ApplicantPackageDto.builder().applicantUin(applicantUin).ritualPackage(RitualPackageDto.builder().id(ritualPackage.getId()).build()).build();

            ApplicantPackageDto finalApplicantPackageDto = applicantPackageDto;

            if (CollectionUtils.isNotEmpty(ritualPackage.getPackageHousings())) {
                applicantPackageHousings = ritualPackage.getPackageHousings().stream().map(ph ->
                        ApplicantPackageHousingDto.builder().packageHousing(PackageHousingDto.builder().id(ph.getId()).build()).applicantPackage(finalApplicantPackageDto).build()
                ).collect(Collectors.toList());

                ritualPackage.getPackageHousings().forEach(ph -> {
                    if (CollectionUtils.isNotEmpty(ph.getPackageCatering())) {
                        applicantPackageCaterings.addAll(ph.getPackageCatering().stream().map(pc ->
                                ApplicantPackageCateringDto.builder().packageCatering(PackageCateringDto.builder().id(pc.getId()).build()).applicantPackage(finalApplicantPackageDto).build()
                        ).collect(Collectors.toList()));
                    }
                });
            }

            if (CollectionUtils.isNotEmpty(ritualPackage.getPackageTransportations())) {
                applicantPackageTransportations = ritualPackage.getPackageTransportations().stream().map(pt ->
                        ApplicantPackageTransportationDto.builder().packageTransportation(PackageTransportationDto.builder().id(pt.getId()).build())
                                .applicantPackage(finalApplicantPackageDto).vehicleNumber(busNumber).seatNumber(seatNumber).build()
                ).collect(Collectors.toList());
            }
            finalApplicantPackageDto.setApplicantPackageCaterings(applicantPackageCaterings);
            finalApplicantPackageDto.setApplicantPackageHousings(applicantPackageHousings);
            finalApplicantPackageDto.setApplicantPackageTransportations(applicantPackageTransportations);

            applicantPackageDto = applicantPackageService.save(finalApplicantPackageDto);

            applicantRitualDto = ApplicantRitualDto.builder().applicantPackage(applicantPackageDto).applicant(applicant).build();
        }

        return applicantRitualDto;
    }

    /**
     * update company staff properties
     *
     * @param item
     * @param <T>
     */

    private <T> void updateCompanyStaffRitualData(T item) {
        if (item != null && item.getClass().isAssignableFrom(CompanyStaffRitualDto.class)) {
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

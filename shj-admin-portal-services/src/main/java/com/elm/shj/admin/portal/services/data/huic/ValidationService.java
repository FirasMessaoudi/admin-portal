/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.huic;

import com.elm.dcc.foundation.commons.core.mapper.CycleAvoidingMappingContext;
import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.*;
import com.elm.shj.admin.portal.orm.repository.*;
import com.elm.shj.admin.portal.services.applicant.*;
import com.elm.shj.admin.portal.services.card.CompanyStaffCardService;
import com.elm.shj.admin.portal.services.company.CompanyRitualSeasonBasicService;
import com.elm.shj.admin.portal.services.company.CompanyRitualSeasonService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.data.validators.CheckFirst;
import com.elm.shj.admin.portal.services.data.validators.CheckSecond;
import com.elm.shj.admin.portal.services.digitalid.CompanyStaffDigitalIdBasicService;
import com.elm.shj.admin.portal.services.digitalid.CompanyStaffDigitalIdService;
import com.elm.shj.admin.portal.services.digitalid.DigitalIdService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
import com.elm.shj.admin.portal.services.ritual.RitualSeasonService;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.time.chrono.HijrahEra;
import java.time.chrono.IsoChronology;
import java.time.temporal.ChronoField;
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
    private final ApplicantBasicService applicantBasicService;
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
    private final CompanyStaffDigitalIdBasicService companyStaffDigitalIdBasicService;
    private final CompanyStaffCardService companyStaffCardService;
    private final CompanyRitualSeasonService companyRitualSeasonService;
    private final CompanyRitualSeasonBasicService companyRitualSeasonBasicService;
    private final RitualSeasonRepository ritualSeasonRepository;
    private final HousingMasterService housingMasterService;
    private final CompanyRepository companyRepository;
    private final CompanyRitualSeasonRepository companyRitualSeasonRepository;
    private final RitualPackageService ritualPackageService;
    private final RitualPackageBasicWithDetailsService ritualPackageBasicWithDetailsService;
    private final RitualSeasonService ritualSeasonService;
    private final ApplicantContactService applicantContactService;
    private static final String ARABIC_REGEX = "^[\\p{InArabic}\\s-_]+$";
    private static final String LATIN_REGEX = "^[\\p{IsLatin}\\s-_]+$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";


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
                if (items.get(i).getClass().isAssignableFrom(HuicApplicantMainData.class)) {
                    saveApplicantsMainData((HuicApplicantMainData) items.get(i), errorResponses, i);
                }
                if (items.get(i).getClass().isAssignableFrom(HuicApplicantRitual.class)) {
                    saveApplicantRitual((HuicApplicantRitual) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(ApplicantHealthDto.class)) {
                    saveApplicantHealth((ApplicantHealthDto) items.get(i), errorResponses, i);
                }
                if (items.get(i).getClass().isAssignableFrom(HuicApplicantRelative.class)) {
                    saveApplicantRelative((HuicApplicantRelative) items.get(i), errorResponses, i);
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
                if (items.get(i).getClass().isAssignableFrom(HuicRitualSeason.class)) {
                    saveRitualSeasons((HuicRitualSeason) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(HuicHousingMaster.class)) {
                    saveHousingMasterData((HuicHousingMaster) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(HuicCompany.class)) {
                    saveCompanies((HuicCompany) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(HuicPlannedPackage.class)) {
                    savePlannedPackages((HuicPlannedPackage) items.get(i));
                }
                if (items.get(i).getClass().isAssignableFrom(HuicArrivalData.class)) {
                    saveArrivalData((HuicArrivalData) items.get(i));
                }
            }

        }


        return errorResponses;
    }

    private void saveArrivalData(HuicArrivalData huicArrivalData) {
        ApplicantLiteDto applicantLite = applicantLiteService.findByBasicInfo(huicArrivalData.getIdNumber() != null ? huicArrivalData.getIdNumber().toString() : null, huicArrivalData.getPassportNo(), huicArrivalData.getNationality().toString());
        if (applicantLite == null) {
            return;
        }
        ApplicantPackageDto applicantPackageDto = applicantPackageService.findJpaApplicantPackageByApplicantId(applicantLite.getId());
        applicantPackageDto.setArrivalCity(EArrivalCity.fromId(huicArrivalData.getArrivalCity()).name());
        applicantPackageDto.setArrivalDate(huicArrivalData.getArrivalDateTime());
        applicantPackageService.save(applicantPackageDto);

    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    void savePlannedPackages(HuicPlannedPackage plannedPackage) {
        log.info("Start savePlannedPackages for {} package reference number.", plannedPackage.getPackageRefNumber());
        RitualPackageBasicWithDetailsDto ritualPackageDto = RitualPackageBasicWithDetailsDto.builder()
                .referenceNumber(plannedPackage.getPackageRefNumber() + "")
                .packageNameAr(plannedPackage.getPackageNameArabic())
                .packageNameEn(plannedPackage.getPackageNameEnglish())
                .packageTypeCode(EPackageType.fromId(plannedPackage.getPackageTypeCode()).name())
                .startDate(DateUtils.toGregorian(plannedPackage.getPackageStartDate()))
                .endDate(DateUtils.toGregorian(plannedPackage.getPackageEndDate()))
                .hajjOfficeMakkah(plannedPackage.getHajjOfficeMakkah() + "")
                .hajjOfficeMadina(plannedPackage.getHajjOfficeMadina() + "")
                .build();

        CompanyRitualSeasonBasicDto companyRitualSeasonDto = companyRitualSeasonBasicService.getLatestCompanyRitualSeasonByRitualSeason(plannedPackage.getCompanyRefCode() + "_" + ECompanyType.fromId(plannedPackage.getCompanyTypeCode()).name(), ERitualType.fromId(plannedPackage.getRitualTypeCode()).name(), plannedPackage.getSeasonYear());
        if (companyRitualSeasonDto != null) {
            ritualPackageDto.setCompanyRitualSeason(companyRitualSeasonDto);
        }

        //check if ritual package is exist
        Long existingRitualPackageId = ritualPackageService.findIdByReferenceAndRitualTypeAndSeason(plannedPackage.getPackageRefNumber().toString(), ERitualType.fromId(plannedPackage.getRitualTypeCode()).name(), plannedPackage.getSeasonYear());
        if (existingRitualPackageId != null) {
            ritualPackageDto.setId(existingRitualPackageId);
        }

        //get the package transportation
        if (plannedPackage.getPackageTransportations() != null) {
            log.info("Start creating the package transportation for {} package reference number.", plannedPackage.getPackageRefNumber());
            List<PackageTransportationBasicDto> packageTransportationBasicList = new ArrayList<>();
            plannedPackage.getPackageTransportations().forEach(huicPackageTransportation -> {
                PackageTransportationBasicDto packageTransportationDto = PackageTransportationBasicDto.builder()
                        .typeCode(ETransportationType.fromId(huicPackageTransportation.getTypeCode()).name())
                        .locationFromNameAr(huicPackageTransportation.getLocationFromNameAr())
                        .locationFromNameEn(huicPackageTransportation.getLocationFromNameEn())
                        .locationToNameAr(huicPackageTransportation.getLocationToNameAr())
                        .locationToNameEn(huicPackageTransportation.getLocationToNameEn())
                        .validityStart(DateUtils.toGregorian(huicPackageTransportation.getValidityStart()))
                        .validityEnd(DateUtils.toGregorian(huicPackageTransportation.getValidityEnd()))
                        .routeDetails(huicPackageTransportation.getRouteDetails())
                        .ritualPackage(existingRitualPackageId == null ? ritualPackageDto : RitualPackageBasicWithDetailsDto.builder().id(existingRitualPackageId).build())
                        .build();
                packageTransportationBasicList.add(packageTransportationDto);
            });
            ritualPackageDto.setPackageTransportations(packageTransportationBasicList);
        }

        //get the package housing
        if (plannedPackage.getPackageHousings() != null) {
            log.info("Start creating the package housing for {} package reference number.", plannedPackage.getPackageRefNumber());
            List<PackageHousingBasicDto> packageHousingBasicList = new ArrayList<>();
            plannedPackage.getPackageHousings().forEach(huicPackageHousing -> {
                HousingMasterDto housingMaster = housingMasterService.findLatestHousingMasterByReferenceCode(huicPackageHousing.getRefNumber().toString());
                PackageHousingBasicDto packageHousing = PackageHousingBasicDto.builder()
                        .typeCode(housingMaster.getTypeCode())
                        .siteCode(housingMaster.getSiteCode())
                        .referenceNumber(huicPackageHousing.getRefNumber().toString())
                        .categoryCode(housingMaster.getCategoryCode())
                        .locationNameAr(housingMaster.getLocationNameAr())
                        .locationNameEn(housingMaster.getLocationNameEn())
                        .validityStart(huicPackageHousing.getValidityStart())
                        .validityEnd(huicPackageHousing.getValidityEnd())
                        .addressAr(housingMaster.getAddressAr())
                        .addressEn(housingMaster.getAddressEn())
                        .isDefault(huicPackageHousing.isDefault())
                        .lat(housingMaster.getLat())
                        .lng(housingMaster.getLng())
                        .ritualPackage(existingRitualPackageId == null ? ritualPackageDto : RitualPackageBasicWithDetailsDto.builder().id(existingRitualPackageId).build())
                        .build();
                //get the package housing catering
                if (huicPackageHousing.getPackageCaterings() != null) {
                    log.info("Start creating the package housing catering for {} package reference number and {} housing master reference code.", plannedPackage.getPackageRefNumber(), housingMaster.getHousingReferenceCode());
                    List<PackageCateringBasicDto> packageCateringBasicList = new ArrayList<>();
                    huicPackageHousing.getPackageCaterings().forEach(huicPackageCatering -> {
                        PackageCateringBasicDto packageCateringDto = PackageCateringBasicDto.builder()
                                .mealCode(huicPackageCatering.getMealCode())
                                .mealTimeCode(EMealTime.fromId(huicPackageCatering.getMealTime()).name())
                                .mealTypeCode(EMealType.fromId(huicPackageCatering.getMealType()).name())
                                .descriptionAr(huicPackageCatering.getOptionDescriptionAr())
                                .descriptionEn(huicPackageCatering.getOptionDescriptionEn())
                                .isDefault(huicPackageCatering.isDefault())
                                .packageHousing(packageHousing)
                                .build();
                        packageCateringBasicList.add(packageCateringDto);
                    });
                    packageHousing.setPackageCatering(packageCateringBasicList);
                }
                packageHousingBasicList.add(packageHousing);
            });
            ritualPackageDto.setPackageHousings(packageHousingBasicList);
        }
        RitualPackageBasicWithDetailsDto savedRitualPackageBasicWithDetails = ritualPackageBasicWithDetailsService.save(ritualPackageDto);
        Long savedRitualPackageId = null;
        if (savedRitualPackageBasicWithDetails != null) {
            savedRitualPackageId = savedRitualPackageBasicWithDetails.getId();
        }
        log.info("Finish savePlannedPackages for {} package reference number and ritual package with {} id saved.", plannedPackage.getPackageRefNumber(), savedRitualPackageId);
    }

    private void saveCompanies(HuicCompany huicCompany) {
        Long existingCompanyId = companyRepository.findIdByCode(huicCompany.getCompanyRefCode() + "_" + ECompanyType.fromId(huicCompany.getCompanyTypeCode()).name());
        // in case of no establishment and ritual type is internal then link it with establishment 8, otherwise no business so far.
        CompanyDto companyDto = CompanyDto.builder()
                .code(huicCompany.getCompanyRefCode() + "_" + ECompanyType.fromId(huicCompany.getCompanyTypeCode()).name())
                .labelAr(huicCompany.getCompanyNameAr())
                .labelEn(huicCompany.getCompanyNameEn())
                .missionRefCode(huicCompany.getMissionId())
                .contactNumber(huicCompany.getCompanyContactNumber() + "")
                .website(huicCompany.getWebsite())
                .email(huicCompany.getCompanyEmail() != null ? huicCompany.getCompanyEmail().matches(EMAIL_REGEX) ? huicCompany.getCompanyEmail() : null : null)
                .moiNumber(huicCompany.getMoiNumber() + "")
                .crNumber(huicCompany.getCrNumber() + "")
                .typeCode(ECompanyType.fromId(huicCompany.getCompanyTypeCode()).name())
                .countryCode(huicCompany.getCountry() + "")
                .establishmentRefCode((huicCompany.getEstablishmentId() == null && huicCompany.getRitualTypeCode().intValue() == ERitualType.INTERNAL_HAJJ.getId()) ? 8 : huicCompany.getEstablishmentId())
                .build();
        if (existingCompanyId != null) {
            companyDto.setId(existingCompanyId);
            List<CompanyRitualSeasonDto> companyRitualSeasonDtos = companyRitualSeasonService.findByCompanyId(existingCompanyId);
            if (!companyRitualSeasonDtos.isEmpty()) {
                companyDto.setCompanyRitualSeasons(companyRitualSeasonDtos);
            }
        }

        JpaCompany savedCompany = companyRepository.save((JpaCompany) findMapper(CompanyDto.class).toEntity(companyDto, mappingContext));
        Optional<JpaRitualSeason> ritualSeason = ritualSeasonRepository.findByRitualTypeCodeAndSeasonYear(ERitualType.fromId(huicCompany.getRitualTypeCode()).name(), huicCompany.getSeasonYear());
        if (ritualSeason.isPresent()) {
            boolean existsByCompanyAndRitual = false;
            if (existingCompanyId != null) {
                existsByCompanyAndRitual = companyRitualSeasonRepository.existsByCompanyIdAndRitualSeasonId(existingCompanyId, ritualSeason.get().getId());
            }
            if (!existsByCompanyAndRitual) {
                JpaCompanyRitualSeason companyRitualSeason = new JpaCompanyRitualSeason();
                companyRitualSeason.setCompany(savedCompany);
                companyRitualSeason.setRitualSeason(ritualSeason.get());
                companyRitualSeason.setActive(true);
                companyRitualSeason.setSeasonStart(ritualSeason.get().getSeasonStart());
                companyRitualSeason.setSeasonEnd(ritualSeason.get().getSeasonEnd());
                companyRitualSeasonRepository.save(companyRitualSeason);
                // if ritual is external create another company ritual season for courtesy hajj
                if (huicCompany.getRitualTypeCode() == 1) {
                    JpaCompanyRitualSeason companyRitualSeasonCourtsey = new JpaCompanyRitualSeason();
                    Optional<JpaRitualSeason> ritualSeasonCourtesy = ritualSeasonRepository.findByRitualTypeCodeAndSeasonYear(ERitualType.fromId(3).name(), huicCompany.getSeasonYear());
                    if (ritualSeasonCourtesy.isPresent()) {
                        companyRitualSeasonCourtsey.setRitualSeason(ritualSeasonCourtesy.get());
                        companyRitualSeasonCourtsey.setSeasonStart(ritualSeasonCourtesy.get().getSeasonStart());
                        companyRitualSeasonCourtsey.setSeasonEnd(ritualSeasonCourtesy.get().getSeasonEnd());
                    } else {
                        JpaRitualSeason newRitual = new JpaRitualSeason();
                        newRitual.setActive(true);
                        newRitual.setRitualTypeCode(ERitualType.fromId(3).name());
                        newRitual.setSeasonYear(huicCompany.getSeasonYear());
                        newRitual.setSeasonStart(ritualSeason.get().getSeasonStart());
                        newRitual.setSeasonEnd(ritualSeason.get().getSeasonEnd());
                        JpaRitualSeason saveRitualSeason = ritualSeasonRepository.save(newRitual);
                        companyRitualSeasonCourtsey.setRitualSeason(saveRitualSeason);
                        companyRitualSeasonCourtsey.setSeasonEnd(saveRitualSeason.getSeasonEnd());
                        companyRitualSeasonCourtsey.setSeasonStart(saveRitualSeason.getSeasonStart());
                    }
                    companyRitualSeasonCourtsey.setCompany(savedCompany);
                    companyRitualSeasonCourtsey.setActive(true);

                    companyRitualSeasonRepository.save(companyRitualSeasonCourtsey);
                }

            }
        }

    }

    private void saveHousingMasterData(HuicHousingMaster huicHousingMaster) {
        //TODO: replace this by findId only method, no need for the whole object.
        HousingMasterDto existingHousingMaster = housingMasterService.findLatestHousingMasterByReferenceCode(huicHousingMaster.getHouseRefNumber() + "");
        HousingMasterDto housingMasterDto = HousingMasterDto.builder()
                .housingReferenceCode(huicHousingMaster.getHouseRefNumber() + "")
                .addressAr(huicHousingMaster.getAddressAr())
                .addressEn(huicHousingMaster.getAddressEn())
                .locationNameAr(huicHousingMaster.getLocationNameAr())
                .locationNameEn(huicHousingMaster.getLocationNameEn())
                .lat(huicHousingMaster.getLocationLongLat() != null ? huicHousingMaster.getLocationLongLat().getLat() : null)
                .lng(huicHousingMaster.getLocationLongLat() != null ? huicHousingMaster.getLocationLongLat().getLng() : null)
                .zoneCode(huicHousingMaster.getZoneCode() + "")
                .categoryCode(huicHousingMaster.getCategoryCode() != null ? EHousingCategory.fromId(huicHousingMaster.getCategoryCode()).name() : null)
                .typeCode(EHousingType.fromId(huicHousingMaster.getTypeCode()).name())
                .siteCode(huicHousingMaster.getSiteCode() != null ? EHousingSite.fromId(huicHousingMaster.getSiteCode()).name() : null)
                .build();
        if (existingHousingMaster != null) {
            housingMasterDto.setId(existingHousingMaster.getId());
        }
        housingMasterService.save(housingMasterDto);
    }

    private void saveRitualSeasons(HuicRitualSeason huicRitualSeason) {
        RitualSeasonDto existingRitual = ritualSeasonService.findByRitualTypeAndSeason(ERitualType.fromId(huicRitualSeason.getRitualTypeCode()).name(), huicRitualSeason.getSeasonYear());
        RitualSeasonDto ritualSeasonDto = RitualSeasonDto.builder()
                .ritualTypeCode(ERitualType.fromId(huicRitualSeason.getRitualTypeCode()).name())
                .seasonStart(huicRitualSeason.getSeasonStart())
                .seasonEnd(huicRitualSeason.getSeasonEnd())
                .seasonYear(huicRitualSeason.getSeasonYear())
                .activated(true)
                .build();
        if (existingRitual != null) {
            ritualSeasonDto.setId(existingRitual.getId());
            ritualSeasonDto.setCompanyRitualSeasons(existingRitual.getCompanyRitualSeasons());

        }
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

    private void saveApplicantRelative(HuicApplicantRelative huicApplicantRelative, List<ErrorResponse> errorResponses, int rowNumber) {
        ApplicantLiteDto applicantLite = applicantLiteService.findByBasicInfo(huicApplicantRelative.getIdNumber() != null ? huicApplicantRelative.getIdNumber().toString() : null, huicApplicantRelative.getPassportNo(), huicApplicantRelative.getNationality().toString());
        if (applicantLite == null) {
            return;
        }
        Long applicantId = applicantLite.getId();
        String applicantUin = digitalIdService.findApplicantUin(applicantId);
        //TODO: to be reviewed , why update ??
        Long savedApplicantRitualId = applicantRitualService.findAndUpdate(applicantId, huicApplicantRelative.getPackageRefNumber(), null, false);
        if (savedApplicantRitualId == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setRowNumber(rowNumber + 1);
            errorResponse.getErrors().add(new ErrorItem(rowNumber + 1, "", "20001", "No Applicant ritual found"));
            errorResponses.add(errorResponse);
            return;
        }
        ApplicantRelativeDto applicantRelativeDto = ApplicantRelativeDto.builder()
                .relationshipCode(ERelativeRelationship.fromId(huicApplicantRelative.getRelationship()).name())
                .relativeIdNumber(huicApplicantRelative.getIdNumber() == null ? null : huicApplicantRelative.getIdNumber().toString())
                .relativePassportNumber(huicApplicantRelative.getPassportNo())
                .packageReferenceNumber(huicApplicantRelative.getPackageRefNumber())
                .build();
        ApplicantLiteDto relativeApplicantLite = applicantLiteService.findByBasicInfo(huicApplicantRelative.getRelativeIdNumber() != null ? huicApplicantRelative.getRelativeIdNumber().toString() : null, huicApplicantRelative.getRelativePassportNo(), huicApplicantRelative.getRelativeNationality().toString());
        applicantRelativeDto.setRelativeApplicant(ApplicantDto.fromApplicantLite(relativeApplicantLite));
        applicantRelativeDto.setApplicant(ApplicantDto.fromApplicantLite(applicantLite));
        applicantRelativeDto.setApplicantRitual(ApplicantRitualDto.builder().id(savedApplicantRitualId).build());
        String relativeApplicantUin = (relativeApplicantLite == null || CollectionUtils.isEmpty(relativeApplicantLite.getDigitalIds())) ? null : relativeApplicantLite.getDigitalIds().get(0).getUin();
        ApplicantRelativeDto existingRelative = applicantRelativeService.findByApplicantIdAndRelativeApplicantId(applicantId, relativeApplicantLite.getId());
        if (existingRelative != null) {
            if (existingRelative.getRelationshipCode().equals(ERelativeRelationship.fromId(huicApplicantRelative.getRelationship()).name())) {
                return;
            } else {
                applicantRelativeDto.setId(existingRelative.getId());
                applicantRelativeService.save(applicantRelativeDto);
                return;
            }
        }
        applicantRelativeService.save(applicantRelativeDto);
        // if the applicant digital id and the relative applicant digital id and the applicant ritual are created then add chat contacts
        // check if digital ids are created for the applicant and the relative applicant and applicant ritual is already created.
        if (applicantUin == null || relativeApplicantUin == null || savedApplicantRitualId == null) {
            return;
        }
        //TODO: try to get the relative applicant ritual id as it is needed to create the chat contact
        chatContactService.createApplicantRelativesChatContacts(applicantRelativeDto, savedApplicantRitualId);
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

    private void saveApplicantsMainData(HuicApplicantMainData huicApplicantMainData, List<ErrorResponse> errorResponses, int rowNumber) {
        ApplicantBasicDto applicant = ApplicantBasicDto.builder()
                .gender(EGenderCode.fromId(huicApplicantMainData.getGender()).name())
                .nationalityCode(huicApplicantMainData.getNationality().toString())
                .idNumber(huicApplicantMainData.getIdNumber() != null ? huicApplicantMainData.getIdNumber().toString() : null)
                .idNumberOriginal(huicApplicantMainData.getNationalIdOriginalCountry())
                .passportNumber(huicApplicantMainData.getPassportNo())
                .dateOfBirthGregorian(huicApplicantMainData.getDateOfBirth())
                .dateOfBirthHijri(huicApplicantMainData.getDateOfBirthHijri())
                .fullNameEn(huicApplicantMainData.getFullNameEn() != null ? huicApplicantMainData.getFullNameEn().matches(LATIN_REGEX) ? huicApplicantMainData.getFullNameEn() : null : null)
                .fullNameAr(huicApplicantMainData.getFullNameAr() != null ? huicApplicantMainData.getFullNameAr().matches(ARABIC_REGEX) ? huicApplicantMainData.getFullNameAr() : null : null)
                .fullNameOrigin(huicApplicantMainData.getFullNameOriginalLang())
                .maritalStatusCode(huicApplicantMainData.getMaritalStatus() != null ? EMaritalStatus.fromId(huicApplicantMainData.getMaritalStatus()).name() : null)
                .photo(huicApplicantMainData.getPhoto())
                .biometricDataFace(huicApplicantMainData.getBiometricDataFace())
                .biometricDataFinger(huicApplicantMainData.getBiometricDataFP())
                .educationLevelCode(huicApplicantMainData.getQualification())
                .packageReferenceNumber(huicApplicantMainData.getPackageRefNumber())
                .establishmentRefCode(huicApplicantMainData.getEstablishmentId())
                .serviceGroupMadinaCode(huicApplicantMainData.getServiceGroupMadinaId())
                .serviceGroupMakkahCode(huicApplicantMainData.getServiceGroupMakkahId())
                .build();
        ApplicantContactBasicDto applicantContactDto = ApplicantContactBasicDto.builder()
                .languageList(huicApplicantMainData.getLanguageList())
                .email(huicApplicantMainData.getEmail() != null ? huicApplicantMainData.getEmail().matches(EMAIL_REGEX) ? huicApplicantMainData.getEmail() : null : null)
                .localMobileNumber(huicApplicantMainData.getMobileNumber())
                .intlMobileNumber(huicApplicantMainData.getMobileNumberIntl())
                .countryCode(huicApplicantMainData.getCountry().toString())
                .streetName(huicApplicantMainData.getStreet())
                .districtName(huicApplicantMainData.getDistrict())
                .cityName(huicApplicantMainData.getCity())
                .buildingNumber(huicApplicantMainData.getBuildingNo())
                .postalCode(huicApplicantMainData.getPostalCode())
                .build();

        applicant.setContacts(Collections.singletonList(applicantContactDto));
        updateApplicantLiteBirthDate(applicant);
        applicant.setDateOfBirthGregorian(updateDate(applicant.getDateOfBirthGregorian()));
        Long existingApplicantId = applicantService.findIdByBasicInfo(applicant.getIdNumber(), applicant.getPassportNumber(), applicant.getNationalityCode());

        if (huicApplicantMainData.getStatus() == null) {
            huicApplicantMainData.setStatus(1);
        }
        // if record exists already in DB we need to update it
        if (existingApplicantId != null) {
            if (huicApplicantMainData.getStatus() == 2) {
                boolean isRegistered = applicantService.findApplicantStatus(existingApplicantId);
                if (!isRegistered) {
                    updateExistingApplicantLite(applicant, existingApplicantId);
                } else {
                    // if applicant is registered raise an error : cannot update
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setRowNumber(rowNumber + 1);
                    errorResponse.getErrors().add(new ErrorItem(rowNumber + 1, "", "30001", "This applicant is already registered and cannot be updated"));
                    errorResponses.add(errorResponse);
                    return;
                }
            } else if (huicApplicantMainData.getStatus() == 3) {
                updateExistingApplicantLite(applicant, existingApplicantId);
                applicant.setDeleted(true);
            } else if (huicApplicantMainData.getStatus() == 1) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setRowNumber(rowNumber + 1);
                errorResponse.getErrors().add(new ErrorItem(rowNumber + 1, "", "30001", "Please enter a valid value. Same value cannot be repeated within the previous registered records"));
                errorResponses.add(errorResponse);
                return;
            }
        } else {
            if (huicApplicantMainData.getStatus() == 2 || huicApplicantMainData.getStatus() == 3) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setRowNumber(rowNumber + 1);
                errorResponse.getErrors().add(new ErrorItem(rowNumber + 1, "", "30002", "No main record found for the input data to update the related sub record"));
                errorResponses.add(errorResponse);
                return;
            }
        }

        //TODO: unify this for both data upload and huic
        addApplicantLiteToContact(applicant);
        if (applicant.getPackageReferenceNumber() == null) {
            String referenceNumber = null;
            //if establishment id not null link applicant with default package of that establishment
            if (applicant.getEstablishmentRefCode() != null) {
                referenceNumber = ritualPackageService.findPackageReferenceNumber(applicant.getEstablishmentRefCode() + "_ESTABLISHMENT", ERitualType.fromId(huicApplicantMainData.getRitualTypeCode()).name(), huicApplicantMainData.getSeasonYear());
            }
            //if establishment is null
            else {
                // if ritual type is courtesy
                // link applicant with flyness package
                if (huicApplicantMainData.getRitualTypeCode() == 3) {
                    referenceNumber = ritualPackageService.findPackageReferenceNumber("9_ESTABLISHMENT", ERitualType.fromId(huicApplicantMainData.getRitualTypeCode()).name(), huicApplicantMainData.getSeasonYear());
                    applicant.setEstablishmentRefCode(9);
                } else {
                    //if ritual type not courtesy
                    //link applicant with default establishment package
                    referenceNumber = ritualPackageService.findPackageReferenceNumber("10_ESTABLISHMENT", ERitualType.fromId(huicApplicantMainData.getRitualTypeCode()).name(), huicApplicantMainData.getSeasonYear());
                    applicant.setEstablishmentRefCode(10);

                }
            }
            applicant.setPackageReferenceNumber(referenceNumber);

        }
        applicantBasicService.save(applicant);


    }

    private void saveApplicantRitual(HuicApplicantRitual huicApplicantRitual) {
        //TODO: check bed and room number
        ApplicantLiteDto applicantLite = applicantLiteService.findByBasicInfo(huicApplicantRitual.getIdNumber() != null ? huicApplicantRitual.getIdNumber().toString() : null, huicApplicantRitual.getPassportNo(), huicApplicantRitual.getNationality().toString());

        if (applicantLite == null) {
            return;
        }
        Long applicantId = applicantLite.getId();
        if (huicApplicantRitual.getPackageRefNumber() == null) {
            huicApplicantRitual.setPackageRefNumber(applicantLite.getPackageReferenceNumber());
        }
        ApplicantRitualDto applicantRitualDto = ApplicantRitualDto.builder()
                .packageReferenceNumber(huicApplicantRitual.getPackageRefNumber())
                .visaNumber(huicApplicantRitual.getVisaNumber())
                .permitNumber(huicApplicantRitual.getPermitNumber())
                .borderNumber(huicApplicantRitual.getBorderNo())
                .insuranceNumber(huicApplicantRitual.getInsuranceNumber())
                .busNumber(huicApplicantRitual.getBusNumber())
                .seatNumber(huicApplicantRitual.getSeatNumber())
                .bedNumber(huicApplicantRitual.getBedNumber())
                .roomNumber(huicApplicantRitual.getRoomNumber())
                .build();
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
        if ((applicant.getDateOfBirthHijri() == null || applicant.getDateOfBirthHijri() == 0) && applicant.getDateOfBirthGregorian() != null) {
            Calendar cl = Calendar.getInstance();
            cl.setTime(applicant.getDateOfBirthGregorian());
            HijrahDate islamyDate = HijrahChronology.INSTANCE.date(LocalDate.of(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH) + 1, cl.get(Calendar.DATE)));
            applicant.setDateOfBirthHijri(Long.parseLong(islamyDate.toString().substring(islamyDate.toString().indexOf("AH") + 3).replace("-", "")));
        }
    }

    public void updateApplicantLiteBirthDate(ApplicantBasicDto applicant) {
        if ((applicant.getDateOfBirthHijri() == null || applicant.getDateOfBirthHijri() == 0) && applicant.getDateOfBirthGregorian() != null) {
            Calendar cl = Calendar.getInstance();
            cl.setTime(applicant.getDateOfBirthGregorian());
            HijrahDate islamyDate = HijrahChronology.INSTANCE.date(LocalDate.of(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH) + 1, cl.get(Calendar.DATE)));
            applicant.setDateOfBirthHijri(Long.parseLong(islamyDate.toString().substring(islamyDate.toString().indexOf("AH") + 3).replace("-", "")));
        }
    }

    public void updateExistingApplicantLite(ApplicantBasicDto applicant, long existingApplicantId) {
        List<ApplicantContactDto> applicantContactDtos = applicantContactService.findByApplicantId(existingApplicantId);
        if (!applicantContactDtos.isEmpty()) {
            if (!applicant.getContacts().isEmpty()) {
                applicant.getContacts().get(0).setId(applicantContactDtos.get(0).getId());
            }
        }
        applicant.setId(existingApplicantId);
        applicant.setUpdateDate(new Date());
    }

    public void updateExistingApplicant(ApplicantDto applicant, long existingApplicantId) {
        List<ApplicantRitualDto> applicantRituals = applicantRitualService.findAllByApplicantId(existingApplicantId);
        List<ApplicantContactDto> applicantContactDtos = applicantContactService.findByApplicantId(existingApplicantId);
        if (!applicantContactDtos.isEmpty()) {
            if (!applicant.getContacts().isEmpty()) {
                applicant.getContacts().get(0).setId(applicantContactDtos.get(0).getId());
            }
        }
        applicant.setRituals(applicantRituals);
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

    public void addApplicantLiteToContact(ApplicantBasicDto applicant) {
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
//        staff.setApplicantGroups(existingStaff.getApplicantGroups());
    }

    public void updateGroupApplicantList(GroupApplicantListDto groupApplicantList, GroupApplicantListDto existingGroupApplicantList) {
        groupApplicantList.setId(existingGroupApplicantList.getId());
        groupApplicantList.setApplicantUin(existingGroupApplicantList.getApplicantUin());
    }

    public Boolean isValidApplicant(ApplicantLiteDto applicantLiteDto, String companyCode){
        long companyRefCode = Long.valueOf(companyCode.contains("_") ? companyCode.substring(0, companyCode.indexOf("_")) : companyCode);
        String companyType = companyCode.substring(companyCode.indexOf("_")+1, companyCode.length());
        EOrganizerTypes organizerTypes= EOrganizerTypes.valueOf(companyType);
        Boolean isValidApplicant = false;
        switch (organizerTypes){
            case ESTABLISHMENT:
                isValidApplicant = null != applicantLiteDto.getEstablishmentRefCode() && applicantLiteDto.getEstablishmentRefCode() == companyRefCode;
                break;
            case MISSION:
                isValidApplicant = null != applicantLiteDto.getMissionRefCode() && applicantLiteDto.getMissionRefCode() == companyRefCode;
                break;
            case EXTERNAL_HAJ_COMPANY:
                isValidApplicant = null != applicantLiteDto.getCompanyCode() && applicantLiteDto.getCompanyCode().equals(companyCode);
                break;
            case INTERNAL_HAJ_COMPANY:
                isValidApplicant = null != applicantLiteDto.getCompanyCode() && applicantLiteDto.getCompanyCode().equals(companyCode);
                break;
            case SERVICE_GROUP:
                isValidApplicant = (null != applicantLiteDto.getServiceGroupMadinaCode() && applicantLiteDto.getServiceGroupMadinaCode() == companyRefCode) || (null != applicantLiteDto.getServiceGroupMakkahCode() && applicantLiteDto.getServiceGroupMakkahCode() == companyRefCode);
                break;
            default:
                isValidApplicant = false;
        }
        return isValidApplicant;
    }

    public void saveStaffRitual(CompanyStaffRitualDto companyStaffRitual) {
        CompanyStaffDto existingStaff = companyStaffService.findByBasicInfo(companyStaffRitual.getIdNumber(), companyStaffRitual.getPassportNumber(), companyStaffRitual.getDateOfBirthGregorian(), companyStaffRitual.getDateOfBirthHijri());
        CompanyStaffDigitalIdBasicDto companyStaffDigitalId = companyStaffDigitalIdBasicService.findByBasicInfo(existingStaff.getId(), companyStaffRitual.getSeason());
        CompanyRitualSeasonDto companyRitualSeasonDto = companyRitualSeasonService.getLatestCompanyRitualSeasonByRitualSeason(companyStaffRitual.getCompanyCode(), companyStaffRitual.getTypeCode(), companyStaffRitual.getSeason());
        //existingStaff.setCompanyRitualSeason(companyRitualSeasonDto);
        if (companyStaffDigitalId != null) {
            // if he has a digital id for that same season
            List<CompanyStaffCardDto> companyStaffCardDtos = companyStaffCardService.findByDigitalId(companyStaffDigitalId.getSuin());
            // if no cards for digitalId and SEASON
            if (companyStaffCardDtos.isEmpty()) {
                CompanyStaffCardDto companyStaffCardDto = new CompanyStaffCardDto();
                companyStaffCardDto.setCompanyStaffDigitalId(CompanyStaffDigitalIdDto.builder().id(companyStaffDigitalId.getId()).build());
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
                companyStaffCardDto.setCompanyStaffDigitalId(CompanyStaffDigitalIdDto.builder().id(companyStaffDigitalId.getId()).build());
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

    @Transactional
    public void saveStaffFullRitual(CompanyStaffRitualDto companyStaffRitual, long staffId) {
        CompanyStaffDto existingStaff = companyStaffService.findOne(staffId);
        CompanyStaffDigitalIdBasicDto companyStaffDigitalId = companyStaffDigitalIdBasicService.findByBasicInfo(existingStaff.getId(), companyStaffRitual.getSeason());
//        CompanyRitualSeasonDto companyRitualSeasonDto = companyRitualSeasonService.getCompanyRitualSeason(companyStaffRitual.getCompanyCode(), companyStaffRitual.getTypeCode(), companyStaffRitual.getSeason());
        //existingStaff.setCompanyRitualSeason(companyRitualSeasonDto);
        if (companyStaffDigitalId != null) {
            // if he has a digital id for that same season
            List<CompanyStaffCardDto> companyStaffCardDtos = companyStaffCardService.findByDigitalId(companyStaffDigitalId.getSuin());
            // if no cards for digitalId and SEASON
            if (companyStaffCardDtos.isEmpty()) {
                CompanyStaffCardDto companyStaffCardDto = new CompanyStaffCardDto();
                companyStaffCardDto.setCompanyStaffDigitalId(CompanyStaffDigitalIdDto.builder().id(companyStaffDigitalId.getId()).build());
                companyStaffCardDto.setStatusCode(ECardStatus.READY_TO_PRINT.name());
                companyStaffCardDto.setCompanyRitualSeason(CompanyRitualSeasonDto.builder()
                        .id(companyRitualSeasonService.getCompanyRitualSeasonId(companyStaffRitual.getCompanyCode(), companyStaffRitual.getTypeCode(), companyStaffRitual.getSeason())).build());
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
                companyStaffCardDto.setCompanyStaffDigitalId(CompanyStaffDigitalIdDto.builder().id(companyStaffDigitalId.getId()).build());
                companyStaffCardDto.setStatusCode(ECardStatus.READY_TO_PRINT.name());
                companyStaffCardDto.setCompanyRitualSeason(CompanyRitualSeasonDto.builder()
                        .id(companyRitualSeasonService.getCompanyRitualSeasonId(companyStaffRitual.getCompanyCode(), companyStaffRitual.getTypeCode(), companyStaffRitual.getSeason())).build());
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
            companyStaffCardDto.setCompanyRitualSeason(CompanyRitualSeasonDto.builder()
                    .id(companyRitualSeasonService.getCompanyRitualSeasonId(companyStaffRitual.getCompanyCode(), companyStaffRitual.getTypeCode(), companyStaffRitual.getSeason())).build());
            companyStaffCardService.save(companyStaffCardDto);

        }
    }

    public static Date toGregorian(Long hijriDate) {
        String hijriDateString = String.valueOf(hijriDate);
        LocalDate muslimDate = LocalDate.of(Integer.valueOf(hijriDateString.substring(0, 4)), Integer.valueOf(hijriDateString.substring(5, 6)), Integer.valueOf(hijriDateString.substring(7, 8)));
        final HijrahDate hijrahDate = HijrahChronology.INSTANCE.date(HijrahEra.AH,
                muslimDate.get(ChronoField.YEAR_OF_ERA), muslimDate.get(ChronoField.MONTH_OF_YEAR),
                muslimDate.get(ChronoField.DAY_OF_MONTH));

        LocalDate localDate = IsoChronology.INSTANCE.date(hijrahDate);
        return Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}

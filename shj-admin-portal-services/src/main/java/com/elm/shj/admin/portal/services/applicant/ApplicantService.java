/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.*;
import com.elm.shj.admin.portal.orm.repository.*;
import com.elm.shj.admin.portal.services.audit.MobileAuditLogService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
import com.elm.shj.admin.portal.services.user.UserLocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.*;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Service handling applicant
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantService extends GenericService<JpaApplicant, ApplicantDto, Long> {

    private final MobileAuditLogService mobileAuditLogService;
    private final ApplicantRepository applicantRepository;
    private final ApplicantContactRepository applicantContactRepository;
    private final ApplicantRitualService applicantRitualService;
    private final ApplicantDigitalIdRepository applicantDigitalIdRepository;
    private final ApplicantPackageService applicantPackageService;
    private final NationalityLookupRepository nationalityLookupRepository;
    private final RitualTypeLookupRepository ritualTypeLookupRepository;
    private final UserLocationService userLocationService;


    public final static String SAUDI_MOBILE_NUMBER_REGEX = "^(966|009665|9665|\\+9665|05|5)([0-9]{8})$";
    private final String GROUP_DATA_FILE_NAME = "group-data.xlsx";
    private final String HOUSING_DATA_FILE_NAME = "housing-data.xlsx";


    /**
     * finds an applicant with the same basic info exists
     *
     * @param applicantBasicInfo the applicant basic info
     * @return the found applicant with the same basic info exists
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantDto findByBasicInfo(ApplicantBasicInfoDto applicantBasicInfo) {
        log.info("ApplicantService ::: Start findByBasicInfo ::: applicantBasicInfo rowNum: {}", applicantBasicInfo == null ? null : applicantBasicInfo.getRowNum());
        ApplicantDto applicantDto = getMapper().fromEntity(applicantRepository.findByBasicInfo(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(), applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian()), mappingContext);
        log.info("ApplicantService ::: Finish findByBasicInfo ::: applicantDto DigitalId: {}", applicantDto.getDigitalIds().get(0));
        return applicantDto;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Long findIdByBasicInfo(ApplicantBasicInfoDto applicantBasicInfo) {
        log.info("ApplicantService ::: Start findByBasicInfo ::: applicantBasicInfo rowNum: {}", applicantBasicInfo == null ? null : applicantBasicInfo.getRowNum());
        Long idByBasicInfo = applicantRepository.findIdByBasicInfo(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(), applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian());
        log.info("ApplicantService ::: Finish findByBasicInfo ::: applicantId: {}", idByBasicInfo);
        return idByBasicInfo;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Long findIdByBasicInfo(String idNumber, String passportNumber, String nationalityCode) {
        log.info("ApplicantService ::: Start findByBasicInfo :::");
        Long idByBasicInfo = applicantRepository.findIdByBasicInfo(idNumber, passportNumber, nationalityCode);
        log.info("ApplicantService ::: Finish findByBasicInfo ::: applicantId: {}", idByBasicInfo);
        return idByBasicInfo;
    }

    /**
     * checks if an applicant exists with the same basic info exists
     *
     * @param applicantBasicInfo the applicant basic info
     * @return if applicant with the same basic info exists
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public boolean existsByBasicInfo(ApplicantBasicInfoDto applicantBasicInfo) {
        log.info("ApplicantService ::: Start existsByBasicInfo ::: applicantBasicInfo rowNum: {}", applicantBasicInfo == null ? null : applicantBasicInfo.getRowNum());
        boolean exists = applicantRepository.existsByBasicInfo(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(), applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian());
        log.info("ApplicantService ::: Finish existsByBasicInfo ::: isExists: {}", exists);
        return exists;
    }

    /**
     * Finds an applicant by his uin
     *
     * @param uin the uin of applicant to find
     * @return the found applicant or empty structure
     */
    public Optional<ApplicantDto> findByUin(String uin) {
        log.info("ApplicantService ::: Start findByUin ::: uin: {}", uin);

        JpaApplicant applicant = applicantRepository.findByUin(uin);
        if (applicant != null) {
            log.info("ApplicantService ::: Finish findByUin ::: FullNameEn: {}", applicant.getFullNameEn());
            return Optional.of(getMapper().fromEntity(applicant, mappingContext));
        }
        {
            log.info("ApplicantService ::: Finish findByUin ::: not found with uin: {}", uin);
            return Optional.empty();
        }
    }


    /**
     * Finds an applicant by his uin
     *
     * @param applicantId the applicant ID
     * @param command     the request body
     * @return the lite version of applicant or empty structure
     */
    @Transactional
    public int updateApplicantContacts(long applicantId, UpdateApplicantCmd command) {
        log.info("ApplicantService ::: Start updateApplicantContacts ::: applicantId: {},  command: {}", applicantId, command);
        ApplicantRitualPackageVo latestPackage = applicantPackageService.findLatestApplicantRitualPackage(Long.parseLong(command.getUin()));
        int updatedRowsCount = 0;
        if (latestPackage != null) {
            if (command.getMobileNumber().matches(SAUDI_MOBILE_NUMBER_REGEX)) {
                updatedRowsCount = applicantContactRepository.updateContactLocalNumber(command.getEmail(), command.getMobileNumber(), applicantId);
            } else {
                updatedRowsCount = applicantContactRepository.updateContactIntlNumber(command.getEmail(),  command.getMobileNumber(), applicantId);
            }
            updatedRowsCount += applicantRepository.markAsRegistered(applicantId, command.getChannel());

        }
        log.info("ApplicantService ::: Finish updateApplicantContacts ::: updatedRowsCount: {}", updatedRowsCount);
        return updatedRowsCount;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public ApplicantDto save(ApplicantDto applicant) {
        // persist the record
        log.info("ApplicantService ::: Start updateApplicantContacts ::: FullNameEn: {}", applicant.getFullNameEn());
        ApplicantDto savedApplicant = super.save(applicant);
        log.info("ApplicantService ::: Finish updateApplicantContacts ::: FullNameEn: {}", savedApplicant.getFullNameEn());
        return savedApplicant;
    }

    /**
     * Find all applicants having active ritual
     *
     * @return the list of applicants
     */
    public List<ApplicantDto> findAllRegisteredAndHavingActiveRitual() {
        log.info("ApplicantService ::: Start findAllRegisteredAndHavingActiveRitual ");
        List<ApplicantDto> applicantDtos = mapList(applicantRepository.findAllApplicantsRegisteredAndHavingActiveRitual(new Date()));
        log.info("ApplicantService ::: Finish findAllRegisteredAndHavingActiveRitual with applicantDtosListSize: {}", applicantDtos.size());
        return applicantDtos;
    }

    /**
     * Count applicants having active ritual
     *
     * @return the number of applicants
     */
    public long countHavingActiveRitual() {
        log.info("ApplicantService ::: Start countHavingActiveRitual ");
        long count = applicantRepository.countHavingActiveRitual(new Date());
        log.info("ApplicantService ::: Finish countHavingActiveRitual: {} ", count);
        return count;
    }

    public long countAllByCriteria(NotificationTemplateCategorizingDto applicantSearchCriteria, List<Long> excludedIds) {
        log.info("Start countAllByCriteria with NotificationTemplateCategorizingDto: {}", applicantSearchCriteria);
        Long notificationsCount = applicantRepository.count(withApplicantFilter(applicantSearchCriteria, excludedIds));
        log.info("Finish countAllByCriteria with NotificationTemplateCategorizingDto");
        return notificationsCount;
    }

    public List<ApplicantDto> findAllByCriteria(NotificationTemplateCategorizingDto applicantSearchCriteria, List<Long> excludedIds) {
        log.info("Start findAllByCriteria with NotificationTemplateCategorizingDto: {}", applicantSearchCriteria);
        List<ApplicantDto> applicantDtoList = mapList(applicantRepository.findAll(withApplicantFilter(applicantSearchCriteria, excludedIds)));
        log.info("Finish findAllByCriteria with NotificationTemplateCategorizingDto");
        return applicantDtoList;
    }

    public Page<ApplicantDto> findAllByCriteriaAndNotInExcludedIds(NotificationTemplateCategorizingDto applicantSearchCriteria, List<Long> excludedIds, Pageable pageable) {
        log.info("Start findAllByCriteriaAndNotInExcludedIds with NotificationTemplateCategorizingDto: {},  excludedIds: {}", applicantSearchCriteria, excludedIds);
        Page<ApplicantDto> applicantDtoPage = mapPage(applicantRepository.findAll(withApplicantFilter(applicantSearchCriteria, excludedIds), pageable));
        log.info("Finish findAllByCriteriaAndNotInExcludedIds");
        return applicantDtoPage;
    }

    private Specification<JpaApplicant> withApplicantFilter(final NotificationTemplateCategorizingDto criteria, List<Long> excludedIds) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            //Create atomic predicates
            List<Predicate> predicates = new ArrayList<>();

            //Filter registered applicants
            predicates.add(criteriaBuilder.equal(root.get("registered"), true));

            // Retrieve only applicants having one active ritual based on applicant package start date and end date
            Date today = new Date();
            Join<JpaApplicant, JpaApplicantPackageHousing> rituals = root.join("rituals");
            predicates.add(criteriaBuilder.lessThanOrEqualTo(rituals.get("applicantPackage").get("startDate"), today));
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(rituals.get("applicantPackage").get("endDate"), today));

            if (criteria.getCompanyId() != null) {
                Path<String> companyId = rituals.get("applicantPackage").get("ritualPackage").get("companyRitualSeason").get("company").get("id");
                predicates.add(criteriaBuilder.equal(companyId, criteria.getCompanyId()));
            }

            if (criteria.getCampId() != null) {
                Join<JpaApplicant, JpaApplicantPackageHousing> applicantPackageHousings = rituals.join("applicantPackage")
                        .join("applicantPackageHousings");
                Path<String> campId = applicantPackageHousings.get("packageHousing").get("id");
                predicates.add(criteriaBuilder.equal(campId, criteria.getCampId()));
            }

            if (criteria.getNationalityCode() != null) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("nationalityCode")), criteria.getNationalityCode().toUpperCase()));
            }

            if (criteria.getGender() != null) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), criteria.getGender()));
            }

            if (criteria.getMaxAge() != null) {
                Date dateBeforeMaxAge = getDateFromAge(criteria.getMaxAge());
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateOfBirthGregorian"), dateBeforeMaxAge));
            }

            if (criteria.getMinAge() != null) {
                Date dateBeforeMinAge = getDateFromAge(criteria.getMinAge());
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateOfBirthGregorian"), dateBeforeMinAge));
            }

            if (criteria.getUin() != null && criteria.getUin().trim().length() > 0) {
                Join<JpaApplicant, JpaApplicantDigitalId> digitalIds = root.join("digitalIds");
                predicates.add(criteriaBuilder.like(digitalIds.get("uin"), "%" + criteria.getUin().trim() + "%"));
            }

            if (criteria.getIdNumber() != null && criteria.getIdNumber().trim().length() > 0) {
                predicates.add(criteriaBuilder.like(root.get("idNumber"), "%" + criteria.getIdNumber().trim() + "%"));
            }

            if (criteria.getPassportNumber() != null && criteria.getPassportNumber().trim().length() > 0) {
                predicates.add(criteriaBuilder.like(root.get("passportNumber"), "%" + criteria.getPassportNumber().trim() + "%"));
            }

            if (excludedIds != null && !excludedIds.isEmpty()) {
                predicates.add(criteriaBuilder.not(root.get("id").in(excludedIds)));
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }

    private Date getDateFromAge(Integer age) {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -age);
        return c.getTime();
    }


    public List<ApplicantDto> findAllByIds(List<Long> selectedApplicants) {
        log.info("ApplicantService ::: Start findAllByIds selectedApplicantsListSize: {} ", selectedApplicants.size());
        List<ApplicantDto> applicantDtos = mapList(applicantRepository.findAllByIds(selectedApplicants));
        log.info("ApplicantService ::: Finish findAllByIds applicantDtosListSize: {} ", applicantDtos.size());
        return applicantDtos;
    }

    public Page<ApplicantDto> findByIds(@RequestParam List<Long> selectedApplicants, Pageable pageable) {
        log.info("ApplicantService ::: Start findByIds selectedApplicantsListSize: {} ", selectedApplicants.size());
        Page<ApplicantDto> applicantDtos = mapPage(applicantRepository.findByIds(selectedApplicants, pageable));
        log.info("ApplicantService ::: Finish findByIds applicantDtosPageSize: {} ", applicantDtos.getTotalElements());
        return applicantDtos;
    }

    //TODO: To be deleted
    public boolean existsByBasicInfoAndPackageCode(ApplicantBasicInfoDto applicantBasicInfo, String packageReferenceNumber) {
        log.info("ApplicantService ::: Start existsByBasicInfoAndPackageCode applicantBasicInfoRowNum: {}, packageReferenceNumber: {} ", applicantBasicInfo == null ? null : applicantBasicInfo.getRowNum(), packageReferenceNumber);
        boolean isExists = applicantRepository.findByBasicInfoAndPackageCode(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(), applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian(), packageReferenceNumber);
        log.info("ApplicantService ::: Finish existsByBasicInfoAndPackageCode isExists: {} ", isExists);
        return isExists;
    }

    @Transactional
    public void updatePreferredLanguage(String uin, String lang) {
        log.info("ApplicantService ::: Start updatePreferredLanguage uin: {},  lang: {} ", uin, lang);
        Optional<ApplicantDto> applicant = findByUin(uin);
        applicant.ifPresent(applicantDto -> applicantRepository.updatePreferredLanguage(applicantDto.getId(), lang));
        log.info("ApplicantService ::: Finish updatePreferredLanguage");
    }

    @Transactional
    public void updateLoggedInFromMobileAppFlag(boolean mobileLoggedIn, long applicantId) {
        log.info("ApplicantService ::: Start updateLoggedInFromMobileAppFlag applicantId: {},  lang: {} ", mobileLoggedIn, applicantId);
        applicantRepository.updateLoggedInFromMobileAppFlag(applicantId, mobileLoggedIn);
        applicantDigitalIdRepository
                .findByApplicantIdAndStatusCode(applicantId, EDigitalIdStatus.VALID.name())
                .ifPresent(applicantDigitalId -> {
                    MobileAuditLogDto mobileAuditLogDto = new MobileAuditLogDto();
                    if (mobileLoggedIn) {
                        mobileAuditLogDto.setEvent("login");
                    } else {
                        mobileAuditLogDto.setEvent("logout");
                    }
                    mobileAuditLogDto.setUserId(applicantDigitalId.getUin());
                    mobileAuditLogService.save(mobileAuditLogDto);
                });
        log.info("ApplicantService ::: Finish updateLoggedInFromMobileAppFlag ");
    }

    @Transactional
    public void storeSignupEvent(long applicantId) {
        log.info("ApplicantService ::: Start storeSignupEvent applicantId: {}", applicantId);
        applicantDigitalIdRepository
                .findByApplicantIdAndStatusCode(applicantId, EDigitalIdStatus.VALID.name())
                .ifPresent(applicantDigitalId -> {
                    MobileAuditLogDto mobileAuditLogDto = new MobileAuditLogDto();
                    mobileAuditLogDto.setEvent("registration");
                    mobileAuditLogDto.setUserId(applicantDigitalId.getUin());
                    mobileAuditLogService.save(mobileAuditLogDto);
                });
        log.info("ApplicantService ::: Finish storeSignupEvent");
    }

    /**
     * Set data request record id for the applicant.
     *
     * @param dataRequestRecordId
     * @param applicantId
     */
    @Transactional
    public void updateDataRequestRecordId(long dataRequestRecordId, long applicantId, long applicantRitualId) {
        log.info("ApplicantService ::: Start updateDataRequestRecordId dataRequestRecordId: {},  applicantId: {},  applicantRitualId: {}", dataRequestRecordId, applicantId, applicantRitualId);
        applicantRepository.updateDataRequestRecordId(dataRequestRecordId, applicantId);
        applicantRitualService.updateDataRequestRecordId(dataRequestRecordId, applicantRitualId);
        log.info("ApplicantService ::: Finish updateDataRequestRecordId");
    }

    @Transactional
    public int markAsRegistered(long applicantId, String channel) {
        log.info("ApplicantService ::: Start markAsRegistered applicantId: {},  channel: {}", applicantId, channel);
        int numberOfAffectedRows = applicantRepository.markAsRegistered(applicantId, channel);
        log.info("ApplicantService ::: Finish markAsRegistered numberOfAffectedRows: {}", numberOfAffectedRows);
        return numberOfAffectedRows;
    }

    public List<ApplicantDto> findApplicantByGroupId(Long groupId) {
        log.info("Start findApplicantByGroupId with groupId: {}", groupId);
        List<ApplicantDto> applicantDtoList = mapList(applicantRepository.findAllApplicantByGroupId(groupId));
        log.info("Finish findApplicantByGroupId with groupId: {}", groupId);
        return applicantDtoList;
    }

    public List<ApplicantDto> findApplicantByCompanyCode(String companyCode) {
        log.info("Start findApplicantByCompanyCode with companyCode: {}", companyCode);
        List<ApplicantDto> applicantDtoList = mapList(applicantRepository.findApplicantByCompanyCode(companyCode));
        log.info("Finish findApplicantByCompanyCode with companyCode: {}", companyCode);
        return applicantDtoList;
    }

    public Page<ApplicantDto> findOrganizerApplicants(ApplicantSearchCriteriaDto applicantSearchCriteriaDto, Long companyRefCode, String companyTypeCode, Pageable pageable) {
        log.info("Start findOrganizerApplicants with companyTypeCode: {}, companyRefCode: {}", companyTypeCode, companyRefCode);
        log.info("Company Ref code ...{}", companyRefCode);
        log.info("Company type code ...{}", companyTypeCode);
        log.info("Applicant search criteria... {}", applicantSearchCriteriaDto);
        log.info("Group reference number... {}", applicantSearchCriteriaDto.getGroupNumber());

        if(companyTypeCode.equals(EOrganizerTypes.GOVERNMENT_AGENCY.name())){
            return  new PageImpl<>(Collections.emptyList(), pageable, 0);
        }

        Long establishmentRefCode = -1L;
        Long missionRefCode = -1L;
        Long serviceGroupRefCode = -1L;
        String companyCode = "-1";

        if(companyTypeCode.equals(EOrganizerTypes.ESTABLISHMENT.name())){
            establishmentRefCode = companyRefCode;
        } else if(companyTypeCode.equals(EOrganizerTypes.MISSION.name())){
            missionRefCode = companyRefCode;
        } else if(companyTypeCode.equals(EOrganizerTypes.SERVICE_GROUP.name())){
            serviceGroupRefCode = companyRefCode;
        } else if(companyTypeCode.equals(EOrganizerTypes.INTERNAL_HAJ_COMPANY.name()) ||
                companyTypeCode.equals(EOrganizerTypes.EXTERNAL_HAJ_COMPANY.name())){
            companyCode = companyRefCode + "_" + companyTypeCode;
        }

        if(applicantSearchCriteriaDto.getGroupNumber() != null && !applicantSearchCriteriaDto.getGroupNumber().equals("")){
            //applicantSearchCriteriaDto.setGroupNumber(applicantSearchCriteriaDto.getGroupNumber() + "_" + String.valueOf(companyRefCode)+ "_" + companyTypeCode);
            String companyFullCode = String.valueOf(companyRefCode) + "_" + companyTypeCode;
            log.info("Group reference number... {}", applicantSearchCriteriaDto.getGroupNumber());
            Page<ApplicantDto> applicants = mapPage(applicantRepository.findOrganizerApplicantsWithGroupNumberFilter(applicantSearchCriteriaDto.getIdNumber(), applicantSearchCriteriaDto.getGroupNumber(),
                    applicantSearchCriteriaDto.getPassportNumber(), applicantSearchCriteriaDto.getApplicantName(), applicantSearchCriteriaDto.getGender(),
                    applicantSearchCriteriaDto.getUin(), companyCode, establishmentRefCode, missionRefCode, serviceGroupRefCode, companyFullCode, pageable));
            log.info("Result of applicant content ... {}", applicants);
            log.info("Result of applicant list ... {}", applicants.getContent());
            applicants.getContent().forEach(applicant -> {
                UserLocationDto userLocationDto = userLocationService.findTopByUserIdAndUserTypeOrderByCreationDateDesc(applicant.getDigitalIds().isEmpty() ? null : applicant.getDigitalIds().get(0).getUin(),
                        EUserType.APPLICANT.name());
                applicant.setLatitude(userLocationDto == null ? null : userLocationDto.getLatitude());
                applicant.setLongitude(userLocationDto == null ? null : userLocationDto.getLongitude());
            });
            log.info("Finish findOrganizerApplicants in case the group number is null");
            return applicants;
        } else {
            Page<ApplicantDto> applicants = mapPage(applicantRepository.findOrganizerApplicants(applicantSearchCriteriaDto.getIdNumber(), applicantSearchCriteriaDto.getPassportNumber(),
                    applicantSearchCriteriaDto.getApplicantName(), applicantSearchCriteriaDto.getGender(),
                    applicantSearchCriteriaDto.getUin(), companyCode, establishmentRefCode, missionRefCode, serviceGroupRefCode, pageable));
            applicants.getContent().forEach(applicant -> {
                UserLocationDto userLocationDto = userLocationService.findTopByUserIdAndUserTypeOrderByCreationDateDesc(applicant.getDigitalIds().isEmpty() ? null : applicant.getDigitalIds().get(0).getUin(),
                        EUserType.APPLICANT.name());
                applicant.setLatitude(userLocationDto == null ? null : userLocationDto.getLatitude());
                applicant.setLongitude(userLocationDto == null ? null : userLocationDto.getLongitude());
            });
            log.info("Finish findOrganizerApplicants in case the group number is not null");
            return applicants;
        }
    }

    public Resource exportApplicantGroupTemplate(Long companyRefCode, String companyTypeCode) throws Exception {
        Resource resource = new ClassPathResource("/templates/export-template/" + GROUP_DATA_FILE_NAME);
        XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
        // read first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);
        // read first row
        AtomicInteger headerRowNum = new AtomicInteger(sheet.getFirstRowNum()+1);

        Long establishmentRefCode = -1L;
        Long missionRefCode = -1L;
        Long serviceGroupRefCode = -1L;
        String companyCode = "-1";

        if(companyTypeCode.equals(EOrganizerTypes.ESTABLISHMENT.name())){
            establishmentRefCode = companyRefCode;
        } else if(companyTypeCode.equals(EOrganizerTypes.MISSION.name())){
            missionRefCode = companyRefCode;
        } else if(companyTypeCode.equals(EOrganizerTypes.SERVICE_GROUP.name())){
            serviceGroupRefCode = companyRefCode;
        } else if(companyTypeCode.equals(EOrganizerTypes.INTERNAL_HAJ_COMPANY.name()) ||
                companyTypeCode.equals(EOrganizerTypes.EXTERNAL_HAJ_COMPANY.name())){
            companyCode = companyRefCode + "_" + companyTypeCode;
        }
        AtomicInteger cellIndex = new AtomicInteger();

        List<ApplicantDto> applicantDtos = mapList(applicantRepository.findOrganizerApplicantsForExport(companyCode, establishmentRefCode, missionRefCode, serviceGroupRefCode));

        applicantDtos.stream().forEach(applicant -> {
            Row row = sheet.getRow(headerRowNum.getAndIncrement());

            Cell idNumber = row.getCell(row.getFirstCellNum() + cellIndex.getAndIncrement());
            idNumber.setCellValue(applicant.getIdNumber());

            Cell passportNumber = row.getCell(row.getFirstCellNum() + cellIndex.getAndIncrement());
            passportNumber.setCellValue(applicant.getPassportNumber());

            Cell nationality = row.getCell(row.getFirstCellNum() + cellIndex.getAndIncrement());
            nationality.setCellValue(nationalityLookupRepository.findLabelByCodeAndLanguage(applicant.getNationalityCode(), "ar"));

            Cell nationalityCode = row.getCell(row.getFirstCellNum() + cellIndex.getAndIncrement());
            nationalityCode.setCellValue(applicant.getNationalityCode());

            Cell groupRefNumber = row.getCell(row.getFirstCellNum() + cellIndex.getAndIncrement());
            groupRefNumber.setCellValue("");

            String ritualType="";
            if(!applicantRepository.findRitualTypeByApplicantId(applicant.getId()).isEmpty()){
                ritualType = applicantRepository.findRitualTypeByApplicantId(applicant.getId()).get(0);
            }

            Cell ritualTypeCell = row.getCell(row.getFirstCellNum() + cellIndex.getAndIncrement());
            ritualTypeCell.setCellValue(ritualTypeLookupRepository.findLabelByCodeAndLanguage(ritualType, "ar"));

            Cell ritualTypeCodeCell = row.getCell(row.getFirstCellNum() + cellIndex.getAndIncrement());
            ritualTypeCodeCell.setCellValue(ritualType);

            cellIndex.set(0);

        });

        ByteArrayOutputStream outputStream = null;
        try{
         outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return new ByteArrayResource(outputStream.toByteArray(), GROUP_DATA_FILE_NAME);
        } catch (Exception e) {
            log.error("Download file failure. TargetPath: {}", e);
            throw new Exception("Download File failure");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public Resource exportApplicantHousingTemplate(Long companyRefCode, String companyTypeCode) throws Exception {
        Resource resource = new ClassPathResource("/templates/export-template/" + HOUSING_DATA_FILE_NAME);
        XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
        // read first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);
        // read first row
        AtomicInteger headerRowNum = new AtomicInteger(sheet.getFirstRowNum()+1);

        Long establishmentRefCode = -1L;
        Long missionRefCode = -1L;
        Long serviceGroupRefCode = -1L;
        String companyCode = "-1";

        if(companyTypeCode.equals(EOrganizerTypes.ESTABLISHMENT.name())){
            establishmentRefCode = companyRefCode;
        } else if(companyTypeCode.equals(EOrganizerTypes.MISSION.name())){
            missionRefCode = companyRefCode;
        } else if(companyTypeCode.equals(EOrganizerTypes.SERVICE_GROUP.name())){
            serviceGroupRefCode = companyRefCode;
        } else if(companyTypeCode.equals(EOrganizerTypes.INTERNAL_HAJ_COMPANY.name()) ||
                companyTypeCode.equals(EOrganizerTypes.EXTERNAL_HAJ_COMPANY.name())){
            companyCode = companyRefCode + "_" + companyTypeCode;
        }
        AtomicInteger cellIndex = new AtomicInteger();

        List<ApplicantDto> applicantDtos = mapList(applicantRepository.findOrganizerApplicantsForExport(companyCode, establishmentRefCode, missionRefCode, serviceGroupRefCode));

        applicantDtos.stream().forEach(applicant -> {
            Row row = sheet.getRow(headerRowNum.getAndIncrement());

            Cell idNumber = row.getCell(row.getFirstCellNum() + cellIndex.getAndIncrement());
            idNumber.setCellValue(applicant.getIdNumber());

            Cell passportNumber = row.getCell(row.getFirstCellNum() + cellIndex.getAndIncrement());
            passportNumber.setCellValue(applicant.getPassportNumber());

            Cell nationality = row.getCell(row.getFirstCellNum() + cellIndex.getAndIncrement());
            nationality.setCellValue(nationalityLookupRepository.findLabelByCodeAndLanguage(applicant.getNationalityCode(), "ar"));

            Cell nationalityCode = row.getCell(row.getFirstCellNum() + cellIndex.getAndIncrement());
            nationalityCode.setCellValue(applicant.getNationalityCode());
            cellIndex.set(0);

        });

        ByteArrayOutputStream outputStream = null;
        try{
            outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();
            return new ByteArrayResource(outputStream.toByteArray(), HOUSING_DATA_FILE_NAME);
        } catch (Exception e) {
            log.error("Download file failure. TargetPath: {}", e);
            throw new Exception("Download File failure");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public List<Long> findApplicantIdByGroupId(Long groupId){
        log.info("Start findApplicantIdByGroupId with groupId: {}", groupId);
        List<Long> applicantIdByGroupId = applicantRepository.findApplicantIdByGroupId(groupId);
        log.info("Finish findApplicantIdByGroupId with groupId: {}", groupId);
        return applicantIdByGroupId;
    }

    public Boolean isValidApplicant(Long applicantId){
        log.info("Start isValidApplicant with applicantId: {}", applicantId);
        Boolean isValidApplicant = applicantRepository.isValidApplicant(applicantId);
        log.info("Finish isValidApplicant with applicantId: {}", applicantId);
        return isValidApplicant;
    }

}

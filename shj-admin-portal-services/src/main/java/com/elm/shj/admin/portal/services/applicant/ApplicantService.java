/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.*;
import com.elm.shj.admin.portal.orm.repository.ApplicantContactRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantDigitalIdRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRepository;
import com.elm.shj.admin.portal.services.audit.MobileAuditLogService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.*;
import java.util.*;

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
    public final static String SAUDI_MOBILE_NUMBER_REGEX = "^(009665|9665|\\+9665|05|5)([0-9]{8})$";


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

    public boolean findApplicantStatus(Long id) {
        log.info("ApplicantService ::: Start findApplicantStatus ::: id: {}", id);
        return applicantRepository.findApplicantStatusById(id);
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
                updatedRowsCount = applicantContactRepository.updateContactLocalNumber(command.getEmail(), command.getCountryCode(), command.getMobileNumber(), applicantId);
            } else {
                updatedRowsCount = applicantContactRepository.updateContactIntlNumber(command.getEmail(), command.getCountryCode(), command.getMobileNumber(), applicantId);
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
        return applicantRepository.count(withApplicantFilter(applicantSearchCriteria, excludedIds));
    }

    public List<ApplicantDto> findAllByCriteria(NotificationTemplateCategorizingDto applicantSearchCriteria, List<Long> excludedIds) {
        return mapList(applicantRepository.findAll(withApplicantFilter(applicantSearchCriteria, excludedIds)));
    }

    public Page<ApplicantDto> findAllByCriteriaAndNotInExcludedIds(NotificationTemplateCategorizingDto applicantSearchCriteria, List<Long> excludedIds, Pageable pageable) {
        return mapPage(applicantRepository.findAll(withApplicantFilter(applicantSearchCriteria, excludedIds), pageable));
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

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
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
        log.info("ApplicantService ::: Start markAsRegistered numberOfAffectedRows: {}", numberOfAffectedRows);
        return numberOfAffectedRows;
    }

    public List<ApplicantDto> findOrganizerApplicants(ApplicantSearchCriteriaDto applicantSearchCriteriaDto, Long companyRefCode, String companyTypeCode) {
        List<ApplicantDto> applicantDtos = mapList(applicantRepository.findAll(withApplicantSearchFilter(applicantSearchCriteriaDto, companyRefCode, companyTypeCode)));
        return applicantDtos;
    }

    private Specification<JpaApplicant> withApplicantSearchFilter(final ApplicantSearchCriteriaDto criteria, Long companyRefCode, String companyTypeCode) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            //Create atomic predicates
            List<Predicate> predicates = new ArrayList<>();
            Join<JpaApplicant, JpaApplicantDigitalId> digitalIds = root.join("digitalIds");
           //Join<JpaApplicantDigitalId, JpaGroupApplicantList> groupApplicationList = digitalIds.join("applicantUin");
           //Join<JpaGroupApplicantList, JpaApplicantGroup> applicantGroup = groupApplicationList.join("applicantGroup");



            /*final Subquery<JpaGroupApplicantList> groupApplicationList = criteriaQuery.subquery(JpaGroupApplicantList.class);
            final Root<JpaGroupApplicantList> groupApplication = groupApplicationList.from(JpaGroupApplicantList.class);

            // select up.username from users_profiles ...
            groupApplicationList.select(groupApplication.get("applicantUin"));

            // select u from users u where u.name in ...
            predicates.add(digitalIds.get("uin").in(groupApplication));*/


            if (criteria.getIdNumber() != null && !criteria.getIdNumber().equals("")) {
                Path<String> idNumber = root.get("idNumber");
                predicates.add(criteriaBuilder.equal(idNumber, criteria.getIdNumber()));
            }

            if (criteria.getUin() != null && !criteria.getUin().equals("")) {
                Path<String> uin = digitalIds.get("uin");
                predicates.add(criteriaBuilder.equal(uin, criteria.getUin()));
            }

            if (criteria.getPassportNumber() != null &&  !criteria.getPassportNumber().equals("")) {
                Path<String> passportNumber = root.get("passportNumber");
                predicates.add(criteriaBuilder.equal(passportNumber, criteria.getPassportNumber()));
            }

            if (criteria.getGender() != null &&  !criteria.getGender().equals("")) {
                Path<String> gender = root.get("gender");
                predicates.add(criteriaBuilder.equal(gender, criteria.getGender()));
            }

            if(criteria.getLanguage() != null && !criteria.getLanguage().equals("")){
                if(criteria.getLanguage().equals("en")){
                    if(criteria.getApplicantName() != null && !criteria.getApplicantName().equals("")){
                        predicates.add(criteriaBuilder.like(root.get("fullNameEn"), "%" + criteria.getApplicantName().trim() + "%"));
                    }
                } else {
                    if(criteria.getApplicantName() != null && !criteria.getApplicantName().equals("")){
                        predicates.add(criteriaBuilder.like(root.get("fullNameAr"), "%" + criteria.getApplicantName().trim() + "%"));
                    }
                }
            }


           /* if (criteria.getGroupNumber() != null &&  !criteria.getGroupNumber().equals("")) {
                Path<String> referenceNumber = applicantGroup.get("referenceNumber");
                predicates.add(criteriaBuilder.equal(referenceNumber, String.valueOf(companyRefCode) +"_"+criteria.getGroupNumber()));
            }*/

            if(companyTypeCode != null){
                switch (companyTypeCode) {
                    case "ESTABLISHMENT":
                        Path<Integer> estRefCode = root.get("estRefCode");
                        predicates.add(criteriaBuilder.equal(estRefCode, companyRefCode));
                        break;
                    case "MISSION":
                        Path<Integer> missionRefCode = root.get("missionRefCode");
                        predicates.add(criteriaBuilder.equal(missionRefCode, companyRefCode));
                        break;
                    case "SERVICE_GROUP":
                        Path<Integer> serviceGroupMakkahCode = root.get("serviceGroupMakkahCode");
                        Path<Integer> serviceGroupMadinaCode = root.get("serviceGroupMadinaCode");
                        predicates.add(criteriaBuilder.or(criteriaBuilder.equal(serviceGroupMakkahCode, companyRefCode), criteriaBuilder.equal(serviceGroupMadinaCode, companyRefCode)));
                        break;
                    case "INTERNAL_HAJ_COMPANY":
                        Path<Integer> companyCode = root.get("companyCode");
                        predicates.add(criteriaBuilder.equal(companyCode, String.valueOf(companyRefCode) +"_"+companyTypeCode));
                        break;
                    default:
                        predicates.add(criteriaBuilder.equal(root.get("companyCode"), String.valueOf(companyRefCode) +"_"+companyTypeCode));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

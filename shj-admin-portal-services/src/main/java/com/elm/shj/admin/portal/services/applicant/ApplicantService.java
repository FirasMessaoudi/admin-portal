/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.*;
import com.elm.shj.admin.portal.orm.repository.ApplicantContactRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRepository;
import com.elm.shj.admin.portal.services.company.CompanyRitualSeasonLiteService;
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
import org.springframework.transaction.annotation.Transactional;

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

    private final ApplicantRepository applicantRepository;
    private final ApplicantContactRepository applicantContactRepository;
    private final CompanyRitualSeasonLiteService companyRitualSeasonLiteService;
    private final ApplicantRitualService applicantRitualService;
    public final static String SAUDI_MOBILE_NUMBER_REGEX = "^(009665|9665|\\+9665|05|5)([0-9]{8})$";

    /**
     * Find all applicants.
     *
     * @param pageable the current page information
     * @return the list of applicants
     */
    public Page<ApplicantDto> findAll(Pageable pageable) {
        return mapPage(getRepository().findAll(pageable));
    }

    /**
     * Find all applicants without digital IDs
     *
     * @return the list of applicants
     */
    public List<ApplicantDto> findAllWithoutDigitalId() {
        return mapList(((ApplicantRepository) getRepository()).findAllApplicantsWithoutDigitalId());
    }

    /**
     * finds an applicant with the same basic info exists
     *
     * @param applicantBasicInfo the applicant basic info
     * @return the found applicant with the same basic info exists
     */
    public ApplicantDto findByBasicInfo(ApplicantBasicInfoDto applicantBasicInfo) {
        return getMapper().fromEntity(((ApplicantRepository) getRepository()).findByBasicInfo(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(), applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian()), mappingContext);
    }

    /**
     * checks if an applicant exists with the same basic info exists
     *
     * @param applicantBasicInfo the applicant basic info
     * @return if applicant with the same basic info exists
     */
    public boolean existsByBasicInfo(ApplicantBasicInfoDto applicantBasicInfo) {
        return ((ApplicantRepository) getRepository()).existsByBasicInfo(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(), applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian());
    }


    /**
     * Finds an applicant by his uin
     *
     * @param uin the uin of applicant to find
     * @return the found applicant or empty structure
     */
    public Optional<ApplicantDto> findByUin(String uin) {
        JpaApplicant applicant = applicantRepository.findByUin(uin);
        return (applicant != null) ? Optional.of(getMapper().fromEntity(applicant, mappingContext)) : Optional.empty();
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
        CompanyRitualSeasonLiteDto latestCompanyRitualSeason = companyRitualSeasonLiteService.getLatestCompanyRitualSeasonByApplicantUin(Long.parseLong(command.getUin()));
        int updatedRowsCount = 0;
        if (latestCompanyRitualSeason != null) {
            ApplicantRitualDto applicantRitual = applicantRitualService.findByApplicantUinAndCompanyRitualSeasonId(command.getUin(), latestCompanyRitualSeason.getId());

            if (command.getMobileNumber().matches(SAUDI_MOBILE_NUMBER_REGEX)) {
                updatedRowsCount = applicantContactRepository.updateContactLocalNumber(command.getEmail(), command.getCountryCode(), command.getMobileNumber(), applicantId, applicantRitual.getId());
            } else {
                updatedRowsCount = applicantContactRepository.updateContactIntlNumber(command.getEmail(), command.getCountryCode(), command.getMobileNumber(), applicantId, applicantRitual.getId());
            }
            updatedRowsCount += applicantRepository.markAsRegistered(applicantId);

        }
        return updatedRowsCount;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public ApplicantDto save(ApplicantDto applicant) {
        // persist the record
        return super.save(applicant);
    }

    /**
     * Find all applicants having active ritual
     *
     * @return the list of applicants
     */
    public List<ApplicantDto> findAllHavingActiveRitual() {
        return mapList(((ApplicantRepository) getRepository()).findAllApplicantsHavingActiveRitual(new Date()));
    }

    /**
     * Count applicants having active ritual
     *
     * @return the number of applicants
     */
    public long countHavingActiveRitual() {
        return applicantRepository.countHavingActiveRitual(new Date());
    }

    public long countAllByCriteria(ApplicantSearchCriteriaDto applicantSearchCriteria, List<Long> excludedIds) {
        return applicantRepository.count(withApplicantFilter(applicantSearchCriteria, excludedIds));
    }

    public List<ApplicantDto> findAllByCriteria(ApplicantSearchCriteriaDto applicantSearchCriteria, List<Long> excludedIds) {
        return mapList(applicantRepository.findAll(withApplicantFilter(applicantSearchCriteria, excludedIds)));
    }

    public Page<ApplicantDto> findAllByCriteriaAndNotInExcludedIds(ApplicantSearchCriteriaDto applicantSearchCriteria, List<Long> excludedIds, Pageable pageable) {
        return mapPage(applicantRepository.findAll(withApplicantFilter(applicantSearchCriteria, excludedIds), pageable));
    }

    private Specification<JpaApplicant> withApplicantFilter(final ApplicantSearchCriteriaDto criteria, List<Long> excludedIds) {
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


    public List<ApplicantDto> findByIds(List<Long> selectedApplicants) {
        return mapList(applicantRepository.findByIds(selectedApplicants));
    }

    public boolean existsByBasicInfoAndPackageCode(ApplicantBasicInfoDto applicantBasicInfo) {
        return ((ApplicantRepository) getRepository()).findByBasicInfoAndPackageCode(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(), applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian(), applicantBasicInfo.getPackageReferenceNumber());

    }

    @Transactional
    public void updatePreferredLanguage(String uin, String lang) {
        Optional<ApplicantDto> applicant = findByUin(uin);
        log.debug("Applicant ID: {}", applicant.get().getId());
        applicant.ifPresent(applicantDto -> applicantRepository.updatePreferredLanguage(applicantDto.getId(), lang));
    }
}


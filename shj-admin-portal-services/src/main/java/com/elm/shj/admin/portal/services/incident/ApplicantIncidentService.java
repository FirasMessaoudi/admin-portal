/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.incident;

import com.elm.shj.admin.portal.orm.entity.JpaApplicant;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantDigitalId;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantIncident;
import com.elm.shj.admin.portal.orm.entity.JpaIncidentAttachment;
import com.elm.shj.admin.portal.orm.repository.ApplicantIncidentRepository;
import com.elm.shj.admin.portal.orm.repository.IncidentAttachmentRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.sftp.SftpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service handling Applicant Incident operations
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantIncidentService extends GenericService<JpaApplicantIncident, ApplicantIncidentDto, Long> {

    private final ApplicantIncidentRepository applicantIncidentRepository;
    private final SftpService sftpService;
    private final IncidentAttachmentRepository incidentAttachmentRepository;
    private static final SimpleDateFormat REF_NUMBER_FORMAT = new SimpleDateFormat("SSS");
    private static final int REQUEST_REF_NUMBER_LENGTH = 12;
    private static final String APPLICANT_INCIDENTS_CONFIG_PROPERTIES = "applicantIncidentsConfigProperties";

    /**
     * Find all incidents.
     *
     * @param pageable the current page information
     * @return the list of incidents
     */
    public Page<ApplicantIncidentDto> findAll(IncidentSearchCriteriaDto criteria, Pageable pageable) {
        return mapPage(applicantIncidentRepository.findAll(withFilter(criteria), pageable));
    }

    private Specification<JpaApplicantIncident> withFilter(final IncidentSearchCriteriaDto criteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            //Create atomic predicates
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getIncidentNumber() != null && criteria.getIncidentNumber().trim().length() > 0) {
                predicates.add(criteriaBuilder.like(root.get("referenceNumber"), "%" + criteria.getIncidentNumber().trim() + "%"));
            }
            if (criteria.getApplicantId() != null && criteria.getApplicantId().trim().length() > 0) {
                Join<JpaApplicant, JpaApplicantDigitalId> digitalIds = root.join("applicantRitual").join("applicant").join("digitalIds");
                predicates.add(criteriaBuilder.like(digitalIds.get("uin"), "%" + criteria.getApplicantId().trim() + "%"));
            }
            if (criteria.getApplicantName() != null && criteria.getApplicantName().trim().length() > 0) {
                Predicate predicateForFullNameAr = criteriaBuilder.like(root.get("applicantRitual").get("applicant").get("fullNameAr"), "%" + criteria.getApplicantName().trim() + "%");
                Predicate predicateForFullNameEn = criteriaBuilder.like(root.get("applicantRitual").get("applicant").get("fullNameEn"), "%" + criteria.getApplicantName().trim() + "%");
                Predicate predicateForFullNameOrigin = criteriaBuilder.like(root.get("applicantRitual").get("applicant").get("fullNameOrigin"), "%" + criteria.getApplicantName().trim() + "%");
                predicates.add(criteriaBuilder.or(predicateForFullNameAr, predicateForFullNameEn, predicateForFullNameOrigin));
            }
            if (criteria.getIncidentType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("typeCode"), criteria.getIncidentType()));
            }
            if (criteria.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("statusCode"), criteria.getStatus()));
            }
            if (criteria.getCreationDateStart() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), criteria.getCreationDateStart()));
            }
            if (criteria.getCreationDateEnd() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("creationDate"), criteria.getCreationDateEnd()));
            }

            Expression<Date> dateTomeDescOrder = criteriaBuilder.coalesce(root.get("updateDate"), root.get("creationDate"));
            criteriaQuery.orderBy(criteriaBuilder.desc(dateTomeDescOrder));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * finds an incident by its ID
     *
     * @param incidentId the incident id to find
     * @return the found incident or <code>null</code>
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantIncidentDto findById(long incidentId) {
        return findOne(incidentId);
    }

    /**
     * List of applicant related incidents.
     *
     * @param applicantRitualId
     * @return List of applicant related incidents
     */
    public List<ApplicantIncidentDto> listApplicantRelatedIncidents(long applicantRitualId) {
        return mapList(applicantIncidentRepository.findByApplicantRitualId(applicantRitualId));
    }


    /**
     * fetches the original file of the data request
     *
     * @param incidentAttachmentId applicant Incident Attachment Id
     * @return the attachment of the applicant incident
     */
    public Resource downloadApplicantIncidentAttachment(long incidentAttachmentId) throws Exception {
        Optional<JpaIncidentAttachment> incidentAttachment = incidentAttachmentRepository.findById(incidentAttachmentId);
        if (!incidentAttachment.isPresent()) {
            return null;
        }
        return sftpService.downloadFile(incidentAttachment.get().getFilePath(), APPLICANT_INCIDENTS_CONFIG_PROPERTIES);
    }


    /**
     * Updates applicant incident
     *
     * @param incidentId the ID number of the incident to update
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void update(long incidentId, ApplicantIncidentVo applicantIncidentVo) {

        if (EIncidentResolutionType.MARK_AS_RESOLVED.equals(applicantIncidentVo.getOperation())) {
            applicantIncidentRepository.update(incidentId, applicantIncidentVo.getResolutionComment(), EIncidentStatus.RESOLVED.name());
        }
        if (EIncidentResolutionType.MARK_AS_CLOSED.equals(applicantIncidentVo.getOperation())) {
            applicantIncidentRepository.update(incidentId, applicantIncidentVo.getResolutionComment(), EIncidentStatus.CLOSED.name());
        }
    }
}

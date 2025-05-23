/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.incident;

import com.elm.shj.admin.portal.orm.entity.*;
import com.elm.shj.admin.portal.orm.repository.ApplicantIncidentLiteRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantIncidentRepository;
import com.elm.shj.admin.portal.orm.repository.IncidentAttachmentRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.integration.IntegrationService;
import com.elm.shj.admin.portal.services.notification.NotificationRequestService;
import com.elm.shj.admin.portal.services.notification.NotificationTemplateService;
import com.elm.shj.admin.portal.services.sftp.SftpService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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
    private final ApplicantIncidentLiteRepository applicantIncidentLiteRepository;
    private final IntegrationService integrationService;
    private final SftpService sftpService;
    private final IncidentAttachmentRepository incidentAttachmentRepository;
    private final NotificationRequestService notificationRequestService;
    private final NotificationTemplateService notificationTemplateService;
    private static final String RESOLVE_INCIDENT_TEMPLATE_NAME = "RESOLVE_INCIDENT";
    private static final String CLOSE_INCIDENT_TEMPLATE_NAME = "CLOSE_INCIDENT";

    private static final SimpleDateFormat REF_NUMBER_FORMAT = new SimpleDateFormat("SSS");
    private static final int REQUEST_REF_NUMBER_LENGTH = 12;
    private static final String APPLICANT_INCIDENTS_CONFIG_PROPERTIES = "applicantIncidentsConfigProperties";

    @Value("${crm.complaint.update.url}")
    private String crmUpdateComplaintUrl;

    /**
     * Find all incidents.
     *
     * @param pageable the current page information
     * @return the list of incidents
     */
    public Page<ApplicantIncidentDto> findAll(IncidentSearchCriteriaDto criteria, Pageable pageable) {
        log.info("Start findAll with IncidentSearchCriteriaDto: {}", criteria);
        Page<ApplicantIncidentDto> applicantIncidentDtoPage = mapPage(applicantIncidentRepository.findAll(withFilter(criteria), pageable));
        log.info("Finish findAll");
        return applicantIncidentDtoPage;
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
            if (criteria.getFromDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), atStartOfDay(criteria.getFromDate())));
            }
            if (criteria.getToDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("creationDate"), atEndOfDay(criteria.getToDate())));
            }
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("creationDate")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Date atStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    private Date atEndOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
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
        log.info("Start listApplicantRelatedIncidents with applicantRitualId: {}", applicantRitualId);
        List<ApplicantIncidentDto> applicantIncidentDtoList = mapList(applicantIncidentRepository.findByApplicantRitualId(applicantRitualId));
        log.info("Finish listApplicantRelatedIncidents with applicantRitualId: {}", applicantRitualId);
        return applicantIncidentDtoList;
    }

    /**
     * Updates applicant incident
     *
     * @param incidentId the ID number of the incident to update
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void update(long incidentId, ApplicantIncidentVo applicantIncidentVo) throws NotFoundException {
        log.info("Start Updates applicant incident with incidentId: {}", incidentId);
        if (EIncidentResolutionType.MARK_AS_RESOLVED.name().equals(applicantIncidentVo.getOperation())) {
            applicantIncidentRepository.update(incidentId, applicantIncidentVo.getResolutionComment(), EIncidentStatus.RESOLVED.name());
            sendIncidentNotification(incidentId, RESOLVE_INCIDENT_TEMPLATE_NAME);
        }
        if (EIncidentResolutionType.MARK_AS_CLOSED.name().equals(applicantIncidentVo.getOperation())) {
            applicantIncidentRepository.update(incidentId, applicantIncidentVo.getResolutionComment(), EIncidentStatus.CLOSED.name());
            sendIncidentNotification(incidentId, CLOSE_INCIDENT_TEMPLATE_NAME);
        }
        JpaApplicantIncidentLite incident = applicantIncidentLiteRepository.findById(incidentId).orElse(null);
        if (incident.getCrmTicketNumber() != null && !incident.getCrmTicketNumber().isEmpty()) {
            //TODO: Update CRM Complaint status
            ApplicantIncidentComplaintVoCRM applicantComplaintVoCRM = new ApplicantIncidentComplaintVoCRM();
            applicantComplaintVoCRM.setCrmTicketNumber(incident.getCrmTicketNumber());
            applicantComplaintVoCRM.setStatus(EIncidentResolutionType.valueOf(applicantIncidentVo.getOperation()).getCrmCode());
            applicantComplaintVoCRM.setSmartIDTicketNumber(incident.getReferenceNumber());
            applicantComplaintVoCRM.setResolutionComment(applicantIncidentVo.getResolutionComment());
            try {
                integrationService.callCRM(crmUpdateComplaintUrl, HttpMethod.POST, applicantComplaintVoCRM, null,
                        new ParameterizedTypeReference<ComplaintUpdateCRMDto>() {
                        });
                applicantIncidentLiteRepository.updateCRMUpdateStatus(incident.getId());
                log.info("complaint successfully updated #{}", incidentId);
            } catch (Exception e){
                log.error("Failed update the status on CRM of complaint #{}", incidentId);

            }
        }
        log.info("Finish Updates applicant incident with incidentId: {}", incidentId);
    }

    /**
     * Updates applicant complaint
     *
     * @param incidentId the ID number of the incident to update
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void updateByCrm(long incidentId, ApplicantIncidentComplaintVoCRM applicantComplaintVo) throws NotFoundException {
        log.info("Start updateByCrm with incidentId: {}", incidentId);
        if (EIncidentStatus.RESOLVED.getCrmCode().equals(applicantComplaintVo.getStatus())) {
            log.info("Finish updateByCrm in case incident status is RESOLVED");
            applicantIncidentRepository.updateByCrm(incidentId, applicantComplaintVo.getResolutionComment(), EIncidentStatus.RESOLVED.name());
            sendIncidentNotification(incidentId, RESOLVE_INCIDENT_TEMPLATE_NAME);
        } else if (EIncidentStatus.CLOSED.getCrmCode().equals(applicantComplaintVo.getStatus())) {
            log.info("Finish updateByCrm in case incident status is CLOSED");
            applicantIncidentRepository.updateByCrm(incidentId, applicantComplaintVo.getResolutionComment(), EIncidentStatus.CLOSED.name());
            sendIncidentNotification(incidentId, CLOSE_INCIDENT_TEMPLATE_NAME);
        }

    }


    private void sendIncidentNotification(long incidentId, String closeIncidentTemplateName) throws NotFoundException {
        log.info("Start sendIncidentNotification with incidentId: {}", incidentId);
        Optional<NotificationTemplateDto> notificationTemplate = notificationTemplateService.findEnabledNotificationTemplateByNameCode(closeIncidentTemplateName);
        if (notificationTemplate == null || !notificationTemplate.isPresent()) {
            log.error("Failed sendIncidentNotification as no template found for :{}", closeIncidentTemplateName);
            throw new NotFoundException("no Template found for  " + closeIncidentTemplateName);
        }

        com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo applicantIncident = applicantIncidentLiteRepository.findOneLite(incidentId);
        String uin = applicantIncident.getApplicantRitual().getApplicant().getUin();
        String preferredLanguage = applicantIncident.getApplicantRitual().getApplicant().getPreferredLanguage();
        notificationRequestService.sendIncidentNotification(notificationTemplate.get(), uin, preferredLanguage);
        log.info("Finish sendIncidentNotification with incidentId: {}", incidentId);
    }
}

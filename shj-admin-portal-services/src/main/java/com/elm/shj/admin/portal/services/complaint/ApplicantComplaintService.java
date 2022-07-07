/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.complaint;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantComplaint;
import com.elm.shj.admin.portal.orm.entity.JpaComplaintAttachmentLite;
import com.elm.shj.admin.portal.orm.repository.ApplicantComplaintLiteRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantComplaintRepository;
import com.elm.shj.admin.portal.orm.repository.ComplaintAttachmentLiteRepository;
import com.elm.shj.admin.portal.orm.repository.ComplaintAttachmentRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.integration.CrmAuthResponse;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

/**
 * Service handling Applicant Complaint operations
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicantComplaintService extends GenericService<JpaApplicantComplaint, ApplicantComplaintDto, Long> {

    private final WebClient webClient;

    @Value("${crm.auth.url}")
    private String crmAuthUrl;
    @Value("${crm.url}")
    private String crmUrl;
    @Value("${crm.complaint.update.url}")
    private String crmUpdateComplaintUrl;

    @Value("${crm.access.username}")
    private String crmAccessUsername;
    @Value("${crm.access.password}")
    private String crmAccessPassword;

    private final ApplicantComplaintRepository applicantComplaintRepository;
    private final ApplicantComplaintLiteRepository applicantComplaintLiteRepository;
    private final SftpService sftpService;
    private final ComplaintAttachmentRepository complaintAttachmentRepository;
    private final ComplaintAttachmentLiteRepository complaintAttachmentLiteRepository;
    private final NotificationRequestService notificationRequestService;
    private final NotificationTemplateService notificationTemplateService;
    private final IntegrationService integrationService;
    private static final String RESOLVE_INCIDENT_TEMPLATE_NAME = "RESOLVE_COMPLAINT";
    private static final String CLOSE_INCIDENT_TEMPLATE_NAME = "CLOSE_COMPLAINT";

    private static final String APPLICANT_COMPLAINTS_CONFIG_PROPERTIES = "applicantComplaintsConfigProperties";

    /**
     * Find all complaint.
     *
     * @param pageable the current page information
     * @return the list of complaint
     */
    public Page<com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo> findAll(ComplaintSearchCriteriaDto criteria, Long companyRefCode, String companyTypeCode, Pageable pageable) {
        log.info("Start findAll with ComplaintSearchCriteriaDto: {}", criteria);
        Long establishmentRefCode = -1L;
        Long missionRefCode = -1L;
        Long serviceGroupRefCode = -1L;
        String companyCode = "-1";

        if(companyTypeCode.equals(EOrganizerTypes.GOVERNMENT_AGENCY.name())){
            return  new PageImpl<>(Collections.emptyList(), pageable, 0);
        }

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

        Page<com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo> applicantComplaintVoPage = applicantComplaintRepository.findApplicantComplaintFilter(criteria.getComplaintNumber(), criteria.getComplaintType(),
                criteria.getStatus(), criteria.getApplicantName(), atStartOfDay(criteria.getFromDate()), atEndOfDay(criteria.getToDate()),
                criteria.getApplicantId(), companyCode, establishmentRefCode, missionRefCode, serviceGroupRefCode, pageable);
        log.info("Finish findAll");
        return applicantComplaintVoPage;
    }

    private Date atStartOfDay(Date date) {
        if (date != null) {
            LocalDateTime localDateTime = dateToLocalDateTime(date);
            LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
            return localDateTimeToDate(startOfDay);
        } else
            return null;
    }

    private Date atEndOfDay(Date date) {
        if (date != null) {
            LocalDateTime localDateTime = dateToLocalDateTime(date);
            LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
            return localDateTimeToDate(endOfDay);
        } else
            return null;
    }

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * finds an complaint by its ID
     *
     * @param complaintId the complaint id to find
     * @return the found complaint or <code>null</code>
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo findByIdLite(long complaintId) {
        log.info("Start findByIdLite with complaintId: {}", complaintId);
        com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo applicantComplaintVo = applicantComplaintLiteRepository.findOneLite(complaintId);
        log.info("Finish findByIdLite with complaintId: {}", complaintId);
        return applicantComplaintVo;
    }


    /**
     * fetches the original file of the data request
     *
     * @param complaintAttachmentId applicant Complaint Attachment Id
     * @return the attachment of the applicant complaint
     */
    public Resource downloadApplicantComplaintAttachment(long complaintAttachmentId) throws Exception {
        log.info("Start downloadApplicantComplaintAttachment with complaintAttachmentId: {}", complaintAttachmentId);
        Optional<JpaComplaintAttachmentLite> complaintAttachment = complaintAttachmentLiteRepository.findById(complaintAttachmentId);
        if (!complaintAttachment.isPresent()) {
            log.info("Start downloadApplicantComplaintAttachment not found with complaintAttachmentId: {}", complaintAttachmentId);
            return null;
        }
        log.info("Finish downloadApplicantComplaintAttachment with complaintAttachmentId: {}", complaintAttachmentId);
        Resource resource = sftpService.downloadFile(complaintAttachment.get().getFilePath(), APPLICANT_COMPLAINTS_CONFIG_PROPERTIES);
        return resource;
    }

    /**
     * Updates applicant complaint
     *
     * @param complaint the ID number of the complaint to update
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void update(ApplicantComplaintLiteDto complaint, ApplicantComplaintVo applicantComplaintVo) throws NotFoundException {
        log.info("Start update with ApplicantComplaintLiteDto: {}", complaint);
        if (EComplaintResolutionType.RESOLVED.name().equals(applicantComplaintVo.getOperation())) {
            applicantComplaintRepository.update(complaint.getId(), applicantComplaintVo.getResolutionComment(), EComplaintStatus.RESOLVED.name());
            sendComplaintNotification(complaint.getId(), RESOLVE_INCIDENT_TEMPLATE_NAME);
        } else if (EComplaintResolutionType.CLOSED.name().equals(applicantComplaintVo.getOperation())) {
            applicantComplaintRepository.update(complaint.getId(), applicantComplaintVo.getResolutionComment(), EComplaintStatus.CLOSED.name());
            sendComplaintNotification(complaint.getId(), CLOSE_INCIDENT_TEMPLATE_NAME);
        }

        if (complaint.getCrmTicketNumber() != null && !complaint.getCrmTicketNumber().isEmpty()) {
            //TODO: Update CRM Complaint status
            ApplicantIncidentComplaintVoCRM applicantComplaintVoCRM = new ApplicantIncidentComplaintVoCRM();
            applicantComplaintVoCRM.setCrmTicketNumber(complaint.getCrmTicketNumber());
            applicantComplaintVoCRM.setStatus(EComplaintResolutionType.valueOf(applicantComplaintVo.getOperation()).getCrmCode());
            applicantComplaintVoCRM.setSmartIDTicketNumber(complaint.getReferenceNumber());
            applicantComplaintVoCRM.setResolutionComment(applicantComplaintVo.getResolutionComment());
            try {
                integrationService.callCRM(crmUpdateComplaintUrl, HttpMethod.POST, applicantComplaintVoCRM, null,
                        new ParameterizedTypeReference<ComplaintUpdateCRMDto>() {
                        });
                applicantComplaintRepository.updateCRMUpdateStatus(complaint.getId());
                log.info("complaint successfully updated #{}", complaint.getId());
            } catch (Exception e){
                log.error("Failed update the status on CRM of complaint #{}", complaint.getId());

            }
        }
    }

    /**
     * Updates applicant complaint
     *
     * @param complaintId the ID number of the complaint to update
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void updateByCrm(long complaintId, ApplicantIncidentComplaintVoCRM applicantComplaintVo) throws NotFoundException {
        log.info("Start updateByCrm with ApplicantIncidentComplaintVoCRM: {}", applicantComplaintVo);
        if (EComplaintResolutionType.RESOLVED.getCrmCode().equals(applicantComplaintVo.getStatus())) {
            log.info("Finish updateByCrm in case resolution type is RESOLVED");
            applicantComplaintRepository.updateByCrm(complaintId, applicantComplaintVo.getResolutionComment(), EComplaintStatus.RESOLVED.name());
            sendComplaintNotification(complaintId, RESOLVE_INCIDENT_TEMPLATE_NAME);
        } else if (EComplaintResolutionType.CLOSED.getCrmCode().equals(applicantComplaintVo.getStatus())) {
            log.info("Finish updateByCrm in case resolution type is CLOSED");
            applicantComplaintRepository.updateByCrm(complaintId, applicantComplaintVo.getResolutionComment(), EComplaintStatus.CLOSED.name());
            sendComplaintNotification(complaintId, CLOSE_INCIDENT_TEMPLATE_NAME);
        }


    }

    private void sendComplaintNotification(long complaintId, String closeComplaintTemplateName) throws NotFoundException {
        log.info("Start sendComplaintNotification with closeComplaintTemplateName: {}, complaintId: {}", closeComplaintTemplateName, complaintId);
        Optional<NotificationTemplateDto> notificationTemplate = notificationTemplateService.findEnabledNotificationTemplateByNameCode(closeComplaintTemplateName);
        if (notificationTemplate == null || !notificationTemplate.isPresent()) {
            log.info("Finish sendComplaintNotification not found with closeComplaintTemplateName: {}, complaintId: {}", closeComplaintTemplateName, complaintId);
            throw new NotFoundException("no Template found for  " + closeComplaintTemplateName);
        }

        com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo applicantComplaint = applicantComplaintLiteRepository.findOneLite(complaintId);
        String uin = applicantComplaint.getApplicantRitual().getApplicant().getUin();
        String preferredLanguage = applicantComplaint.getApplicantRitual().getApplicant().getPreferredLanguage();
        notificationRequestService.sendComplaintNotification(notificationTemplate.get(), uin, preferredLanguage);
        log.info("Finish sendComplaintNotification with closeComplaintTemplateName: {}, complaintId: {}", closeComplaintTemplateName, complaintId);
    }

    public <B, R> R callCRM(String serviceRelativeUrl, HttpMethod httpMethod, B bodyToSend,
                            ParameterizedTypeReference<R> responseTypeReference)  {
        CrmAuthResponse accessTokenWsResponse = webClient.post().uri(crmUrl + crmAuthUrl)
                .body(BodyInserters.fromValue(LoginRequestCRM.builder().username(crmAccessUsername).password(crmAccessPassword).build()))
                .retrieve().bodyToMono(CrmAuthResponse.class).block();
        if (CrmAuthResponse.ECrmResponseStatus.SUCCESS.getCode() != accessTokenWsResponse.getResponseCode()) {
            // cannot authenticate, throw an exception
            // TODO: handle failure of authentication .
        }
        // check if no body
        if (bodyToSend == null) {

            return webClient.method(httpMethod).uri(crmUrl + serviceRelativeUrl).headers(header -> header.setBearerAuth(accessTokenWsResponse.getToken()))
                    .retrieve().bodyToMono(responseTypeReference).block();
        }
        return webClient.method(httpMethod).uri(crmUrl + serviceRelativeUrl).headers(header -> header.setBearerAuth(accessTokenWsResponse.getToken()))
                .body(BodyInserters.fromValue(bodyToSend)).retrieve().bodyToMono(responseTypeReference).block();
    }


}

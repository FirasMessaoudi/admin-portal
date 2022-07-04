/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.incident;

import com.elm.shj.admin.portal.orm.entity.JpaIncidentAttachmentLite;
import com.elm.shj.admin.portal.services.dto.IncidentAttachmentLiteDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.sftp.SftpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Service handling Applicant Complaint Attachment operations
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IncidentAttachmentLiteService extends GenericService<JpaIncidentAttachmentLite, IncidentAttachmentLiteDto, Long> {

    private final SftpService sftpService;
    private static final String APPLICANT_INCIDENTS_CONFIG_PROPERTIES = "applicantIncidentsConfigProperties";

    public IncidentAttachmentLiteDto save(IncidentAttachmentLiteDto dto){
        return getMapper().fromEntity(getRepository().save(getMapper().toEntity(dto, mappingContext)), mappingContext);
    }

    public Resource downloadApplicantIncidentAttachment(long incidentAttachmentId) throws Exception {
        log.info("Start downloadApplicantIncidentAttachment with incidentAttachmentId: {}", incidentAttachmentId);
        Optional<JpaIncidentAttachmentLite> incidentAttachment = getRepository().findById(incidentAttachmentId);
        if (!incidentAttachment.isPresent()) {
            log.info("Finish downloadApplicantIncidentAttachment not found with incidentAttachmentId: {}", incidentAttachmentId);
            return null;
        }
        log.info("Finish downloadApplicantIncidentAttachment with incidentAttachmentId: {}", incidentAttachmentId);
        return sftpService.downloadFile(incidentAttachment.get().getFilePath(), APPLICANT_INCIDENTS_CONFIG_PROPERTIES);
    }
}


/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.incident;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantIncident;
import com.elm.shj.admin.portal.orm.entity.JpaIncidentAttachment;
import com.elm.shj.admin.portal.orm.repository.ApplicantIncidentRepository;
import com.elm.shj.admin.portal.orm.repository.IncidentAttachmentRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantIncidentDto;
import com.elm.shj.admin.portal.services.dto.EIncidentStatus;
import com.elm.shj.admin.portal.services.dto.IncidentAttachmentDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.sftp.SftpService;
import com.jcraft.jsch.JSchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
     * List of applicant related incidents.
     * @param  applicantRitualId
     * @return  List of applicant related incidents
     */

    public List<ApplicantIncidentDto> listApplicantRelatedIncidents(long applicantRitualId){
        return  mapList(applicantIncidentRepository.findByApplicantRitualId(applicantRitualId));
    }



    /**
     * {@inheritDoc}
     */
    @Transactional
    public ApplicantIncidentDto addApplicantIncident(ApplicantIncidentDto  applicantIncidentDto, MultipartFile attachment) {
         // generate request reference
        String referenceNumber= generateReferenceNumber();
        // generate and set reference number
        applicantIncidentDto.setReferenceNumber(referenceNumber);
        applicantIncidentDto.setStatusCode(EIncidentStatus.UNDER_PROCESSING.name());
        // upload the file in the SFTP
        try {
            if(attachment!=null && !attachment.isEmpty() && attachment.getSize()>0){
                // generate file and folder names to be uploaded/created in SFTP

                Path p = Paths.get(attachment.getOriginalFilename());
                String sftpPath = sftpService.generateSftpFilePath(p.getFileName().toString(), referenceNumber, false);
                List<IncidentAttachmentDto> incidentAttachmentList = new ArrayList<>();
                IncidentAttachmentDto incidentAttachmentDto = new IncidentAttachmentDto();
                incidentAttachmentDto.setFilePath(sftpPath);
                incidentAttachmentDto.setApplicantIncident(applicantIncidentDto);
                incidentAttachmentList.add(incidentAttachmentDto);
                applicantIncidentDto.setIncidentAttachments(incidentAttachmentList);
                sftpService.uploadFile(sftpPath, attachment.getInputStream(),APPLICANT_INCIDENTS_CONFIG_PROPERTIES);
                log.info("file uploaded successfully to: {}", sftpPath);
            }

        } catch (JSchException e) {
            log.error("Unable to open attached file", e);
            throw new IllegalArgumentException("Unable to open attached file");
        } catch (IOException ioe) {
            log.error("Unable to upload file to SFTP", ioe);
            throw new IllegalArgumentException("Unable to upload file to SFTP");
        }
        // persist the request
        ApplicantIncidentDto createdApplicantIncident = super.save(applicantIncidentDto);
        log.info("applicant incident created successfully with id# {}", createdApplicantIncident.getId());
        // return the persisted object
        return createdApplicantIncident;
    }


    /**
     * fetches the original file of the data request
     *
     * @param incidentAttachmentId applicant Incident Attachment Id
     * @return the attachment of the applicant incident
     */
    public Resource downloadApplicantIncidentAttachment(long incidentAttachmentId) throws Exception {
       Optional<JpaIncidentAttachment> incidentAttachment= incidentAttachmentRepository.findById(incidentAttachmentId);
        if (!incidentAttachment.isPresent()) {
            return null;
        }
        return sftpService.downloadFile(incidentAttachment.get().getFilePath(),APPLICANT_INCIDENTS_CONFIG_PROPERTIES);
    }
    /**
     * Generates a unique identifier for the applicant incident
     *
     * @return a unique identifier for the applicant incident
     */
    public String generateReferenceNumber() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .filteredBy(t -> t >= '0' && t <= '9')
                .build();
        return generator.generate(REQUEST_REF_NUMBER_LENGTH - 6) + REF_NUMBER_FORMAT.format(new Date());
    }

}

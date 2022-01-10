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
import com.elm.shj.admin.portal.services.sftp.SftpService;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import com.jcraft.jsch.JSchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
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
public class ApplicantIncidentLiteService extends GenericService<JpaApplicantIncidentLite, ApplicantIncidentLiteDto, Long> {


    private final SftpService sftpService;
    private final  ApplicantIncidentLiteRepository applicantIncidentLiteRepository ;
    private static final int REQUEST_REF_NUMBER_LENGTH = 12;
    private static final String APPLICANT_INCIDENTS_CONFIG_PROPERTIES = "applicantIncidentsConfigProperties";
    private static ThreadLocal<List<String>> threadLocalLatestSerialList = ThreadLocal.withInitial(() -> new ArrayList<>());



    /**
     * finds an incident by its ID
     *
     * @param incidentId the incident id to find
     * @return the found incident or <code>null</code>
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantIncidentLiteDto findById(long incidentId) {
        return findOne(incidentId);
    }



    /**
     * {@inheritDoc}
     */
    @Transactional
    public ApplicantIncidentLiteDto addApplicantIncident(ApplicantIncidentLiteDto  applicantIncidentLiteDto, MultipartFile attachment) {
        // generate request reference
        String referenceNumber = generateReferenceNumber();
        // generate and set reference number
        applicantIncidentLiteDto.setReferenceNumber(referenceNumber);
        applicantIncidentLiteDto.setStatusCode(EIncidentStatus.UNDER_PROCESSING.name());

        // upload the file in the SFTP
        try {
            if (attachment != null && !attachment.isEmpty() && attachment.getSize() > 0) {
                // generate file and folder names to be uploaded/created in SFTP

                Path p = Paths.get(attachment.getOriginalFilename());
                String sftpPath = sftpService.generateSftpFilePath(p.getFileName().toString(), referenceNumber, false);
                List<IncidentAttachmentLiteDto> incidentAttachmentList = new ArrayList<>();
                IncidentAttachmentLiteDto incidentAttachmentDto = new IncidentAttachmentLiteDto();
                incidentAttachmentDto.setFilePath(sftpPath);
                incidentAttachmentDto.setApplicantIncidentLite(applicantIncidentLiteDto);
                incidentAttachmentList.add(incidentAttachmentDto);
                applicantIncidentLiteDto.setIncidentAttachments(incidentAttachmentList);
                sftpService.uploadFile(sftpPath, attachment.getInputStream(), APPLICANT_INCIDENTS_CONFIG_PROPERTIES);
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
        ApplicantIncidentLiteDto createdApplicantIncident = super.save(applicantIncidentLiteDto);
        log.info("applicant incident created successfully with id# {}", createdApplicantIncident.getId());
        // return the persisted object
        return createdApplicantIncident;
    }


    /**
     * Generates a unique identifier for the applicant incident
     *
     * @return a unique identifier for the applicant incident
     */
    public String generateReferenceNumber() {
        String referenceNumPrefix = String.valueOf(DateUtils.getCurrentHijriYear());
        threadLocalLatestSerialList.get().addAll(0, applicantIncidentLiteRepository.fetchReferenceNumByReferenceNumLike(referenceNumPrefix));
        long nextSequence = CollectionUtils.isEmpty(threadLocalLatestSerialList.get()) ? 1 : Long.parseLong(threadLocalLatestSerialList.get().get(0)) + 1;
        String serialDigits = StringUtils.leftPad(String.valueOf(nextSequence), 8, "0");
        return referenceNumPrefix+ serialDigits  ;
    }

    public Boolean validateFileType(String contentType){
        switch (contentType){
            case MediaType.IMAGE_JPEG_VALUE:
                return true;
            case MediaType.IMAGE_PNG_VALUE:
                return true;
            case MediaType.IMAGE_GIF_VALUE:
                return true;
            case Constants.VIDEO_TYPE_FLV:
                return true;
            case Constants.VIDEO_TYPE_MP4:
                return true;
            case Constants.VIDEO_TYPE_3GP:
                return true;
            case Constants.VIDEO_TYPE_MOV:
                return true;
            case Constants.VIDEO_TYPE_AVI:
                return true;
            case Constants.VIDEO_TYPE_WMV:
                return true;
            default:
                return false;
        }
    }

}

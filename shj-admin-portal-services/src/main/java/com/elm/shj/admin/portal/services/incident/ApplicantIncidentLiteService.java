/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.incident;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantIncidentLite;
import com.elm.shj.admin.portal.orm.repository.ApplicantIncidentLiteRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.integration.IntegrationService;
import com.elm.shj.admin.portal.services.location.Location;
import com.elm.shj.admin.portal.services.location.LocationService;
import com.elm.shj.admin.portal.services.sftp.SftpService;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import com.elm.shj.admin.portal.services.zone.AreaLayerService;
import com.jcraft.jsch.JSchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.shaded.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Value("${incident.file.allowed.extensions}")
    private String allowedFileExtensions;

    @Value("${incident.file.allowed.max.size}")
    private long maxFileSize;

    private final SftpService sftpService;
    private final  ApplicantIncidentLiteRepository applicantIncidentLiteRepository ;
    private static final int REQUEST_REF_NUMBER_LENGTH = 12;
    private static final String APPLICANT_INCIDENTS_CONFIG_PROPERTIES = "applicantIncidentsConfigProperties";
    private static ThreadLocal<List<String>> threadLocalLatestSerialList = ThreadLocal.withInitial(() -> new ArrayList<>());
    private final AreaLayerService areaLayerService;
    private final LocationService locationService;
    private final IntegrationService integrationService;
    private final IncidentAttachmentLiteService incidentAttachmentLiteService;

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
        log.info("Start addApplicantIncident with ApplicantIncidentLiteDto: {}", applicantIncidentLiteDto);
        // generate request reference
        String referenceNumber = generateReferenceNumber();
        // generate and set reference number
        applicantIncidentLiteDto.setReferenceNumber(referenceNumber);
        applicantIncidentLiteDto.setStatusCode(EIncidentStatus.UNDER_PROCESSING.name());
        //  lat and long
        List<AreaLayerDto> areaLayers = areaLayerService.findAll();

        if (applicantIncidentLiteDto.getLocationLat() != null && applicantIncidentLiteDto.getLocationLat() != null) {
            Location incidentLocation = new Location();
            incidentLocation.setLat(applicantIncidentLiteDto.getLocationLat());
            incidentLocation.setLng(applicantIncidentLiteDto.getLocationLng());

            List<AreaLayerDto> searchAreas = areaLayers
                    .stream()
                    .filter(area-> area.getParentLayerId() == null)
                    .collect(Collectors.toList());
            while (!searchAreas.isEmpty()) {
                AreaLayerDto area = searchAreas.remove(0);
                List<Location> locations = Stream
                        .of(area.getLayer().split("-"))
                        .map(stringPoint -> {
                            Location location = new Location();
                            location.setLat(Double.parseDouble(stringPoint.split(",")[0]));
                            location.setLng(Double.parseDouble(stringPoint.split(",")[1]));
                            return location;
                        }).collect(Collectors.toList());
                if (locationService.isInside(locations.toArray(Location[]::new),incidentLocation)) {
                    applicantIncidentLiteDto.setAreaCode(area.getAreaCode());
                    searchAreas = areaLayers
                            .stream()
                            .filter(a -> a.getParentLayerId() != null)
                            .filter(a -> a.getParentLayerId().longValue() == area.getId())
                            .collect(Collectors.toList());
                }
            }
        }

        IncidentAttachmentLiteDto incidentAttachmentDto = null;
        // upload the file in the SFTP
        try {
            if (attachment != null && !attachment.isEmpty() && attachment.getSize() > 0) {
                // generate file and folder names to be uploaded/created in SFTP

                Path p = Paths.get(attachment.getOriginalFilename());
                String sftpPath = sftpService.generateSftpFilePath(p.getFileName().toString(), referenceNumber, false);
                incidentAttachmentDto = new IncidentAttachmentLiteDto();
                incidentAttachmentDto.setFilePath(sftpPath);
                incidentAttachmentDto.setApplicantIncident(applicantIncidentLiteDto);
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

        if (incidentAttachmentDto != null){
            incidentAttachmentDto.setApplicantIncident(new ApplicantIncidentLiteDto(createdApplicantIncident.getId()));
            createdApplicantIncident.setIncidentAttachment(incidentAttachmentLiteService.save(incidentAttachmentDto));
        }
        integrationService.callCRMIncident(createdApplicantIncident);
        log.info("Finish addApplicantIncident Successfully");
        return createdApplicantIncident;
    }


    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantIncidentLiteDto findByCrmTicketNumberOrSmartIDTicketNumber(String crmTicketNumber, String smartIDTicketNumber) {
        log.info("Start findByCrmTicketNumberOrSmartIDTicketNumber with crmTicketNumber: {} or smartIDTicketNumber: {}", crmTicketNumber, smartIDTicketNumber);
        ApplicantIncidentLiteDto applicantIncidentLiteDto = getMapper().fromEntity(applicantIncidentLiteRepository.findByCrmTicketNumberOrReferenceNumber(crmTicketNumber, smartIDTicketNumber), mappingContext);
        log.info("Finish findByCrmTicketNumberOrSmartIDTicketNumber with crmTicketNumber: {} or smartIDTicketNumber: {}", crmTicketNumber, smartIDTicketNumber);
        return applicantIncidentLiteDto;
    }

    /**
     * Generates a unique identifier for the applicant incident
     *
     * @return a unique identifier for the applicant incident
     */
    public String generateReferenceNumber() {
        log.info("Start generateReferenceNumber");
        String referenceNumPrefix = String.valueOf(DateUtils.getCurrentHijriYear());
        threadLocalLatestSerialList.get().addAll(0, applicantIncidentLiteRepository.fetchReferenceNumByReferenceNumLike(referenceNumPrefix));
        long nextSequence = CollectionUtils.isEmpty(threadLocalLatestSerialList.get()) ? 1 : Long.parseLong(threadLocalLatestSerialList.get().get(0)) + 1;
        String serialDigits = StringUtils.leftPad(String.valueOf(nextSequence), 8, "0");
        log.info("Finish generateReferenceNumber");
        return referenceNumPrefix+ serialDigits  ;
    }

    public Boolean validateFileExtension(String fileName){
        String fileExtension = FilenameUtils.getExtension(fileName);
        return Arrays.stream(allowedFileExtensions.split(",")).anyMatch(fileExtension:: equalsIgnoreCase);
    }

    public Boolean validateFileSize(long fileSize){
        return fileSize > maxFileSize ? false : true;
    }

    /**
     * List of applicant related incidents.
     *
     * @param applicantRitualId
     * @return List of applicant related incidents
     */
    public List<ApplicantIncidentLiteDto> listApplicantRelatedIncidents(long applicantRitualId) {
        log.info("Start listApplicantRelatedIncidents with applicantRitualId: {}", applicantRitualId);
        List<ApplicantIncidentLiteDto> applicantIncidentLiteDtoList = mapList(applicantIncidentLiteRepository.findByApplicantRitualId(applicantRitualId));
        log.info("Finish listApplicantRelatedIncidents with applicantRitualId: {}", applicantRitualId);
        return applicantIncidentLiteDtoList;
    }
}


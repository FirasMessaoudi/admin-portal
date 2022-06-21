/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.complaint;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantComplaintLite;
import com.elm.shj.admin.portal.orm.repository.ApplicantComplaintLiteRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
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
 * Service handling Applicant Complaint operations
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicantComplaintLiteService extends GenericService<JpaApplicantComplaintLite, ApplicantComplaintLiteDto, Long> {

    private final ApplicantComplaintLiteRepository applicantComplaintLiteRepository;

    @Value("${incident.file.allowed.extensions}")
    private String allowedFileExtensions;

    @Value("${incident.file.allowed.max.size}")
    private long maxFileSize;

    private final AreaLayerService areaLayerService;
    private final LocationService locationService;
    private final SftpService sftpService;

    private static ThreadLocal<List<String>> threadLocalLatestSerialList = ThreadLocal.withInitial(() -> new ArrayList<>());

    private static final String APPLICANT_COMPLAINTS_CONFIG_PROPERTIES = "applicantComplaintsConfigProperties";

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantComplaintLiteDto findByCrmTicketNumber(String crmTicketNumber) {
        return getMapper().fromEntity(applicantComplaintLiteRepository.findByCrmTicketNumber(crmTicketNumber), mappingContext);
    }
    /**
     * {@inheritDoc}
     */
    @Transactional
    public ApplicantComplaintLiteDto addApplicantComplaint(ApplicantComplaintLiteDto  applicantComplaintLiteDto, MultipartFile attachment) {
        // generate request reference
        String referenceNumber = generateReferenceNumber();
        // generate and set reference number
        applicantComplaintLiteDto.setReferenceNumber(referenceNumber);
        applicantComplaintLiteDto.setStatusCode(EComplaintStatus.UNDER_PROCESSING.getCode());
        //  lat and long
        List<AreaLayerDto> areaLayers = areaLayerService.findAll();

        if (applicantComplaintLiteDto.getLocationLat() != null && applicantComplaintLiteDto.getLocationLat() != null) {
            Location complaintLocation = new Location();
            complaintLocation.setLat(applicantComplaintLiteDto.getLocationLat());
            complaintLocation.setLng(applicantComplaintLiteDto.getLocationLng());

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
                if (locationService.isInside(locations.toArray(Location[]::new),complaintLocation)) {
                    applicantComplaintLiteDto.setAreaCode(area.getAreaCode());
                    searchAreas = areaLayers
                            .stream()
                            .filter(a -> a.getParentLayerId() != null)
                            .filter(a -> a.getParentLayerId().longValue() == area.getId())
                            .collect(Collectors.toList());
                }
            }
        }

        // upload the file in the SFTP
        try {
            if (attachment != null && !attachment.isEmpty() && attachment.getSize() > 0) {
                // generate file and folder names to be uploaded/created in SFTP

                Path p = Paths.get(attachment.getOriginalFilename());
                String sftpPath = sftpService.generateSftpFilePath(p.getFileName().toString(), referenceNumber, false);
                List<ComplaintAttachmentLiteDto> complaintAttachmentList = new ArrayList<>();
                ComplaintAttachmentLiteDto complaintAttachmentDto = new ComplaintAttachmentLiteDto();
                complaintAttachmentDto.setFilePath(sftpPath);
                complaintAttachmentDto.setApplicantComplaintLite(applicantComplaintLiteDto);
                complaintAttachmentList.add(complaintAttachmentDto);
                applicantComplaintLiteDto.setComplaintAttachments(complaintAttachmentList);
                sftpService.uploadFile(sftpPath, attachment.getInputStream(), APPLICANT_COMPLAINTS_CONFIG_PROPERTIES);
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
        ApplicantComplaintLiteDto createdApplicantComplaint = super.save(applicantComplaintLiteDto);
        log.info("applicant complaint created successfully with id# {}", createdApplicantComplaint.getId());
        // return the persisted object
        return createdApplicantComplaint;
    }
    

    public Boolean validateFileExtension(String fileName){
        String fileExtension = FilenameUtils.getExtension(fileName);
        return Arrays.stream(allowedFileExtensions.split(",")).anyMatch(fileExtension:: equalsIgnoreCase);
    }

    public Boolean validateFileSize(long fileSize){
        return fileSize > maxFileSize ? false : true;
    }

    /**
     * Generates a unique identifier for the applicant incident
     *
     * @return a unique identifier for the applicant incident
     */
    public String generateReferenceNumber() {
        String referenceNumPrefix = String.valueOf(DateUtils.getCurrentHijriYear());
        threadLocalLatestSerialList.get().addAll(0, applicantComplaintLiteRepository.fetchReferenceNumByReferenceNumLike(referenceNumPrefix));
        long nextSequence = CollectionUtils.isEmpty(threadLocalLatestSerialList.get()) ? 1 : Long.parseLong(threadLocalLatestSerialList.get().get(0)) + 1;
        String serialDigits = StringUtils.leftPad(String.valueOf(nextSequence), 8, "0");
        return referenceNumPrefix+ serialDigits  ;
    }

}


/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.dto.ApplicantIncidentLiteDto;
import com.elm.shj.admin.portal.services.dto.ECity;
import com.elm.shj.admin.portal.services.dto.IncidentTypeLookupDto;
import com.elm.shj.admin.portal.services.incident.ApplicantIncidentLiteService;
import com.elm.shj.admin.portal.services.incident.ApplicantIncidentService;
import com.elm.shj.admin.portal.services.incident.IncidentAttachmentLiteService;
import com.elm.shj.admin.portal.services.lookup.IncidentTypeLookupService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Objects;

/**
 * Controller for exposing web services for external party for applicant incidents.
 *
 * @author ahmad Ali
 * @since 1.1.0
 */
@CrossOrigin(
        originPatterns = "*",
        maxAge = 3600,
        exposedHeaders = {"Authorization", JwtTokenService.CALLER_TYPE_HEADER_NAME, JwtTokenService.TOKEN_COOKIE_NAME},
        allowCredentials = "true"
)
@Slf4j
@RestController
@RequestMapping(Navigation.API_INCIDENTS_INTEGRATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IncidentWsController {

    private static final int MIN_GEO_CORDINATES = -90;
    private static final int MAX_GEO_CORDINATES = 90;

    private final ApplicantIncidentService applicantIncidentService;
    private final IncidentAttachmentLiteService incidentAttachmentLiteService;
    private final ApplicantIncidentLiteService applicantIncidentLiteService;
    private final IncidentTypeLookupService incidentTypeLookupService;

    /**
     * Downloads applicant incident attachment
     *
     * @param attachmentId data request Id
     * @return WsResponse of  the saved incident attachment
     */
    @GetMapping("/attachment/{attachmentId}")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable long attachmentId) throws Exception {
        log.info("Start downloadAttachment attachmentId: {} ", attachmentId);
        Resource attachment = incidentAttachmentLiteService.downloadApplicantIncidentAttachment(attachmentId);

        if (attachment != null) {
            String attachmentName = "img.jpg";
            if (Objects.requireNonNull(attachment.getDescription()).contains("[")) {
                attachmentName = attachment.getDescription().split("\\[")[1].replaceAll("]", "");
            }

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachmentName + "\"");
            log.info("Finish downloadAttachment attachmentName: {} ", attachmentName);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachmentName + "\"")
                    .body(attachment);
        }
        return null;
    }

    /**
     * Creates a new applicant incident
     *
     * @param applicantIncidentRequest applicant incident details
     * @return WsResponse of the persisted applicant incident
     */
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<WsResponse<?>> create(@RequestPart("incident") @Valid ApplicantIncidentLiteDto applicantIncidentRequest,
                                                @RequestPart(value = "attachment", required = false) MultipartFile incidentAttachment) {

        log.info("Start create Incident ApplicantIncidentLiteDto ReferenceNumber: {}", applicantIncidentRequest.getReferenceNumber());
        if (incidentAttachment != null) {
            log.debug("create Incident without attachment");
            //validate file type, allow only images and video
            if (!incidentAttachment.getOriginalFilename().equals("") && !applicantIncidentLiteService.validateFileExtension(incidentAttachment.getOriginalFilename())) {
                log.info("Finish create Incident {}, {} ","FAILURE", WsError.EWsError.INVALID_FILE_EXTENSION.getCode());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(WsError.builder().error(WsError.EWsError.INVALID_FILE_EXTENSION.getCode()).build()).build());
            }
            //validate file size, max size is allowed 15MB
            if (!incidentAttachment.getOriginalFilename().equals("") && !applicantIncidentLiteService.validateFileSize(incidentAttachment.getSize() / (1024 * 1024))) {
                log.info("Finish create Incident {}, {} ","FAILURE", WsError.EWsError.ExCEED_MAX_SIZE.getCode());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(WsError.builder().error(WsError.EWsError.ExCEED_MAX_SIZE.getCode()).build()).build());
            }
        }
        if (applicantIncidentRequest.getLocationLat() != null && applicantIncidentRequest.getLocationLng() != null) {
            log.debug("create Incident applicantIncidentRequest Location not null");
            // validate latitude cordinates, it should be between -90 and +90
            if (applicantIncidentRequest.getLocationLat().intValue() < MIN_GEO_CORDINATES || applicantIncidentRequest.getLocationLat().intValue() > MAX_GEO_CORDINATES) {
                log.info("Finish create Incident {}, {} ","FAILURE", WsError.EWsError.INVALID_LOCATION_ENTRIES.getCode());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(WsError.builder().error(WsError.EWsError.INVALID_LOCATION_ENTRIES.getCode()).build()).build());
            }
            // validate longitude cordinates, it should be between -90 and +90
            if (applicantIncidentRequest.getLocationLng().intValue() < MIN_GEO_CORDINATES || applicantIncidentRequest.getLocationLng().intValue() > MAX_GEO_CORDINATES) {
                log.info("Finish create Incident {}, {} ","FAILURE", WsError.EWsError.INVALID_LOCATION_ENTRIES.getCode());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(WsError.builder().error(WsError.EWsError.INVALID_LOCATION_ENTRIES.getCode()).build()).build());
            }
        }

        // validate camp number, it should be provided if city is holy sites
        if (applicantIncidentRequest.getCity().equals(ECity.HOLY_SITES.name()) && (applicantIncidentRequest.getCampNumber() == null || applicantIncidentRequest.getCampNumber().isEmpty())) {
            log.info("Finish create Incident {}, {} ","FAILURE", WsError.EWsError.CAMP_NUMBER_NOT_PROVIDED.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(WsError.builder().error(WsError.EWsError.CAMP_NUMBER_NOT_PROVIDED.getCode()).build()).build());
        }

        IncidentTypeLookupDto incidentTypeLookupDto = incidentTypeLookupService.findByCode(applicantIncidentRequest.getTypeCode());
        if (incidentTypeLookupDto == null) {
            log.info("Finish create Incident {}, {} ","FAILURE", WsError.EWsError.INCIDENT_TYPE_NOT_FOUND.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(WsError.builder().error(WsError.EWsError.INCIDENT_TYPE_NOT_FOUND.getCode()).build()).build());
        }
        ApplicantIncidentLiteDto applicantIncidentLiteDto = applicantIncidentLiteService.addApplicantIncident(applicantIncidentRequest, incidentAttachment);
        log.info("Finish create Incident {}, ReferenceNumber: {} ","SUCCESS",applicantIncidentLiteDto.getReferenceNumber());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantIncidentLiteDto).build());

    }


    @GetMapping("/find/{id}")
    private ResponseEntity<WsResponse<?>> findById(@PathVariable Long id){

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(applicantIncidentLiteService.findById(id)).build());
    }

}

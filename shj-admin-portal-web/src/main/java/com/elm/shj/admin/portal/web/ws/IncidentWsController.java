/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.dto.ApplicantIncidentLiteDto;
import com.elm.shj.admin.portal.services.dto.IncidentTypeLookupDto;
import com.elm.shj.admin.portal.services.incident.ApplicantIncidentLiteService;
import com.elm.shj.admin.portal.services.incident.ApplicantIncidentService;
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

    private final ApplicantIncidentService applicantIncidentService;
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
        log.info("Downloading incident attachment with id# {} ", attachmentId);
        Resource attachment = applicantIncidentService.downloadApplicantIncidentAttachment(attachmentId);

        if (attachment != null) {
            String attachmentName = "img.jpg";
            if (Objects.requireNonNull(attachment.getDescription()).contains("[")) {
                attachmentName = attachment.getDescription().split("\\[")[1].replaceAll("]", "");
            }

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachmentName + "\"");
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
                                                @RequestPart(value = "attachment", required = false) MultipartFile incidentAttachment) throws Exception {

        log.info("adding  applicant incident");
        IncidentTypeLookupDto incidentTypeLookupDto= incidentTypeLookupService.findByCode(applicantIncidentRequest.getTypeCode());
       if(incidentTypeLookupDto ==null)
           return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE).body(WsError.builder().error(WsError.EWsError.INCIDENT_TYPE_NOT_FOUND).build()).build());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(applicantIncidentLiteService.addApplicantIncident(applicantIncidentRequest, incidentAttachment)).build());
    }
}

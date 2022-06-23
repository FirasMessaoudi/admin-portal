/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.incident;

import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.incident.ApplicantIncidentService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Main controller for incident management page
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@RestController
@RequestMapping(Navigation.API_INCIDENTS)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IncidentManagementController {

    private final ApplicantIncidentService applicantIncidentService;

    /**
     * List all incidents.
     *
     * @return the list of incidents
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.NOTIFICATION_MANAGEMENT + "')")
    //TODO Change it to INCIDENT_MANAGEMENT
    public Page<ApplicantIncidentDto> list(@RequestBody IncidentSearchCriteriaDto criteria, Pageable pageable, Authentication authentication) {
        return applicantIncidentService.findAll(criteria, pageable);
    }

    /**
     * finds an incident by its ID
     *
     * @param incidentId the incident id to find
     * @return the found incident or <code>null</code>
     */
    @GetMapping("/find/{incidentId}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.NOTIFICATION_MANAGEMENT + "')")
    //TODO Change it to INCIDENT_MANAGEMENT
    public ApplicantIncidentDto findIncident(@PathVariable long incidentId) {
        log.debug("Finding incident #{}", incidentId);
        return applicantIncidentService.findById(incidentId);
    }

    /**
     * Handles incident by marking it as resolved or closed and update resolution comment
     *
     * @param incidentId the ID of the incident to update
     * @return the {@link ResponseEntity} with status
     */
    @PostMapping("/handle/{incidentId}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.NOTIFICATION_MANAGEMENT + "')")
    //TODO Change it to INCIDENT_MANAGEMENT
    public ResponseEntity<String> handleIncident(@PathVariable long incidentId,
                                                 @RequestBody ApplicantIncidentVo applicantIncidentVo) throws NotFoundException {
        log.debug("Handle incident #{}", incidentId);
        applicantIncidentService.update(incidentId, applicantIncidentVo);
        return ResponseEntity.ok(StringUtils.EMPTY);
    }

    /**
     * Downloads applicant incident attachment
     *
     * @param attachmentId data request Id
     * @return WsResponse of  the saved incident attachment
     */
    @GetMapping("/attachments/{attachmentId}")
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
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachmentName + "\"")
                    .body(attachment);
        }
        return null;
    }

}

/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.data.request.DataRequestService;
import com.elm.shj.admin.portal.services.data.segment.DataSegmentService;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.DataRequestDto;
import com.elm.shj.admin.portal.services.dto.DataSegmentDto;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

/**
 * Main controller for data request management page
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@RestController
@RequestMapping(Navigation.API_DATA_REQUEST)
@Slf4j
public class DataRequestManagementController {

    @Autowired
    private DataRequestService dataRequestService;

    @Autowired
    private DataSegmentService dataSegmentService;

    /**
     * Downloads a template for a specific segment
     *
     * @param segmentId data segment Id
     * @return the template for the given segment
     */
    @GetMapping("/tpl/{segmentId}")
    @RolesAllowed({AuthorityConstants.ROLE_MANAGEMENT})
    public ResponseEntity<Resource> downloadTemplate(@PathVariable long segmentId) {
        DataSegmentDto dataSegment = dataSegmentService.findOne(segmentId);
        log.info("Downloading template for data segment#{}", segmentId);
        if (dataSegment == null) {
            log.warn("Now data segment found with #{}", segmentId);
            return null;
        }
        Resource tplFile = dataSegmentService.loadTemplateFile(dataSegment);
        if (tplFile.exists()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + tplFile.getFilename() + "\"")
                    .body(tplFile);
        }
        return null;
    }

    /**
     * Creates a new data request
     *
     * @param dataRequest the data request
     * @return the persisted request updated
     */
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DataRequestDto create(@RequestPart("request") @Valid DataRequestDto dataRequest,
                                 @RequestPart("file") MultipartFile file) throws Exception {
        log.info("Creating data request for segment#{}", dataRequest.getDataSegment().getId());
        return dataRequestService.save(dataRequest, file);
    }

}

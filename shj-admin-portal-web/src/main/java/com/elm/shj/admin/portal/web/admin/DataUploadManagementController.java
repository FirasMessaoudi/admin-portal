/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.data.segment.DataSegmentService;
import com.elm.shj.admin.portal.services.data.upload.DataUploadService;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.DataSegmentDto;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * Main controller for data upload management page
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@RestController
@RequestMapping(Navigation.API_DATA_UPLOAD)
@Slf4j
public class DataUploadManagementController {

    @Autowired
    private DataUploadService dataUploadService;

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
        Resource tplFile = new ClassPathResource("/templates/excel/" + segmentId + "/" + dataSegment.getTemplateFileName());
        if (tplFile.exists()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + tplFile.getFilename() + "\"")
                    .body(tplFile);
        }
        return null;
    }

}

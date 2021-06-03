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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Objects;

/**
 * Main controller for data request management page
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping(Navigation.API_DATA_REQUEST)
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DataRequestManagementController {

    private final DataRequestService dataRequestService;
    private final DataSegmentService dataSegmentService;

    private enum EDataRequestFileType {
        O, // Original
        E // Errors
    }

    /**
     * List all data requests.
     *
     * @return the list of data requests
     */
    @GetMapping("/list")
    @RolesAllowed({AuthorityConstants.ROLE_MANAGEMENT})
    public Page<DataRequestDto> list(Pageable pageable) {
        log.info("list all data requests.");
        return dataRequestService.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("creationDate").descending()));
    }

    /**
     * finds a data request by its ID
     *
     * @param dataRequestId the data request id to find
     * @return the found data request or <code>null</code>
     */
    @GetMapping("/find/{dataRequestId}")
    @RolesAllowed(AuthorityConstants.ROLE_MANAGEMENT)
    public DataRequestDto findRole(@PathVariable long dataRequestId) {
        log.debug("Finding data request #{}", dataRequestId);
        return dataRequestService.findOne(dataRequestId);
    }

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
     * Downloads a template for a specific segment
     *
     * @param dataRequestId data request Id
     * @param fileType file type to download
     * @return the file for the given data request and file type
     */
    @GetMapping("/{dataRequestId}/file/{fileType}")
    @RolesAllowed({AuthorityConstants.ROLE_MANAGEMENT})
    public ResponseEntity<Resource> downloadFile(@PathVariable long dataRequestId, @PathVariable EDataRequestFileType fileType) throws Exception {
        log.info("Downloading file for data request#{} and file type #{}", dataRequestId, fileType);
        Resource file = null;
        switch (fileType) {
            case O:
                file = dataRequestService.fetchOriginalFile(dataRequestId);
                break;
            case E:
                file = dataRequestService.fetchErrorsFile(dataRequestId);
                break;
        }
        if (file != null) {
            String fileName = "file.xlsx";
            if (Objects.requireNonNull(file.getDescription()).contains("[")) {
                fileName = file.getDescription().split("\\[")[1].replaceAll("]", "");
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(file);
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
        DataRequestDto savedDataRequest = dataRequestService.save(dataRequest, file);
        savedDataRequest.setItemCount(dataRequestService.readItemsCount(file));
        return savedDataRequest;
    }

    /**
     * Confirms a newly created data request
     *
     * @param dataRequestId the data request id to be confirmed
     */
    @PostMapping(value = "/confirm/{dataRequestId}")
    public void confirm(@PathVariable long dataRequestId) throws Exception {
        log.info("Confirming data request #{}", dataRequestId);
        dataRequestService.confirm(dataRequestId);
    }

}

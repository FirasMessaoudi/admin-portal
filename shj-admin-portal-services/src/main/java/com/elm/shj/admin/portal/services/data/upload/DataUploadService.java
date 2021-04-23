/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.upload;

import com.elm.shj.admin.portal.services.data.request.DataRequestService;
import com.elm.shj.admin.portal.services.data.segment.DataSegmentService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.sftp.SftpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

/**
 * Service handling data uploads
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataUploadService {

    private static final SimpleDateFormat FILE_NAME_POSTFIX_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss");
    private static final SimpleDateFormat FOLDER_NAME_FORMAT = new SimpleDateFormat("yyyy_MM_dd");
    private static final String XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    private final SftpService sftpService;
    private final DataRequestService dataRequestService;
    private final DataSegmentService dataSegmentService;
    private final MessageSource messageSource;

    /**
     * Uploads data file and creates a new data request
     *
     * @param file      the file content to upload
     * @param segmentId the data segment id
     * @return
     * @throws Exception in case of upload/transfer issue
     */
    @Transactional
    public int uploadDataFile(MultipartFile file, long segmentId) throws Exception {
        // generate request reference
        String requestReference = UUID.randomUUID().toString();
        // retrieve data segment
        DataSegmentDto dataSegment = dataSegmentService.findOne(segmentId);
        // generate file and folder names to be uploaded/created in SFTP
        String[] fileNameParts = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String localFileName = fileNameParts[0] +
                "_" +
                FILE_NAME_POSTFIX_FORMAT.format(new Date()) +
                "." +
                fileNameParts[1];
        String sftpPath = FOLDER_NAME_FORMAT.format(new Date()) +
                "/" +
                requestReference +
                "/" +
                localFileName;
        // create and save the data request
        DataRequestDto dataRequest = DataRequestDto.builder()
                .referenceNumber(requestReference)
                .dataSegment(dataSegment)
                .channel(EDataUploadChannel.SYSTEM.toString())
                .originalSourcePath(sftpPath)
                .status(DataRequestStatusLookupDto.builder().id(EDataRequestStatus.NEW.getId()).build())
                .creationDate(new Date())
                .build();
        DataRequestDto createdRequest = dataRequestService.save(dataRequest);
        log.info("data request created successfully with #{}", createdRequest.getId());
        // upload the file in the SFTP
        sftpService.uploadFile(sftpPath, file.getInputStream());
        log.info("file uploaded successfully to: {}", sftpPath);
        return 100;
    }

    public void validateFile(MultipartFile file, DataSegmentDto dataSegment, String lang) {
        if (!XLSX_CONTENT_TYPE.equals(file.getContentType())){
            messageSource.getMessage("", null, Locale.forLanguageTag(lang));
        }
    }
}

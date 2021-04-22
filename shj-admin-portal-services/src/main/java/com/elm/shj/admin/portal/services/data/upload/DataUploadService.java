/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.upload;

import com.elm.shj.admin.portal.services.sftp.SftpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private SftpService sftpService;

    public int uploadDataFile(MultipartFile file, long segmentId) throws Exception {
        UUID requestReference = UUID.randomUUID();

        String [] fileNameParts = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
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
        sftpService.uploadFile(sftpPath, file.getInputStream());
        log.info("service: uploadFile");
        return 100;
    }
}

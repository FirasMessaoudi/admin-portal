/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.upload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service handling data uploads
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@Service
public class DataUploadService {

    public int uploadDataFile(MultipartFile file, long segmentId) {
        log.info("service: uploadFile");
        return 100;
    }
}

/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.request;

import com.elm.shj.admin.portal.orm.entity.JpaDataRequest;
import com.elm.shj.admin.portal.orm.repository.DataRequestRepository;
import com.elm.shj.admin.portal.services.dto.DataRequestDto;
import com.elm.shj.admin.portal.services.dto.DataRequestStatusLookupDto;
import com.elm.shj.admin.portal.services.dto.EDataRequestStatus;
import com.elm.shj.admin.portal.services.dto.EDataUploadChannel;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.sftp.SftpService;
import com.jcraft.jsch.JSchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Service handling data requests
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataRequestService extends GenericService<JpaDataRequest, DataRequestDto, Long> {

    private static final SimpleDateFormat FILE_NAME_POSTFIX_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss");
    private static final SimpleDateFormat FOLDER_NAME_FORMAT = new SimpleDateFormat("yyyy_MM_dd");

    private final SftpService sftpService;

    /**
     * Find all data requests.
     *
     * @param pageable the current page information
     * @return the list of data requests
     */
    public Page<DataRequestDto> findAll(Pageable pageable) {
        return mapPage(getRepository().findAll(pageable));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public DataRequestDto save(DataRequestDto dataRequest, MultipartFile file) {
        // generate request reference
        String requestReference = UUID.randomUUID().toString();
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
        dataRequest.setReferenceNumber(requestReference);
        dataRequest.setChannel(EDataUploadChannel.SYSTEM.toString());
        dataRequest.setOriginalSourcePath(sftpPath);
        dataRequest.setStatus(DataRequestStatusLookupDto.builder().id(EDataRequestStatus.NEW.getId()).build());
        dataRequest.setCreationDate(new Date());
        // persist the request
        DataRequestDto createdRequest = super.save(dataRequest);
        log.info("data request created successfully with #{}", createdRequest.getId());
        // upload the file in the SFTP
        try {
            sftpService.uploadFile(sftpPath, file.getInputStream());
        } catch (JSchException e) {
            log.error("Unable to open attached file", e);
            throw new IllegalArgumentException("Unable to open attached file");
        } catch (IOException ioe) {
            log.error("Unable to upload file to SFTP", ioe);
            throw new IllegalArgumentException("Unable to upload file to SFTP");
        }
        log.info("file uploaded successfully to: {}", sftpPath);
        // discovering items
        createdRequest.setItemCount(200);
        // return the persisted object
        return createdRequest;
    }

    /**
     * Confirms a newly created data request
     *
     * @param dataRequestId the data request id to be confirmed
     */
    public void confirm(long dataRequestId) {
        ((DataRequestRepository)getRepository()).updateStatus(dataRequestId, EDataRequestStatus.CONFIRMED.getId());
    }
}

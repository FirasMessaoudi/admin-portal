/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.request;

import com.elm.shj.admin.portal.orm.entity.JpaDataRequest;
import com.elm.shj.admin.portal.orm.repository.DataRequestRepository;
import com.elm.shj.admin.portal.services.dto.DataRequestDto;
import com.elm.shj.admin.portal.services.dto.DataRequestStatusLookupDto;
import com.elm.shj.admin.portal.services.dto.EDataRequestChannel;
import com.elm.shj.admin.portal.services.dto.EDataRequestStatus;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.sftp.SftpService;
import com.jcraft.jsch.JSchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
    private static final SimpleDateFormat REF_NUMBER_FORMAT = new SimpleDateFormat("SSS");
    private static final String REQUEST_REF_NUMBER_SYSTEM_PREFIX = "DS";
    private static final String REQUEST_REF_NUMBER_WS_PREFIX = "DI";
    private static final int REQUEST_REF_NUMBER_LENGTH = 12;

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
     * fetches the original file of the data request
     *
     * @param dataRequestId the data request id
     * @return the original file of the data request
     */
    public Resource fetchOriginalFile(long dataRequestId) throws Exception {
        DataRequestDto dataRequest = findOne(dataRequestId);
        if (dataRequest == null) {
            return null;
        }
        return sftpService.downloadFile(dataRequest.getOriginalSourcePath());
    }

    /**
     * fetches the errors file of the data request
     *
     * @param dataRequestId the data request id
     * @return the errors file of the data request
     */
    public Resource fetchErrorsFile(long dataRequestId) throws Exception {
        DataRequestDto dataRequest = findOne(dataRequestId);
        if (dataRequest == null || dataRequest.getErrorFilePath() == null
                || dataRequest.getStatus().getId() != EDataRequestStatus.PROCESSED_WITH_ERRORS.getId()) {
            return null;
        }
        return sftpService.downloadFile(dataRequest.getOriginalSourcePath());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public DataRequestDto save(DataRequestDto dataRequest, MultipartFile file) {
        dataRequest.setChannel(EDataRequestChannel.SYSTEM.toString());
        // generate request reference
        String requestReference = generateReferenceNumber(EDataRequestChannel.nullSafeValueOf(dataRequest.getChannel()));
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
    @Transactional
    public void confirm(long dataRequestId) {
        ((DataRequestRepository) getRepository()).updateStatus(dataRequestId, EDataRequestStatus.CONFIRMED.getId());
    }

    /**
     * Generates a unique identifier for the data request
     *
     * @return a unique identifier for the data request
     */
    public String generateReferenceNumber(EDataRequestChannel channel) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(t -> t >= '0' && t <= '9', t -> t >= 'A' && t <= 'Z')
                .build();
        return (channel == EDataRequestChannel.WEB_SERVICE ? REQUEST_REF_NUMBER_WS_PREFIX : REQUEST_REF_NUMBER_SYSTEM_PREFIX) + "-" + generator.generate(REQUEST_REF_NUMBER_LENGTH - 6) + REF_NUMBER_FORMAT.format(new Date());
    }

}

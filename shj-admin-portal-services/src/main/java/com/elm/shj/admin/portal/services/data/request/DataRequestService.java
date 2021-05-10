/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.request;

import com.elm.shj.admin.portal.orm.entity.JpaDataRequest;
import com.elm.shj.admin.portal.orm.repository.DataRequestRepository;
import com.elm.shj.admin.portal.services.data.mapper.ApplicantRowMapper;
import com.elm.shj.admin.portal.services.data.processor.DataProcessorService;
import com.elm.shj.admin.portal.services.data.processor.DataProcessorResult;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.sftp.SftpService;
import com.jcraft.jsch.JSchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DataRequestService extends GenericService<JpaDataRequest, DataRequestDto, Long> {

    private static final SimpleDateFormat FILE_NAME_POSTFIX_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss");
    private static final SimpleDateFormat FOLDER_NAME_FORMAT = new SimpleDateFormat("yyyy_MM_dd");
    private static final SimpleDateFormat REF_NUMBER_FORMAT = new SimpleDateFormat("SSS");
    private static final String REQUEST_REF_NUMBER_SYSTEM_PREFIX = "DS";
    private static final String REQUEST_REF_NUMBER_WS_PREFIX = "DI";
    private static final int REQUEST_REF_NUMBER_LENGTH = 12;

    private final SftpService sftpService;
    private final DataProcessorService dataRequestProcessor;

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
        String sftpPath = generateSftpFilePath(file.getOriginalFilename(), requestReference, false);
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
    @Async
    @Transactional
    public void confirm(long dataRequestId) throws Exception {
        ((DataRequestRepository) getRepository()).updateStatus(dataRequestId, EDataRequestStatus.CONFIRMED.getId());
        processRequest(dataRequestId);
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

    /**
     * Processes the data request identified by its ID
     *
     * @param dataRequestId the data request id to be processed
     */
    private void processRequest(long dataRequestId) throws Exception {
        // retrieve the data request
        DataRequestDto dataRequest = findOne(dataRequestId);
        // initial validation
        if (dataRequest != null && dataRequest.getOriginalSourcePath() != null
                && dataRequest.getStatus().getId() == EDataRequestStatus.CONFIRMED.getId()) {
            // retrieve the original file to start processing
            Resource originalFile = sftpService.downloadFile(dataRequest.getOriginalSourcePath());
            // process the file
            DataProcessorResult<ApplicantDto> parserResult = dataRequestProcessor.processRequestFile(originalFile, new ApplicantRowMapper());
            // in case of error, generate the error file and save it in the SFTP
            if (parserResult.isWithErrors()) {
                // generate error file
                Resource errorFile = dataRequestProcessor.generateErrorFile(originalFile, parserResult.getDataValidationResults());
                // generate file name
                String fileName = "processing-error";
                if (Objects.requireNonNull(originalFile.getDescription(), StringUtils.EMPTY).contains("[")) {
                    fileName = originalFile.getDescription().split("\\[")[1].replaceAll("]", "");
                }
                // save the file in the sftp folder
                sftpService.uploadFile(generateSftpFilePath(fileName, dataRequest.getReferenceNumber(), true), errorFile.getInputStream());
                // update the data request status
                ((DataRequestRepository) getRepository()).updateStatus(dataRequestId, EDataRequestStatus.PROCESSED_WITH_ERRORS.getId());
            } else {
                // update the data request status
                ((DataRequestRepository) getRepository()).updateStatus(dataRequestId, EDataRequestStatus.PROCESSED_SUCCESSFULLY.getId());
            }
        }
    }

    /**
     * Generates the file path to be used to save the file in sftp server
     *
     * @param fileName         the file name to save
     * @param requestReference the data request reference
     * @param isErrorFile      if the file to save is an error file
     * @return the sftp path for the file
     */
    private String generateSftpFilePath(String fileName, String requestReference, boolean isErrorFile) {
        String[] fileNameParts = Objects.requireNonNull(fileName).split("\\.");
        String localFileName = fileNameParts[0] +
                "_" +
                (isErrorFile ? "with-errors" : FILE_NAME_POSTFIX_FORMAT.format(new Date())) +
                "." +
                fileNameParts[1];
        return FOLDER_NAME_FORMAT.format(new Date()) +
                "/" +
                requestReference +
                "/" +
                (isErrorFile ? "error/" : "") +
                localFileName;
    }


}

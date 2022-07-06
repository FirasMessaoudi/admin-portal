/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.request;

import com.elm.shj.admin.portal.orm.entity.JpaDataRequest;
import com.elm.shj.admin.portal.orm.repository.DataRequestRepository;
import com.elm.shj.admin.portal.services.data.processor.DataProcessorResult;
import com.elm.shj.admin.portal.services.data.processor.DataProcessorService;
import com.elm.shj.admin.portal.services.data.validators.DataValidationResult;
import com.elm.shj.admin.portal.services.data.writer.ItemWriter;
import com.elm.shj.admin.portal.services.dto.DataRequestDto;
import com.elm.shj.admin.portal.services.dto.DataRequestStatusLookupDto;
import com.elm.shj.admin.portal.services.dto.EDataRequestChannel;
import com.elm.shj.admin.portal.services.dto.EDataRequestStatus;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.sftp.SftpService;
import com.jcraft.jsch.JSchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

 private static final SimpleDateFormat REF_NUMBER_FORMAT = new SimpleDateFormat("SSS");
    private static final String REQUEST_REF_NUMBER_SYSTEM_PREFIX = "DS";
    private static final String REQUEST_REF_NUMBER_WS_PREFIX = "DI";
    private static final int REQUEST_REF_NUMBER_LENGTH = 12;
    private static final String DATA_UPLOAD_CONFIG_PROPERTIES = "dataUploadConfigProperties";


    private final SftpService sftpService;
    private final DataProcessorService dataRequestProcessor;
    private final ItemWriter itemWriter;
    private final DataRequestRepository dataRequestRepository;

    /**
     * Find all data requests for command portal
     *
     * @param pageable the current page information
     * @return the list of data requests
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Page<DataRequestDto> findAll(Pageable pageable) {
        return mapPage(dataRequestRepository.finDataRequests(Arrays.asList(1L, 2L, 3L,4L,5L,6L,7L,8L,9L), pageable));
    }

    /**
     * Find all data requests for organizer portal
     *
     * @param pageable the current page information
     * @return the list of data requests
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Page<DataRequestDto> findAllOrganizerDataRequest(long companyRefCode, String companyTypeCode, Pageable pageable) {

        return mapPage(dataRequestRepository.finOrganizerDataRequests(Arrays.asList(11L, 12L, 13L, 14L), String.valueOf(companyRefCode) + "_" + companyTypeCode, pageable));
    }

    /**
     * finds a data request by its ID
     *
     * @param dataRequestId the data request id to find
     * @return the found data request or <code>null</code>
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public DataRequestDto findById(long dataRequestId) {
        return findOne(dataRequestId);
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
        return sftpService.downloadFile(dataRequest.getOriginalSourcePath(),DATA_UPLOAD_CONFIG_PROPERTIES);
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
        return sftpService.downloadFile(dataRequest.getErrorFilePath(),DATA_UPLOAD_CONFIG_PROPERTIES);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public DataRequestDto save(DataRequestDto dataRequest, MultipartFile file) {
        log.info("start save dataRequest: {}", dataRequest);
        dataRequest.setChannel(EDataRequestChannel.SYSTEM.toString());
        // generate request reference
        String requestReference = generateReferenceNumber(EDataRequestChannel.nullSafeValueOf(dataRequest.getChannel()));
        // generate file and folder names to be uploaded/created in SFTP
        log.info("requestReference for generate sftp file path: {}", requestReference);
        String sftpPath = sftpService.generateSftpFilePath(file.getOriginalFilename(), requestReference, false);
        // create and save the data request
        dataRequest.setReferenceNumber(requestReference);
        dataRequest.setOriginalSourcePath(sftpPath);
        dataRequest.setStatus(DataRequestStatusLookupDto.builder().id(EDataRequestStatus.NEW.getId()).build());
        // persist the request
        DataRequestDto createdRequest = super.save(dataRequest);
        log.info("data request created successfully with #{}", createdRequest.getId());
        // upload the file in the SFTP
        try {
            sftpService.uploadFile(sftpPath, file.getInputStream(),DATA_UPLOAD_CONFIG_PROPERTIES);
        } catch (JSchException e) {
            log.error("Unable to open attached file", e);
            throw new IllegalArgumentException("Unable to open attached file");
        } catch (IOException ioe) {
            log.error("Unable to upload file to SFTP", ioe);
            throw new IllegalArgumentException("Unable to upload file to SFTP");
        }
        log.info("file uploaded successfully to: {}", sftpPath);
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
    public void confirm(long dataRequestId, String... companyRefCode) throws Exception {
        log.info("Confirming service data request #{}", companyRefCode);
        updateRequestStatus(dataRequestId, EDataRequestStatus.UNDER_PROCESSING);
        processRequest(dataRequestId, companyRefCode);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void updateRequestStatus(long dataRequestId, EDataRequestStatus requestStatus) {
        ((DataRequestRepository) getRepository()).updateStatus(dataRequestId, requestStatus.getId());
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
     * Reads the items counts in file coming with the request
     *
     * @param file the input stream coming with the request
     * @return the items count in the file
     * @throws IOException if any exception happens during reading the stream coming with the request
     */
    public long readItemsCount(MultipartFile file) throws IOException {
        /// load workbook
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        // read first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);
        // get blank rows count
        long blankRows = StreamSupport.stream(Spliterators.spliteratorUnknownSize(sheet.rowIterator(), Spliterator.ORDERED), false).filter(dataRequestProcessor::isBlankRow).count();
        // return non blank rows count
        long nonBlankRows = sheet.getLastRowNum() - sheet.getFirstRowNum() - blankRows;
        // close workbook
        workbook.close();
        // return result
        return nonBlankRows;
    }

    /**
     * Processes the data request identified by its ID
     *
     * @param dataRequestId the data request id to be processed
     */
    @Transactional
    public <T> void processRequest(long dataRequestId, String... companyRefCode) throws Exception {
        log.info("Confirming process request #{}", companyRefCode);
        // retrieve the data request
        DataRequestDto dataRequest = findOne(dataRequestId);
        log.info("found data request with dataRequestId: {}", dataRequestId);
        // initial validation
        if (dataRequest != null && dataRequest.getOriginalSourcePath() != null) {
            // retrieve the original file to start processing
            log.info("Start downloading original file with path: {}", dataRequest.getOriginalSourcePath());
            Resource originalFile = sftpService.downloadFile(dataRequest.getOriginalSourcePath(),DATA_UPLOAD_CONFIG_PROPERTIES);
            log.info("end downloading original file with path: {}", dataRequest.getOriginalSourcePath());
            DataProcessorResult<T> parserResult;
            List<DataValidationResult> writerValidationResult;
            try {
                // process the file
                parserResult = dataRequestProcessor.processRequestFile(originalFile, dataRequest.getDataSegment());
                // save all parsed items
                writerValidationResult = itemWriter.write(parserResult.getParsedItems(), dataRequest.getDataSegment(), dataRequestId, companyRefCode);
            } catch (Exception e) {
                log.error("Failed to process the data request id {}.", dataRequestId, e);
                //update the status of the request
                updateRequestStatus(dataRequestId, EDataRequestStatus.FAILED);
                return;
            }

            // in case of error, generate the error file and save it in the SFTP
           if(!writerValidationResult.isEmpty()){
                parserResult.getDataValidationResults().addAll(writerValidationResult);
               parserResult.setWithErrors(true);
           }
            if (parserResult.isWithErrors()) {
                // generate error file
                Resource errorFile = dataRequestProcessor.generateErrorFile(originalFile, parserResult.getDataValidationResults());
                // generate file name
                String fileName = "processing-error";
                if (Objects.requireNonNull(originalFile.getDescription(), StringUtils.EMPTY).contains("[")) {
                    fileName = originalFile.getDescription().split("\\[")[1].replaceAll("]", "");
                }
                // save the file in the sftp folder
                log.info("start generate sftp file path with File name: {}", fileName);
                String errorFilePath = sftpService.generateSftpFilePath(fileName, dataRequest.getReferenceNumber(), true);
                sftpService.uploadFile(errorFilePath, errorFile.getInputStream(),DATA_UPLOAD_CONFIG_PROPERTIES);
                log.info("upload error file successfully with fileName: {}", fileName);
                // update the data request status
                Set<Integer> rowsWithErrors = parserResult.getDataValidationResults().stream().filter(dvr -> !dvr.isValid()).map(dvr -> dvr.getCell().getRow().getRowNum()).collect(Collectors.toSet());
                ((DataRequestRepository) getRepository()).updateProcessingStatus(dataRequestId, EDataRequestStatus.PROCESSED_WITH_ERRORS.getId(), errorFilePath, rowsWithErrors.size());
                // return writerValidationResult
            } else {
                // update the data request status
                log.info("End procession without Errors: {}", dataRequestId);
                ((DataRequestRepository) getRepository()).updateStatus(dataRequestId, EDataRequestStatus.PROCESSED_SUCCESSFULLY.getId());
                //return writerValidationResult
            }
        }
    }



}

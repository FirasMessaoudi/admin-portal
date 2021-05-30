/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.print.request;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequest;
import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service handling print request
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PrintRequestService extends GenericService<JpaPrintRequest, PrintRequestDto, Long> {

    private static final SimpleDateFormat REF_NUMBER_FORMAT = new SimpleDateFormat("SSS");
    private static final int REQUEST_REF_NUMBER_LENGTH = 12;

    private final ApplicantService applicantService;
    private final PrintRequestApplicantService printRequestApplicantService;

    /**
     * Find all print requests.
     *
     * @param pageable the current page information
     * @return the list of print requests
     */
    public Page<PrintRequestDto> findAll(Pageable pageable) {
        return mapPage(getRepository().findAll(pageable));
    }

    @Transactional
    public PrintRequestDto save(List<Long> applicantsIds) {
        // create and save the print request
        PrintRequestDto printRequest = PrintRequestDto.builder()
                .referenceNumber(generateReferenceNumber())
                .statusCode("NEW")
                .creationDate(new Date())
                .build();
        // persist the request
        printRequest = super.save(printRequest);
        log.info("printRequest created successfully with #{}", printRequest.getId());
        List<PrintRequestApplicantDto> printRequestApplicants = new ArrayList<>();
        PrintRequestDto finalPrintRequest = printRequest;
        applicantsIds.forEach(id -> {
            ApplicantDto applicant = applicantService.findOne(id);
            printRequestApplicants.add(
                    PrintRequestApplicantDto.builder()
                            .applicant(applicant)
                            .printRequest(finalPrintRequest)
                            .creationDate(new Date())
                            .build());
        });
        printRequestApplicantService.saveAll(printRequestApplicants);
        // return the persisted object
        return printRequest;
    }

    /**
     * Generates a unique identifier for the print request
     *
     * @return a unique identifier for the print request
     */
    public String generateReferenceNumber() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(t -> t >= '0' && t <= '9', t -> t >= 'A' && t <= 'Z')
                .build();
        return generator.generate(REQUEST_REF_NUMBER_LENGTH - 6) + REF_NUMBER_FORMAT.format(new Date());
    }

    public PrintRequestDto processBatching(long requestId, List<String> batchTypes) {
        // Retrieve the print request
        PrintRequestDto printRequest = findOne(requestId);

        //TODO TAKE INTO ACCOUNT SEARCH CRITERIA
        if (batchTypes.contains(EPrintBatchType.NATIONALITY.name())) {
            printRequest.getPrintRequestBatches().clear();

            Map<List<Object>, List<PrintRequestApplicantDto>> groupedRequestApplicants = printRequest.getPrintRequestApplicants()
                    .stream().collect(Collectors.groupingBy(requestApplicant -> Arrays.asList(requestApplicant.getApplicant().getNationalityCode())));

            groupedRequestApplicants.forEach((key, value) -> {
                PrintRequestBatchDto printRequestBatch = PrintRequestBatchDto.builder()
                        .creationDate(new Date())
                        .batchTypes("NATIONALITY")
                        .printRequestBatchApplicants(value)
                        .build();
                printRequest.getPrintRequestBatches().add(printRequestBatch);
            });
            return printRequest;
        } else {
            PrintRequestBatchDto printRequestBatch = PrintRequestBatchDto.builder()
                    .creationDate(new Date())
                    .batchTypes("NATIONALITY")
                    .printRequestBatchApplicants(printRequest.getPrintRequestApplicants())
                    .build();
            printRequest.getPrintRequestBatches().add(printRequestBatch);
            return printRequest;
        }
    }
}


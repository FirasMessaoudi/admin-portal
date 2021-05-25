/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.print.request;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequest;
import com.elm.shj.admin.portal.orm.repository.PrintRequestRepository;
import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.EDataRequestChannel;
import com.elm.shj.admin.portal.services.dto.PrintRequestApplicantDto;
import com.elm.shj.admin.portal.services.dto.PrintRequestDto;
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
import java.util.Date;
import java.util.List;

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
    private final PrintRequestRepository printRequestRepository;
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

    public Page<PrintRequestDto> findNew(Pageable pageable) {
        return mapPage(printRequestRepository.findByStatusCode("NEW", pageable));
    }

    @Transactional
    public PrintRequestDto save(List<Long> applicantsIds) {
        // create and save the print request
        PrintRequestDto printRequest = new PrintRequestDto();
        // generate request reference
        String printRequestReference = generateReferenceNumber();
        printRequest.setReferenceNumber(printRequestReference);
        printRequest.setStatusCode("NEW");
        printRequest.setCreationDate(new Date());
        // persist the request
        System.out.println(printRequest);
        printRequest = super.save(printRequest);
        log.info("printRequest created successfully with #{}", printRequest.getId());
        for (long id : applicantsIds) {
            PrintRequestApplicantDto printRequestApplicant = new PrintRequestApplicantDto();
            ApplicantDto applicantDto = applicantService.findOne(id);
            printRequestApplicant.setApplicant(applicantDto);
            printRequestApplicant.setCreationDate(new Date());
            printRequestApplicant.setPrintRequest(printRequest);
            printRequestApplicantService.save(printRequestApplicant);
        }
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
}

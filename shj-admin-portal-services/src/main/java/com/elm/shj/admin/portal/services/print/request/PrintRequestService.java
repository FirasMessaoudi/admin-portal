/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.print.request;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequest;
import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.PrintRequestApplicantDto;
import com.elm.shj.admin.portal.services.dto.PrintRequestDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    private final ApplicantService applicantService;

    /**
     * Find all print requests.
     *
     * @param pageable the current page information
     * @return the list of print requests
     */
    public Page<PrintRequestDto> findAll(Pageable pageable) {
        return mapPage(getRepository().findAll(pageable));
    }

    public PrintRequestDto createPrintRequest(List<Long> applicantsIds) {
        PrintRequestDto printRequest = new PrintRequestDto();
        printRequest.setStatusCode("NEW");
        applicantsIds.forEach(id -> {
            PrintRequestApplicantDto printRequestApplicant = new PrintRequestApplicantDto();
            printRequestApplicant.setApplicant(applicantService.findOne(id));
            printRequestApplicant.setCreationDate(new Date());
            printRequest.getPrintRequestApplicants().add(printRequestApplicant);
        });
        save(printRequest);
        return printRequest;
    }
}

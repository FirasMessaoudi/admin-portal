/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.print.request;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestBatchApplicant;
import com.elm.shj.admin.portal.services.dto.PrintRequestBatchApplicantDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling print request batch applicant
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PrintRequestBatchApplicantService extends GenericService<JpaPrintRequestBatchApplicant, PrintRequestBatchApplicantDto, Long> {
}

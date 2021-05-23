/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the print request domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@NoArgsConstructor
@Data
public class PrintRequestDto implements Serializable {

    private static final long serialVersionUID = -5860349238491983581L;

    private long id;
    private String statusCode;
    private List<PrintRequestApplicantDto> printRequestApplicants;
    private List<PrintRequestBatchDto> printRequestBatches;
    private Date creationDate;
}

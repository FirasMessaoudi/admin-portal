/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the print request batch domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@NoArgsConstructor
@Data
public class PrintRequestBatchDto implements Serializable {

    private static final long serialVersionUID = -2689417211511498736L;
    private PrintRequestDto printRequest;
    private int sequenceNumber;
    private String batchTypes;
    private Date creationDate;
}

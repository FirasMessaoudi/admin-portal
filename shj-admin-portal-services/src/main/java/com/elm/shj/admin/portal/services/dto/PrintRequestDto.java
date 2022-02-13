/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Dto class for the print request domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrintRequestDto implements Serializable {

    private static final long serialVersionUID = -5860349238491983581L;

    private long id;
    private String referenceNumber;
    private String statusCode;
    private String description;
    private Set<PrintRequestCardDto> printRequestCards = new HashSet<>();
    private Set<PrintRequestBatchDto> printRequestBatches = new HashSet<>();
    private String target;
    private int cardsCount;
    private int batchesCount;
    private Date creationDate;
    private Date updateDate;
    private Date confirmationDate;
    private Integer seasonYear;
    private String ritualTypeCode;
}

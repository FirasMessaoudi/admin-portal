/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the print request lite domain.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrintRequestLiteDto implements Serializable {

    private static final long serialVersionUID = 6135830787465871921L;

    private long id;
    private String referenceNumber;
    private String description;
    private String statusCode;
    private Date creationDate;
    private int cardsCount;
    private int batchesCount;
    private String target;
    private Date updateDate;
    private Date confirmationDate;

}

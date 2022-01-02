/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the staff print request lite domain.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StaffPrintRequestLiteDto implements Serializable {


    private static final long serialVersionUID = -2424483294508748152L;
    private long id;
    private String referenceNumber;
    private String description;
    private String statusCode;
    private Date creationDate;
    private int cardsCount;
    private int season;
    private String ritualTypeCode;
    private int batchesCount;
    private Date updateDate;
    private Date confirmationDate;

}

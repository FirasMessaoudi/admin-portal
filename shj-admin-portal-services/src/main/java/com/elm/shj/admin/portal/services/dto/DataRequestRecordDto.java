/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.validators.DataSegmentStructure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the DataRequestRecord domain.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@DataSegmentStructure
public class DataRequestRecordDto implements Serializable {

    private static final long serialVersionUID = 123456193540474532L;

    private long id;
    private Long createDataRequestId;
    private Long lastUpdateDataRequestId;
    private Long createDataRequestRowNum;
    private Long lastUpdateDataRequestRowNum;
    private long itemId;
    private Date creationDate;
    private Date updateDate;
}

/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the DataSegment domain.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataSegmentDto implements Serializable {

    private static final long serialVersionUID = 4771234193540474532L;

    private long id;
    private String labelAr;
    private String labelEn;
    private String templateFileName;
    private Date creationDate;
    private Date updateDate;
}

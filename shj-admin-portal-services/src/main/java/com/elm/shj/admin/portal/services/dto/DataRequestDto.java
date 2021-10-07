/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.validators.DataSegmentStructure;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the DecisionRule domain.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DataSegmentStructure
public class DataRequestDto implements Serializable {

    private static final long serialVersionUID = 123456193540474532L;

    private long id;
    private String referenceNumber;
    private String originalSourcePath;
    private String errorFilePath;
    private String channel;
    private DataSegmentDto dataSegment;
    private DataRequestStatusLookupDto status;
    private Date creationDate;
    private Date updateDate;
    private long itemCount;
    private long errorCount;
}

/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.validation.SafeFile;
import com.elm.shj.admin.portal.services.data.validators.ContentType;
import com.elm.shj.admin.portal.services.data.validators.DataSegmentFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
@Data
@DataSegmentFormat
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

    // these fields are not persisted but used as part of
    // the command in the data upload controller
    private int itemCount;
}

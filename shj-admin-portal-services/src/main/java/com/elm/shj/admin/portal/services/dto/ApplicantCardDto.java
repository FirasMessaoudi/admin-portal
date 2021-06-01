/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant card domain.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@NoArgsConstructor
@Data
public class ApplicantCardDto implements Serializable {

    private static final long serialVersionUID = -5830783313676682718L;

    private long id;
    private ApplicantRitualDto applicantRitual;
    private String referenceNumber;
    private Long batchId;
    private String statusCode;
    private Date creationDate;
}

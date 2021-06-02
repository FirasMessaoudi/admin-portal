/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestCard;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    @JsonBackReference
    private List<JpaPrintRequestCard> printRequestCards;
    private String statusCode;
    private Date creationDate;
}

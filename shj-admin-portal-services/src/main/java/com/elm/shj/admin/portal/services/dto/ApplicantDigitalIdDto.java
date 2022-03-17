/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant digital id domain.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicantDigitalIdDto implements Serializable {

    private static final long serialVersionUID = -1067949632316606990L;

    private long id;
    private String uin;
    private String statusCode;
    private long applicantId;
    private Date creationDate;
}

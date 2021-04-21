/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant health special needs domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@NoArgsConstructor
@Data
public class ApplicantHealthSpecialNeedsDto implements Serializable {

    private static final long serialVersionUID = -2130894375076782343L;

    private long id;
    private ApplicantHealthDto applicantHealth;
    private String specialNeedTypeCode;
    private Date creationDate;
}

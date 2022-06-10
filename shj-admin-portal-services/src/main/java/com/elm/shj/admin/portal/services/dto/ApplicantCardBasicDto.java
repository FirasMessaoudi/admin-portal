/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Dto class for the applicant card domain.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantCardBasicDto implements Serializable, HibernateAwareMapper {

    private static final long serialVersionUID = -5830783313676682718L;

    private long id;
    private ApplicantRitualBasicDto applicantRitual;
    private String statusCode;
}
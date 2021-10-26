/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant digital ID status domain.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class ApplicantDigitalIdStatusLookupDto implements Serializable {

    private static final long serialVersionUID = 3173097897775498101L;
    private long id;
    private String code;
    private String lang;
    private String label;
    private Date creationDate;
}
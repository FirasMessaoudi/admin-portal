/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant group type domain.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@NoArgsConstructor
@Data
public class ApplicantGroupTypeLookupDto implements Serializable {

    private static final long serialVersionUID = 7788506226034704074L;

    private long id;
    private String code;
    private String lang;
    private String label;
    private Date creationDate;
}

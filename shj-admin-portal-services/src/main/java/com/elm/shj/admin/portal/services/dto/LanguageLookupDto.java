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
 * Dto class for the language domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
public class LanguageLookupDto implements Serializable {

    private static final long serialVersionUID = 2108238859214254284L;

    private long id;
    private String code;
    private String lang;
    private String label;
    private Date creationDate;
}

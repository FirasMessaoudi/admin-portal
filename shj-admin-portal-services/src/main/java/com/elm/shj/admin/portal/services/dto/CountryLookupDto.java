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
 * Dto class for the country domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
public class CountryLookupDto implements Serializable {

    private static final long serialVersionUID = -3015368529250122708L;

    private long id;
    private String code;
    private String countryNamePrefix;
    private String lang;
    private String label;
    private String countryPhonePrefix;
    private Date creationDate;
}

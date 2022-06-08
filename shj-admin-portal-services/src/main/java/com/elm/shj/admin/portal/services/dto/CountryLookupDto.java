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
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class CountryLookupDto implements Serializable {

    private long id;
    private String code;
    private String lang;
    private String label;
    private Date creationDate;
}

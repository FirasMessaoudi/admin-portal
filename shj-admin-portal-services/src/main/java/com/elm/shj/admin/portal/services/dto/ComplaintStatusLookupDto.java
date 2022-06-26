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
 * Dto class for complaint status domain.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Getter
@Setter
@NoArgsConstructor
public class ComplaintStatusLookupDto implements Serializable {

    private static final long serialVersionUID = 349062794062613813L;

    private long id;
    private String code;
    private String lang;
    private String label;
    private Date creationDate;
}

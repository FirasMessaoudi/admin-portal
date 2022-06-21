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
 * Dto class for complaint type domain.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Getter
@Setter
@NoArgsConstructor
public class ComplaintTypeLookupDto implements Serializable {

    private static final long serialVersionUID = -5519794686804429980L;

    private long id;
    private int code;
    private String lang;
    private String label;
    private Date creationDate;
}

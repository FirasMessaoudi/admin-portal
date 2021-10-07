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
 * Dto class for the housing site domain.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class HousingSiteLookupDto implements Serializable {

    private static final long serialVersionUID = 8978734576054249054L;

    private long id;
    private String code;
    private String lang;
    private String label;
    private Date creationDate;
}

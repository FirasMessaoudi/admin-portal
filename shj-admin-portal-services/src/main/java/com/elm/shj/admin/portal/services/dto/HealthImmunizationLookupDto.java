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
 * Dto class for the health immunization domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@NoArgsConstructor
@Getter
@Setter
public class HealthImmunizationLookupDto implements Serializable {

    private static final long serialVersionUID = 6039326590227110860L;

    private long id;
    private String code;
    private String lang;
    private String label;
    private Date creationDate;
}

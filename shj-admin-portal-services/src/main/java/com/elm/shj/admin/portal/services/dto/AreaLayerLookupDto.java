/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the area layer domain.
 *
 * @author jaafer jarray
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class AreaLayerLookupDto implements Serializable {

    private static final long serialVersionUID = -5358244881018936417L;

    private long id;
    private String code;
    private String lang;
    private String label;
    private String layer;
    private Date creationDate;
    private Integer parentLayerId;
}

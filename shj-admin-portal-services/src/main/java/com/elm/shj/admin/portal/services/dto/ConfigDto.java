/*
 *  Copyright (c) 2017 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the Config domain.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ConfigDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String confKey;
    private String confValue;
    private Date creationDate;
    private Date updateDate;
}

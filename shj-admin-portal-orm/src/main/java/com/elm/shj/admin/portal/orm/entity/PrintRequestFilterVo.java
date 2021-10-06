/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Print request filter value object.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Getter
@Setter
public class PrintRequestFilterVo implements Serializable {

    private static final long serialVersionUID = -8483791293043657948L;

    private String statusCode;
    private String description;
}

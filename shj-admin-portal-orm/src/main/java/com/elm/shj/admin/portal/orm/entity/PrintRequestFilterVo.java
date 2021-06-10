/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Print request filter value object.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Data
public class PrintRequestFilterVo implements Serializable {

    private String statusCode;
}

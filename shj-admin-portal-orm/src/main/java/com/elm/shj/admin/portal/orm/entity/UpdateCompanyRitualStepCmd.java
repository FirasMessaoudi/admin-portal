/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Value object class for returned company ritual step.
 *
 * @author salzoubi
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompanyRitualStepCmd implements Serializable {

    private static final long serialVersionUID = 8160457570099912194L;

    private long groupId;
    private String stepCode;
    private long hijriDate;
    private String time;

}

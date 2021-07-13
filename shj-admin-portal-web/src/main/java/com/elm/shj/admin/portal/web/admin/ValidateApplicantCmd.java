/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import lombok.Data;

import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * Check Applicant MVC Command
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Data
public class ValidateApplicantCmd implements Serializable {

    private static final long serialVersionUID = 6641483817347109390L;

    private String uin;

    @Past
    private Date dateOfBirthGregorian;

    private int dateOfBirthHijri;

}

/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * Check Applicant MVC Command
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
public class ValidateApplicantCmd implements Serializable {

    private static final long serialVersionUID = 6641483817347109390L;
    private String type;
    private String nationalityCode;
    private String identifier;

    @Past
    private Date dateOfBirthGregorian;

    private int dateOfBirthHijri;

}

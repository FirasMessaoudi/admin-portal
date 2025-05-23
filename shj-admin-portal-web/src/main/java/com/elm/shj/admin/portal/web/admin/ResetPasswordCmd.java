/*
 * Copyright (c) 2018 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.dcc.foundation.commons.validation.NinOrIqama;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * Reset Password MVC Command
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Getter
@Setter
public class ResetPasswordCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @NinOrIqama
    private long idNumber;

    @Past
    private Date dateOfBirthGregorian;

    private int dateOfBirthHijri;

}

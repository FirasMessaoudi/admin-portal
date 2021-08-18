/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.validators.NullOrNotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Update Applicant MVC Command
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Data
public class UpdateApplicantCmd implements Serializable {

    private static final long serialVersionUID = -7437270089118787394L;

    private String uin;

    @NullOrNotBlank(min = 5, max = 50)
    @Email(message = "validation.data.constraints.msg.20003")
    private String email;

    @NotNull
    @Size(min = 5, max = 20)
    private String mobileNumber;
    @Length(max = 20)
    private String countryCode;
    private int dateOfBirthHijri;

}

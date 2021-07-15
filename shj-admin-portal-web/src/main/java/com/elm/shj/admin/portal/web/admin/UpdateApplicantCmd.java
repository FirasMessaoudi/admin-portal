/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.data.validators.NullOrNotBlank;
import lombok.Data;

import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * Update Applicant MVC Command
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Data
public class UpdateApplicantCmd implements Serializable {

    private static final long serialVersionUID = -7437270089118787394L;

    @NullOrNotBlank(min = 5, max = 50)
    @Email(message = "validation.data.constraints.msg.20003")
    private String email;

    @NullOrNotBlank(min = 5, max = 16)
    private String localMobileNumber;
}

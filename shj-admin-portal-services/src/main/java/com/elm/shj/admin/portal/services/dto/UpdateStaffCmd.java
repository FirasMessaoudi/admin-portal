/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.validators.NullOrNotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Update Staff MVC Command
 *
 * @author Rameez Imtiaz
 * @since 1.1.0
 */
@Getter
@Setter
public class UpdateStaffCmd implements Serializable {

    private static final long serialVersionUID = -7437270089118787394L;

    private String suin;

    private String email;

    private String mobileNumber;

    @Length(max = 20)
    private String countryCode;

    private int dateOfBirthHijri;

}

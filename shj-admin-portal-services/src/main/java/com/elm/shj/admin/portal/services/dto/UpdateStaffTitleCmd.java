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
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Update Staff Title MVC Command
 *
 * @author Rameez Imtiaz
 * @since 1.1.0
 */
@Getter
@Setter
public class UpdateStaffTitleCmd implements Serializable {

    private static final long serialVersionUID = -7437270089118787394L;

    private long id;
    private String jobTitle;
    private String customJobTitle;
}

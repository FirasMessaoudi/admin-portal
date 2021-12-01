/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Applicant chat contact value object.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
public class ApplicantChatContactVo implements Serializable {

    private static final long serialVersionUID = -8147938876661428572L;

    private String uin;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private String alias;

    @Size(min = 5, max = 20)
    private String mobileNumber;
}

/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Applicant Incident value object.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
public class ApplicantIncidentVo implements Serializable {

    private static final long serialVersionUID = 6800775521807599452L;

    private String operation;

    private String resolutionComment;
}

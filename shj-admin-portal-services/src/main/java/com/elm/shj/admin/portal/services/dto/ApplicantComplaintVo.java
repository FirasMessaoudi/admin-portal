/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Applicant complaint value object.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Getter
@Setter
public class ApplicantComplaintVo implements Serializable {

    private static final long serialVersionUID = 1382831750888750241L;

    private EComplaintResolutionType operation;

    private String resolutionComment;
}

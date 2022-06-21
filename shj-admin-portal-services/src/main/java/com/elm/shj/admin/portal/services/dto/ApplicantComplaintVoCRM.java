/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ApplicantComplaintVoCRM implements Serializable {

    private static final long serialVersionUID = 6800775521807599452L;

    @JsonProperty("Status")
    private int status;
    @JsonProperty("Comments")
    private String resolutionComment;

    @JsonProperty("CRMTicketNumber")
    private String crmTicketNumber;
    @JsonProperty("SmartIDTicketNumber")
    private String smartIDTicketNumber;
}

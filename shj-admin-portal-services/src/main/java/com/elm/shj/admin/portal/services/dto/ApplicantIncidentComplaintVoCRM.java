/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Applicant complaint value object for crm.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Setter
public class ApplicantIncidentComplaintVoCRM implements Serializable {

    private static final long serialVersionUID = 6800775521807599452L;

    @JsonProperty("Status")
    private Integer status;
    @JsonProperty("Comments")
    private String resolutionComment;

    @JsonProperty("CRMTicketNumber")
    private String crmTicketNumber;
    @JsonProperty("SmartIDTicketNumber")
    private String smartIDTicketNumber;

    @JsonProperty("MainType")
    private Integer mainType;// 1: Incident, 2: Complaint


    @JsonProperty("Status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("Comments")
    public String getResolutionComment() {
        return resolutionComment;
    }

    @JsonProperty("CRMTicketNumber")
    public String getCrmTicketNumber() {
        return crmTicketNumber;
    }

    @JsonProperty("SmartIDTicketNumber")
    public String getSmartIDTicketNumber() {
        return smartIDTicketNumber;
    }

    public Integer getMainType() {
        return mainType;
    }
}

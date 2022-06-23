/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.io.Serializable;

/**
 * Applicant complaint value object for crm.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Setter
public class ApplicantCreateComplaintVoCRM implements Serializable {

    private static final long serialVersionUID = 6800775521807599452L;
    private String digitalID;//UIN
    private String idNumber;// NIN in case Internal
    private String passportNumber;// in case External
    private String nationalityCode;
    private Integer mainType;// 1: Incident, 2: Complaint
    private String smartIDTicketNumber;// Reference number
    private String ticketDetails;
    private Integer ticketSubType;
    private String registerDateTime;
    private Double locationLng;
    private Double locationLat;
    private String attachmentId;
    private String attchementType;
    private Integer city;
    private String campNumber;

    @JsonProperty("digitalID")
    public String getDigitalID() {
        return digitalID;
    }

    @JsonProperty("idNumber")
    public String getIdNumber() {
        return idNumber;
    }

    @JsonProperty("passportNumber")
    public String getPassportNumber() {
        return passportNumber;
    }

    @JsonProperty("NationalityCode")
    public String getNationalityCode() {
        return nationalityCode;
    }

    @JsonProperty("MainType")
    public Integer getMainType() {
        return mainType;
    }

    @JsonProperty("SmartIDTicketNumber")
    public String getSmartIDTicketNumber() {
        return smartIDTicketNumber;
    }

    @JsonProperty("TicketDetails")
    public String getTicketDetails() {
        return ticketDetails;
    }

    @JsonProperty("TicketSubType")
    public Integer getTicketSubType() {
        return ticketSubType;
    }

    @JsonProperty("RegisterDateTime")
    public String getRegisterDateTime() {
        return registerDateTime;
    }

    @JsonProperty("LocationLng")
    public Double getLocationLng() {
        return locationLng;
    }

    @JsonProperty("LocationLat")
    public Double getLocationLat() {
        return locationLat;
    }

    @JsonProperty("AttachmentId")
    public String getAttachmentId() {
        return attachmentId;
    }

    @JsonProperty("AttchementType")
    public String getAttchementType() {
        return attchementType;
    }

    @JsonProperty("City")
    public Integer getCity() {
        return city;
    }

    @JsonProperty("CampNumber")
    public String getCampNumber() {
        return campNumber;
    }
}

/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for applicant incident domain.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ApplicantIncidentDto implements Serializable {

    private static final long serialVersionUID = -5654394816918782403L;

    private long id;
    private ApplicantRitualDto applicantRitual;
    private String statusCode;
    private String referenceNumber;
    private String typeCode;
    @NotNull(message = "validation.data.constraints.msg.20001")
    @Size(min = 1, max = 500)
    private String description;
    private Double locationLat;
    private Double locationLng;
    private String resolutionComment;
    private List<IncidentAttachmentDto> incidentAttachments;
    private Date creationDate;
    private Date updateDate;
    private String areaCode;
    private String city;
    private String campNumber;
    private String crmTicketNumber;
    private String mobileNumber;

}

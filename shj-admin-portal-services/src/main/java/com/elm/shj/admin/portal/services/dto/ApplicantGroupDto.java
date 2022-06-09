/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the applicant group domain.
 *
 * @author jaafer jarray
 * @since 1.1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApplicantGroupDto implements Serializable {

    private static final long serialVersionUID = 7617423887048517970L;

    private long id;

    private long localOfficeId;

    private String referenceNumber;

    private Date arrivalDate;

    private Date departureDate;

    private CompanyStaffDto groupLeader;

    @JsonBackReference
    private CompanyRitualSeasonDto companyRitualSeason;

    private String groupTypeCode;

    private String entryTransportationTypeCode;

    private String groupName;

    private Date creationDate;

    private Date updateDate;

    @JsonBackReference(value = "groupApplicantLists")
    private List<GroupApplicantListDto> groupApplicantLists;

    @JsonBackReference(value = "companyRitualSteps")
    private List<CompanyRitualStepDto> companyRitualSteps;
}

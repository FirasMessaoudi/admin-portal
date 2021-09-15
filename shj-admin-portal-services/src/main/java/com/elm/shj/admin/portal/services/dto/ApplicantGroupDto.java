/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualSeason;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStep;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaff;
import com.elm.shj.admin.portal.orm.entity.JpaGroupApplicantList;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
public class ApplicantGroupDto implements Serializable {

    private static final long serialVersionUID = 7617423887048517970L;

    private long id;

    private long localOfficeId;

    private String referenceNumber;

    private Date arrivalDate;

    private Date departureDate;

    private CompanyStaffDto groupLeader;

    private CompanyRitualSeasonDto companyRitualSeason;

    private String groupTypeCode;

    private String entryTransportationTypeCode;

    private Date creationDate;

    private Date updateDate;

    @JsonBackReference(value = "groupApplicantLists")
    private List<GroupApplicantListDto> groupApplicantLists;

    @JsonBackReference(value = "companyRitualSteps")
    private List<CompanyRitualStepDto> companyRitualSteps;
}

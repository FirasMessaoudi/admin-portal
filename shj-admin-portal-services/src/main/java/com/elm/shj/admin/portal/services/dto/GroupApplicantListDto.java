/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * Dto class for the group applicant list domain.
 *
 * @author jaafer jarray
 * @since 1.1.0
 */
@AllArgsConstructor
@Data
@Builder
public class GroupApplicantListDto implements Serializable {

    private static final long serialVersionUID = 7014050783686266825L;

    public GroupApplicantListDto(){}

    private long id;

    private ApplicantGroupDto applicantGroup;

    private String applicantUin;

    private Date creationDate;

}

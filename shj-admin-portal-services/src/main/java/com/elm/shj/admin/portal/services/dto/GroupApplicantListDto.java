/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantGroup;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * Dto class for the group applicant list domain.
 *
 * @author jaafer jarray
 * @since 1.1.0
 */
@NoArgsConstructor
@Data
public class GroupApplicantListDto implements Serializable {

    private static final long serialVersionUID = 7014050783686266825L;

    private long id;

    private JpaApplicantGroup applicantGroup;

    private String applicantUin;

    private Date creationDate;

}

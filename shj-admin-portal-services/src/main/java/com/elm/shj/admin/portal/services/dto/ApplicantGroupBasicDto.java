/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaff;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the applicant group domain.
 *
 * @author ramiz imtiaz
 * @since 1.2.6
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApplicantGroupBasicDto implements Serializable {

    private long id;
    private long localOfficeId;
    private String referenceNumber;
    private Date arrivalDate;
    private Date departureDate;
    private Long groupLeaderId;
    private Long companyRitualSeasonId;
    private String groupTypeCode;
    private String entryTransportationTypeCode;
    private String groupName;
    private Date creationDate;
    private Date updateDate;
}

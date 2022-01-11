/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the company staff card domain.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStaffCardDto implements Serializable {

    private static final long serialVersionUID = 6897431323864677377L;

    private long id;
    private String referenceNumber;
    private String statusCode;
    private CompanyRitualSeasonDto companyRitualSeason;
    private CompanyStaffDigitalIdDto companyStaffDigitalId;
    private Date creationDate;
    private Date updateDate;
    private Long batchNumber;

}

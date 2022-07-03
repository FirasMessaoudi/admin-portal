/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the company staff digital id domain.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyStaffDigitalIdDto implements Serializable {

    private static final long serialVersionUID = 7921153249196768040L;

    private long id;

    private String suin;

    private String statusCode;

    private int seasonYear;

    private CompanyStaffDto companyStaff;

    private Date creationDate;

    private Date updateDate;

}

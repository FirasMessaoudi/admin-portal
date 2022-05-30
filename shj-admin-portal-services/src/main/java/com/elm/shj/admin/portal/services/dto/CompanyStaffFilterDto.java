/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;

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
public class CompanyStaffFilterDto implements Serializable {

    private static final long serialVersionUID = 6897431323864677377L;

    private String gender;
    private String ritualType;
    private String companyCode;
    private String passportNumber;
    private String suin;
    private String idNumber;
    private String jobTitle;
    private String language;
    private String name;
}

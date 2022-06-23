/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the applicant company staff lite domain.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStaffLiteDto implements Serializable {

    private static final long serialVersionUID = 531849258704172988L;
    private long id;
    private String suin;
    private String fullNameAr;
    private String fullNameEn;
    private String email;
    private String titleCode;
    private String customJobTitle;
    private String countryCode;
    private String mobileNumber;
    private String mobileNumberIntl;
    private String countryPhonePrefix;
    private String nationalityCode;
    private String photo;
    private Date dateOfBirthGregorian;
    private Long dateOfBirthHijri;
    private String gender;
    private List<CompanyStaffDigitalIdDto> digitalIds;
    private String passportNumber;
    private String idNumber;
    private Double latitude;
    private Double longitude;

}

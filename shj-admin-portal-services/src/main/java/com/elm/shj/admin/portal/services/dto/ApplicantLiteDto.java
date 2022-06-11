/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the applicant lite domain.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ApplicantLiteDto implements Serializable {

    private static final long serialVersionUID = 2708176152525069666L;

    private long id;
    private String nationalityCode;
    private String idNumber;
    private String passportNumber;
    private String packageReferenceNumber;
    private Date dateOfBirthGregorian;
    private Long dateOfBirthHijri;
    private String fullNameAr;
    private String fullNameEn;
    private String fullNameOrigin;
    private String preferredLanguage;
    private String gender;
    //TODO(aflaifel): Remove this or user another version of JpaApplicant without photo
    private String photo;
    private List<ApplicantContactDto> contacts;
    private List<ApplicantDigitalIdDto> digitalIds;

    private String email;
    private boolean hasLocalMobileNumber;
    private String mobileNumber;
    private String countryCode;
    private boolean deleted;
}

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
 * Dto class for the applicant main data domain.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class ApplicantMainDataDto implements Serializable {

    private static final long serialVersionUID = -211394632324992513L;

    /*From Applicant table*/
    private long id;
    private String gender;
    private String nationalityCode;
    private String idNumber;
    private String idNumberOriginal;
    private String passportNumber;
    private Date dateOfBirthGregorian;
    private Long dateOfBirthHijri;
    private String fullNameAr;
    private String fullNameEn;
    private String fullNameOrigin;
    private String maritalStatusCode;
    private String photo;

    /*From Applicant, Applicant Ritual and Applicant Relatives*/
    private List<ApplicantRelativeDto> relatives;

    /*From Applicant and Applicant Contacts*/
    private List<ApplicantContactDto> contacts;

    /*From Applicant Ritual, Applicant Package, Ritual Package, Company Ritual Season and Ritual Season*/
    private String ritualTypeCode;

    /*From Applicant Ritual and Applicant Card*/
    private String cardReferenceNumber;
    private String cardStatusCode;

    /*From Applicant and Applicant Digital ID*/
    private String statusCode;
    private String uin;

    private String preferredLanguage;
    private String groupNumber;
}

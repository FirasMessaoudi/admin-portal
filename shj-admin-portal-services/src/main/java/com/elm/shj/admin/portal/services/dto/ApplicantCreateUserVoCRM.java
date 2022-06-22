/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.io.Serializable;

/**
 * Applicant profile value object for crm.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Setter
public class ApplicantCreateUserVoCRM implements Serializable {

    private static final long serialVersionUID = 2966323659223961837L;

    private Integer customerType;// 1p: pilgrim, 2: staff
    private String digitalID;// UIN
    private String idNumber;// NIN in case Internal
    private String passportNumber;// in case External
    private String fullNameEn;
    private String gender;
    private String mobileNumber;
    private String nationalityCode;
    private String dateOfBirthHijri;
    private String dateOfBirth;
    private String fullNameAr;
    private String fullNameOrginalLang;
    private String email;

    @JsonProperty("customerType")
    public Integer getCustomerType() {
        return customerType;
    }

    @JsonProperty("digitalID")
    public String getDigitalID() {
        return digitalID;
    }

    @JsonProperty("idNumber")
    public String getIdNumber() {
        return idNumber;
    }

    @JsonProperty("passportNumber")
    public String getPassportNumber() {
        return passportNumber;
    }

    @JsonProperty("fullNameEn")
    public String getFullNameEn() {
        return fullNameEn;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("mobileNumber")
    public String getMobileNumber() {
        return mobileNumber;
    }

    @JsonProperty("nationalityCode")
    public String getNationalityCode() {
        return nationalityCode;
    }

    @JsonProperty("dateOfBirthHijri")
    public String getDateOfBirthHijri() {
        return dateOfBirthHijri;
    }

    @JsonProperty("dateOfBirth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty("fullNameAr")
    public String getFullNameAr() {
        return fullNameAr;
    }

    @JsonProperty("fullNameOrginalLang")
    public String getFullNameOrginalLang() {
        return fullNameOrginalLang;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }
}

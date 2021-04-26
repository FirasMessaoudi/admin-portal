/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the applicant domain.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@NoArgsConstructor
@Data
public class ApplicantDto implements Serializable {

    private static final long serialVersionUID = 4276580006724069703L;

    private long id;
    private String gender;
    private String nationalityCode;
    private long idNumber;
    private String idNumberOriginal;
    private String passportNumber;
    private Date dateOfBirthGregorian;
    private long dateOfBirthHijri;
    private String fullNameAr;
    private String fullNameEn;
    private String fullNameOrigin;
    private long maritalStatus;
    private String photo;
    private long requestId;
    private List<ApplicantDigitalIdDto> digitalIds;
    private List<ApplicantRelativeDto> relatives;
    private List<ApplicantRitualDto> rituals;
    private List<ApplicantContactDto> contacts;
    private List<ApplicantHealthDto> healths;
    private long status;
    private Date creationDate;
    private Date updateDate;
}

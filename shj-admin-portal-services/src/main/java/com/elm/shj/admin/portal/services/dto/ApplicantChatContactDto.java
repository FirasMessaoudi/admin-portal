/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant chat contact domain.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ApplicantChatContactDto implements Serializable {

    private static final long serialVersionUID = -4684813036429505035L;

    private Long id;
    private String uin;
    private ApplicantRitualDto applicantRitual;
    private String contactUin;
    private String alias;
    private String photoFilePath;
    private String mobileNumber;
    private String contactTypeCode;
    private Boolean systemDefined;
    private Boolean deleted;
    private Date creationDate;
    private Date updateDate;
}

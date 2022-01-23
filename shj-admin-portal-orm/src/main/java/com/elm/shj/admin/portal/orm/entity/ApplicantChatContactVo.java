/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Value object class for returned applicant chat contact.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantChatContactVo implements Serializable {

    private static final long serialVersionUID = 8160457570099912194L;

    private long id;
    private String applicantUin;
    private String contactUin;
    private String contactFullNameAr;
    private String contactFullNameEn;
    private long typeId;
    private String alias;
    private String avatar;
    private Boolean systemDefined;
    private String staffTitleCode;
    private String relationshipCode;
    private String mobileNumber;
    private String countryPhonePrefix;
    private String countryCode;
    private boolean autoAdded;
    private Long applicantRitualId;
    private Date creationDate;
    private Date updateDate;
    private String statusCode;
}

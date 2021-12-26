/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

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
@AllArgsConstructor
@Builder
public class ApplicantChatContactDto implements Serializable {

    private static final long serialVersionUID = -4684813036429505035L;

    private Long id;
    private String applicantUin;
    private String contactUin;
    private ContactTypeLookupDto type;
    private String alias;
    private String avatar;
    private Boolean systemDefined;
    private String staffTitleCode;
    private String relationshipCode;
    private String mobileNumber;
    private String countryPhonePrefix;
    private String countryCode;
    private Boolean deleted;
    private Boolean isAutomatically;
    private ApplicantRitualDto applicantRitual;
    private Date creationDate;
    private Date updateDate;
}

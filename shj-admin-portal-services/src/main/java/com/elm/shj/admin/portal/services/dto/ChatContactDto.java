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
public class ChatContactDto implements Serializable {

    private static final long serialVersionUID = -4684813036429505035L;

    private long id;
    private String digitalId;
    private String contactDigitalId;
    private ContactTypeLookupDto type;
    private String alias;
    private String avatar;
    private boolean systemDefined;
    private String staffTitleCode;
    private String relationshipCode;
    private String mobileNumber;
    private String countryPhonePrefix;
    private String countryCode;
    private boolean deleted;
    private boolean autoAdded;
    private Long applicantRitualId;
    private Date creationDate;
    private Date updateDate;
}

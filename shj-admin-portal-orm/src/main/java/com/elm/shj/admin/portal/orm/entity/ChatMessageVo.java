/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Value object class for returned applicant chat message domain.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ChatMessageVo implements Serializable {

    private static final long serialVersionUID = -4352620262606672595L;

    private long id;
    private String digitalId;
    private String contactDigitalId;
    private String contactFullNameAr;
    private String contactFullNameEn;
    private long typeId;
    private String alias;
    private String avatar;
    private boolean systemDefined;
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
    private boolean deleted;
    private String messageText;
    private Date sentDate;
    private Date receivedDate;
    private Date readDate;
    private long unreadMessagesCount;

    public ChatMessageVo(long id, String digitalId, String contactDigitalId, String contactFullNameAr, String contactFullNameEn, String statusCode, long typeId, String alias, String avatar, boolean systemDefined, String staffTitleCode, String relationshipCode, String mobileNumber, String countryPhonePrefix, String countryCode, boolean autoAdded, boolean deleted, String messageText, Date sentDate, Date receivedDate, Date readDate, Date creationDate) {
        this.id = id;
        this.digitalId = digitalId;
        this.contactDigitalId = contactDigitalId;
        this.contactFullNameAr = contactFullNameAr;
        this.contactFullNameEn = contactFullNameEn;
        this.typeId = typeId;
        this.alias = alias;
        this.avatar = avatar;
        this.systemDefined = systemDefined;
        this.staffTitleCode = staffTitleCode;
        this.relationshipCode = relationshipCode;
        this.mobileNumber = mobileNumber;
        this.countryPhonePrefix = countryPhonePrefix;
        this.countryCode = countryCode;
        this.autoAdded = autoAdded;
        this.statusCode = statusCode;
        this.deleted = deleted;
        this.messageText = messageText;
        this.sentDate = sentDate;
        this.receivedDate = receivedDate;
        this.readDate = readDate;
        this.creationDate = creationDate;
    }
}
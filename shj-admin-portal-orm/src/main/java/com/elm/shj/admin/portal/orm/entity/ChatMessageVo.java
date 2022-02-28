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

    private long contactId;
    private String contactUin;
    private String messageText;
    private Date sentDate;
    private Date receivedDate;
    private Date readDate;
    private boolean deleted;
    private long unreadMessagesCount;

    public ChatMessageVo(long contactId, String contactUin, String messageText, Date sentDate, Date receivedDate, Date readDate, boolean deleted) {
        this.contactId = contactId;
        this.contactUin = contactUin;
        this.messageText = messageText;
        this.sentDate = sentDate;
        this.receivedDate = receivedDate;
        this.readDate = readDate;
        this.deleted = deleted;
    }
}
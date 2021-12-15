/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantChatContact;
import com.elm.shj.admin.portal.orm.entity.JpaChatMessageTypeLookup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant chat message domain.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ChatMessageDto implements Serializable {

    private static final long serialVersionUID = 4745158769022455992L;

    private long id;
    private String text;
    private JpaChatMessageTypeLookup type;
    private String contentFilePath;
    private JpaApplicantChatContact sender;
    private JpaApplicantChatContact receiver;
    private Date sentDate;
    private Date receivedDate;
    private Date readDate;
    private Boolean deleted;
    private Date creationDate;
    private Date updateDate;
}

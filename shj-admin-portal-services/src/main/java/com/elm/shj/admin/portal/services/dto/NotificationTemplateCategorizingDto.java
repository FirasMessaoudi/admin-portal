/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the notification template categorizing domain.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class NotificationTemplateCategorizingDto implements Serializable {

    private static final long serialVersionUID = 35290787428528516L;

    private long id;
    @JsonBackReference
    private NotificationTemplateDto notificationTemplate;
    private Long campId;
    private Long companyId;
    private String nationalityCode;
    private Integer minAge;
    private Integer maxAge;
    private String gender;
    private Date creationDate;
    private Date updateDate;
}

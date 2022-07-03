/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.validators.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    @Min(0)
    @Max(120)
    private Integer minAge;

    @Max(120)
    private Integer maxAge;

    @Gender
    private String gender;

    private String idNumber;
    private String uin;
    private String passportNumber;
    private String staffTitle;
    private String suin;
    private Integer notificationCategory;

    private String selectedApplicants;

    private Long selectedGroupId;

    private String selectedStaff;

    private Date creationDate;
    private Date updateDate;
}

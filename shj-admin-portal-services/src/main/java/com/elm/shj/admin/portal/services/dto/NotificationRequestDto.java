/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Dto class for the user notification request domain.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequestDto implements Serializable {

    private static final long serialVersionUID = -2499947945567039208L;

    private long id;
    @JsonBackReference
    @NotNull(message = "validation.data.constraints.msg.20001")
    private NotificationTemplateDto notificationTemplate;
    @NotNull(message = "validation.data.constraints.msg.20001")
    private long userId;
    private NotificationProcessingStatusLookupDto processingStatus;
    @NotNull(message = "validation.data.constraints.msg.20001")
    private String userLang;
    @NotNull(message = "validation.data.constraints.msg.20001")
    private Date sendingDate;
    private Set<NotificationRequestParameterValueDto> notificationRequestParameterValues;
    private Date creationDate;
    private Date updateDate;

}

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
 * Dto class for incident attachment domain.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class IncidentAttachmentDto implements Serializable {

    private static final long serialVersionUID = 8743710249950641326L;

    private long id;
    private String filePath;
    private ApplicantIncidentDto applicantIncident;
    private Date creationDate;
}

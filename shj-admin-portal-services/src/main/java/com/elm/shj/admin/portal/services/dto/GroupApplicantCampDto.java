/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for applicant complaint domain.
 *
 * @author rameez imtiaz
 * @since 1.2.6
 */
@Getter
@Setter
@NoArgsConstructor
public class GroupApplicantCampDto implements Serializable {

    private Long groupId;
    private String menaCampRefNumber;
    private String arafatCampRefNumber;
}

/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;


/**
 * Dto class for the update applicant camp dto.
 *
 * @author r.imtiaz
 * @since 1.1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateApplicantHousingCampDto implements Serializable {


    private static final long serialVersionUID = -4449354665489813468L;

    private String applicantUin;

    private String menaCampRefCode;
    private String menaTent;
    private String menaFloor;
    private String menaCorridor;
    private String menaRoom;
    private String menaBedNumber;

    private String arafatCampRefCode;
    private String arafatTent;
    private String arafatFloor;
    private String arafatCorridor;
    private String arafatRoom;
    private String arafatBedNumber;


}

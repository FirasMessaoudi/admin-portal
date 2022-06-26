/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Dto class for applicant camp details domain.
 *
 * @author rameez imtiaz
 * @since 1.2.6
 */
@Getter
@Setter
@NoArgsConstructor
public class ApplicantCampDetailDto implements Serializable {


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

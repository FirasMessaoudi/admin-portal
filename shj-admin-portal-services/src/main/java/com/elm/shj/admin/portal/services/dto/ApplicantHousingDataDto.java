/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.validators.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;


/**
 * Dto class for the applicant housing data.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@WithApplicant
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApplicantHousingDataDto implements Serializable {


    private static final long serialVersionUID = -4449354665489813468L;
    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    @CellIndex(index = 0)
    private String idNumber;

    @PassportNumber
    @CellIndex(index = 1)
    private String passportNumber;

    @NullOrNotBlank(min = 1, max = 30)
    @CellIndex(index = 2)
    @JsonIgnore
    private String nationality;

    @NationalityCode
    @CellIndex(index = 3)
    private String nationalityCode;

    @CellIndex(index = 4)
    @NullOrNotBlank(min = 1, max = 30)
    private String menaCampRefCode;

    @CellIndex(index = 5)
    @NullOrNotBlank(min = 1, max = 30)
    private String menaTent;

    @CellIndex(index = 6)
    @NullOrNotBlank(min = 1, max = 30)
    private String menaFloor;

    @CellIndex(index = 7)
    @NullOrNotBlank(min = 1, max = 30)
    private String menaCorridor;

    @CellIndex(index = 8)
    @NullOrNotBlank(min = 1, max = 30)
    private String menaRoom;

    @CellIndex(index = 9)
    @NullOrNotBlank(min = 1, max = 30)
    private String menaBedNumber;


    @CellIndex(index = 10)
    @NullOrNotBlank(min = 1, max = 30)
    private String arafetCampRefCode;

    @CellIndex(index = 11)
    @NullOrNotBlank(min = 1, max = 30)
    private String arafetTent;

    @CellIndex(index = 12)
    @NullOrNotBlank(min = 1, max = 30)
    private String arafetFloor;

    @CellIndex(index = 13)
    @NullOrNotBlank(min = 1, max = 30)
    private String arafetCorridor;

    @CellIndex(index = 14)
    @NullOrNotBlank(min = 1, max = 30)
    private String arafetRoom;

    @CellIndex(index = 15)
    @NullOrNotBlank(min = 1, max = 30)
    private String arafetBedNumber;


}

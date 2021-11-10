/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import lombok.Data;

import java.io.Serializable;

/**
 * Dto class for the applicant emergency data.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Data
public class ApplicantEmergencyDto implements Serializable {

    private static final long serialVersionUID = -2027005588974249665L;

    @NestedCells
    private ApplicantRitualEmergencyDto applicantRitualEmergencyDto;
    @NestedCells
    private ApplicantInfoEmergencyDto applicantInfoEmergencyDto;





}

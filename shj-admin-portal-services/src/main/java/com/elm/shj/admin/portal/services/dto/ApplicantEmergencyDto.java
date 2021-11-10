/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.elm.shj.admin.portal.services.data.validators.*;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the applicant emergency data.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Data
public class ApplicantEmergencyDto implements Serializable {

    @NestedCells
    private ApplicantRitualEmergencyDto applicantRitualEmergencyDto;
    @NestedCells
    private ApplicantInfoEmergencyDto applicantInfoEmergencyDto;





}

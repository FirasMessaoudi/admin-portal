/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.validators.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * Dto class for the group data.
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
public class GroupDataDto implements Serializable {


    private static final long serialVersionUID = 5240863164971764913L;
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
    private String groupReferenceNumber;

    @CellIndex(index = 5)
    @NullOrNotBlank(min = 1, max = 30)
    @JsonIgnore
    private String ritualType;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @CellIndex(index = 6)
    @RitualTypeCode
    private String ritualTypeCode;

}

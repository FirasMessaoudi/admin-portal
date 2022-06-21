/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.validators.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Dto class for the group main data .
 *
 * @author f.messaoudi
 * @since 1.1.0
 */

@WithGroupLeaderCompanyStaff
@FieldDependency.List({
        @FieldDependency(first = "staffIdNumber", second = "staffPassportNumber")
})

@NoArgsConstructor
@Getter
@Setter
public class GroupMainDataDto implements Serializable {

    private static final long serialVersionUID = 5103617124678932708L;
    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    @CellIndex(index = 0)
    private String staffIdNumber;

    @PassportNumber
    @CellIndex(index = 1)
    private String staffPassportNumber;

    @NullOrNotBlank(min = 1, max = 30)
    @CellIndex(index = 2)
    @JsonIgnore
    private String nationality;

    @NationalityCode
    @CellIndex(index = 3)
    private String nationalityCode;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @CellIndex(index = 4)
    private String groupReferenceNumber;

    @CellIndex(index = 5)
    private String groupName;

    @CellIndex(index = 6)
    @NullOrNotBlank(min = 1, max = 30)
    @JsonIgnore
    private String ritualType;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @CellIndex(index = 7)
    @RitualTypeCode
    private String ritualTypeCode;


}

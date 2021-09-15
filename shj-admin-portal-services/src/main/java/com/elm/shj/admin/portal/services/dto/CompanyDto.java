/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.validators.NullOrNotBlank;
import com.elm.shj.admin.portal.services.data.validators.OnlyCharacters;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Dto class for the company.
 *
 * @author salzoubi
 * @since 1.1.0
 */

@NoArgsConstructor
@Data
public class CompanyDto {

    private static final long serialVersionUID = 436091561114554168L;
    private long id;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @OnlyCharacters(min = 3, max = 20, allowEmpty = false, arabic = false, allowNumbers = true, allowSpecialChars = false)
    private String code;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @OnlyCharacters(min = 3, max = 50, allowEmpty = false, arabic = false, allowNumbers = true, allowSpecialChars = false)
    private String labelAr;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @OnlyCharacters(min = 3, max = 25, allowEmpty = false, arabic = false, allowNumbers = true, allowSpecialChars = false)
    private String labelEn;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private int missionId;

    @Max(20)
    @Min(10)
    @NotNull(message = "validation.data.constraints.msg.20001")
    private String contactNumber;

    @NullOrNotBlank(min = 5, max = 75)
    @Pattern(regexp = "((http|https)://)(www.)?"
            + "[a-zA-Z0-9@:%._\\\\+~#?&//=]{2,60}\\\\.[a-z]"
            + "{2,6}\\\\b([-a-zA-Z0-9@:%._\\\\+~#?&//=]*)"
            , message = "validation.data.constraints.msg.20003")
    private String website;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @OnlyCharacters(min = 3, max = 45, allowEmpty = false, arabic = false, allowNumbers = true, allowSpecialChars = false)
    private String accreditationOrganization;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @OnlyCharacters(min = 3, max = 45, allowEmpty = false, arabic = false, allowNumbers = true, allowSpecialChars = false)
    private String accreditationNumber;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private Date accreditationDate;

    @Future
    @NotNull(message = "validation.data.constraints.msg.20001")
    private Date accreditationExpiry;

    @NullOrNotBlank(min = 5, max = 50)
    @Email(message = "validation.data.constraints.msg.20003")
    private String email;

    private Date creationDate;
    private Date updateDate;

    @JsonBackReference(value = "companyRitualSeasons")
    private List<CompanyRitualSeasonDto> companyRitualSeasons;

    @JsonBackReference(value = "companyStaff")
    private List<CompanyStaffDto> companyStaff;

}

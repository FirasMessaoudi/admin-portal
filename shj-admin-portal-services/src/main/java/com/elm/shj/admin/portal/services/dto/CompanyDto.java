/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.validators.CompanyTypeCode;
import com.elm.shj.admin.portal.services.data.validators.NullOrNotBlank;
import com.elm.shj.admin.portal.services.data.validators.OnlyCharacters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the company.
 *
 * @author salzoubi
 * @since 1.1.0
 */

@NoArgsConstructor
@Getter
@Setter
public class CompanyDto {

    private static final long serialVersionUID = 436091561114554168L;
    private long id;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @OnlyCharacters(min = 1, max = 30, allowEmpty = false, arabic = false, allowNumbers = true, allowSpecialChars = false)
    private String code;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @OnlyCharacters(min = 10, max = 150, allowEmpty = false, arabic = true, allowNumbers = true, allowSpecialChars = false)
    private String labelAr;

    @OnlyCharacters(min = 10, max = 150, allowEmpty = false, arabic = false, allowNumbers = true, allowSpecialChars = false)
    private String labelEn;

    private Integer missionId;

    @NullOrNotBlank(min = 5, max = 20)
    private String contactNumber;

    @NullOrNotBlank(min = 5, max = 75)
    @Pattern(regexp = "(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?(\\/[a-z0-9])*(\\/?|(\\?[a-z0-9]=[a-z0-9](&[a-z0-9]=[a-z0-9]*)?))"
            , message = "validation.data.constraints.msg.20003")
    private String website;

    @OnlyCharacters(min = 3, max = 45, allowEmpty = true, arabic = false, allowNumbers = true, allowSpecialChars = false)
    private String accreditationOrganization;

    @OnlyCharacters(min = 3, max = 45, allowEmpty = true, arabic = false, allowNumbers = true, allowSpecialChars = false)
    private String accreditationNumber;

    private Date accreditationDate;

    @Future
    private Date accreditationExpiry;

    @NullOrNotBlank(min = 5, max = 50)
    @Email(message = "validation.data.constraints.msg.20003")
    private String email;

    @OnlyCharacters(min = 3, max = 30, allowEmpty = true, arabic = false, allowNumbers = true, allowSpecialChars = false)
    private String moiNumber;

    @OnlyCharacters(min = 1, max = 30, allowEmpty = true, arabic = false, allowNumbers = true, allowSpecialChars = false)
    private String crNumber;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @CompanyTypeCode
    private Integer typeCode;

    private Date creationDate;
    private Date updateDate;

    //@JsonManagedReference(value = "companyRitualSeasons")
    private List<CompanyRitualSeasonDto> companyRitualSeasons;


}

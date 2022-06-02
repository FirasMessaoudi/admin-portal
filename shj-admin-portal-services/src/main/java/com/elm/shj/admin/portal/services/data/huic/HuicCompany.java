package com.elm.shj.admin.portal.services.data.huic;

import com.elm.shj.admin.portal.services.data.validators.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * class for the company huic .
 *
 * @author f.messaoudi
 * @since 1.1.0
 */

@NoArgsConstructor
@Getter
@Setter
public class HuicCompany implements Serializable {

    private static final long serialVersionUID = -1268725762902906346L;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @Pattern(regexp = "([0-9]+)"
            , message = "validation.data.constraints.msg.20003")
    private String companyRefCode;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @OnlyCharacters(min = 10, max = 150, allowEmpty = false, arabic = true, allowNumbers = true, allowSpecialChars = false)
    private String companyNameAr;

    @OnlyCharacters(min = 10, max = 150, allowEmpty = false, arabic = false, allowNumbers = true, allowSpecialChars = false)
    private String companyNameEn;

    private String missionId;

    @NullOrNotBlank(min = 5, max = 20)
    @Pattern(regexp = "([0-9]+)"
            , message = "validation.data.constraints.msg.20003")
    private String companyContactNumber;

    @NullOrNotBlank(min = 5, max = 75)
    @Pattern(regexp = "(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?(\\/[a-z0-9])*(\\/?|(\\?[a-z0-9]=[a-z0-9](&[a-z0-9]=[a-z0-9]*)?))"
            , message = "validation.data.constraints.msg.20003")
    private String website;

    @NullOrNotBlank(min = 5, max = 50)
    @Email(message = "validation.data.constraints.msg.20003")
    private String companyEmail;

    @Pattern(regexp = "([0-9]+)"
            , message = "validation.data.constraints.msg.20003")
    private String moiNumber;
    @Pattern(regexp = "([0-9]+)"
            , message = "validation.data.constraints.msg.20003")
    private String crNumber;

    @CompanyTypeCode
    private Integer companyTypeCode;

    @RitualTypeCode
    private String ritualTypeCode;
    @SeasonYear
    private int seasonYear;
    @CountryCode
    private String country;

    private Integer establishmentId;
}

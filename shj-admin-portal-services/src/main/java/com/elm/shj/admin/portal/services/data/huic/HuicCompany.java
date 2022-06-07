package com.elm.shj.admin.portal.services.data.huic;

import com.elm.shj.admin.portal.services.data.validators.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * class for the company huic .
 *
 * @author f.messaoudi
 * @since 1.1.0
 */

@UniqueCompany
@NoArgsConstructor
@Getter
@Setter
public class HuicCompany implements Serializable {

    private static final long serialVersionUID = -1268725762902906346L;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private BigInteger companyRefCode;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @OnlyCharacters(min = 10, max = 150, allowEmpty = false, arabic = true, allowNumbers = true, allowSpecialChars = false)
    private String companyNameAr;

    @OnlyCharacters(min = 10, max = 150, allowEmpty = true, arabic = false, allowNumbers = true, allowSpecialChars = false)
    private String companyNameEn;

    @WithMission
    private Integer missionId;

    @NullOrNotBlank(min = 5, max = 20)
    private Long companyContactNumber;

    @NullOrNotBlank(min = 5, max = 75)
    @Pattern(regexp = "(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?(\\/[a-z0-9])*(\\/?|(\\?[a-z0-9]=[a-z0-9](&[a-z0-9]=[a-z0-9]*)?))"
            , message = "validation.data.constraints.msg.20003")
    private String website;

    @NullOrNotBlank(min = 5, max = 50)
    @Email(message = "validation.data.constraints.msg.20003")
    private String companyEmail;

    private BigInteger moiNumber;

    private BigInteger crNumber;

    @CompanyTypeCode
    private Long companyTypeCode;

    @RitualTypeCode
    private Long ritualTypeCode;
    @SeasonYear
    private int seasonYear;
    @NationalityCode
    private Long country;

    @EstablishmentCode
    private Integer establishmentId;
}

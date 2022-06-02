package com.elm.shj.admin.portal.services.data.huic;

import com.elm.dcc.foundation.commons.validation.ArabicCharacters;
import com.elm.shj.admin.portal.services.data.validators.CompanyTypeCode;
import com.elm.shj.admin.portal.services.data.validators.RitualTypeCode;
import com.elm.shj.admin.portal.services.data.validators.SeasonYear;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * class for the  Ritual Package huic .
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HuicPlannedPackage implements Serializable {
    private static final long serialVersionUID = -8577691725500853641L;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @Pattern(regexp = "([0-9]+)"
            , message = "validation.data.constraints.msg.20003")
    private String packageRefNumber;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @Pattern(regexp = "([0-9]+)"
            , message = "validation.data.constraints.msg.20003")
    private String companyRefCode;

    @Pattern(regexp = "(^[a-zA-Z0-9]*)"
            , message = "validation.data.constraints.msg.20003")
    private String hajjOfficeMakkah;
    @Pattern(regexp = "(^[a-zA-Z0-9]*)"
            , message = "validation.data.constraints.msg.20003")
    private String hajjOfficeMadina;
    @ArabicCharacters(lettersOnly = true, numbersOnly = false)
    private String packageNameArabic;
    @Pattern(regexp = "(^[a-zA-Z0-9]*)"
            , message = "validation.data.constraints.msg.20003")
    private String packageNameEnglish;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private String packageTypeCode;
    @CompanyTypeCode
    private Integer companyTypeCode;

    @JsonFormat(pattern = "yyyy-MM-dd")

    private Date packageStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date packageEndDate;

    @RitualTypeCode
    private String ritualTypeCode;
    @SeasonYear
    private int seasonYear;

    List<HuicPackageHousing> packageHousings;

    List<HuicPackageTransportation> packageTransportations;

}

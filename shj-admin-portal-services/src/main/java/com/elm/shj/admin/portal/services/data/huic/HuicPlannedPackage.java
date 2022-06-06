package com.elm.shj.admin.portal.services.data.huic;

import com.elm.dcc.foundation.commons.validation.ArabicCharacters;
import com.elm.shj.admin.portal.services.data.validators.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * class for the  Ritual Package huic .
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@WithCompanyRitualSeason
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HuicPlannedPackage implements Serializable {
    private static final long serialVersionUID = -8577691725500853641L;

    @NotNull(message = "validation.data.constraints.msg.20001")

    private Long packageRefNumber;
    @NotNull(message = "validation.data.constraints.msg.20001")
    private BigInteger companyRefCode;

    @WithServiceGroup
    private BigInteger hajjOfficeMakkah;
    @WithServiceGroup
    private BigInteger hajjOfficeMadina;
    @ArabicCharacters(lettersOnly = true, numbersOnly = false)
    private String packageNameArabic;
    @Pattern(regexp = "(^[a-zA-Z0-9]*)"
            , message = "validation.data.constraints.msg.20003")
    private String packageNameEnglish;


    @WithPackageType
    private Long packageTypeCode;
    @CompanyTypeCode
    private Long companyTypeCode;
    @EstablishmentCode
    private Long establishmentId;

    @HijriDate(minOffset = -1, maxOffset = 1)
    private Long packageStartDate;
    @HijriDate(minOffset = -1, maxOffset = 1)
    private Long packageEndDate;

    @RitualTypeCode
    private Long ritualTypeCode;
    @SeasonYear
    private int seasonYear;

    List<HuicPackageHousing> packageHousings;

    List<HuicPackageTransportation> packageTransportations;

}

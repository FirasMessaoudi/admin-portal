package com.elm.shj.admin.portal.services.data.huic;

import com.elm.shj.admin.portal.services.data.validators.*;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
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
    private Long companyRefCode;
    @WithServiceGroup
    private Long hajjOfficeMakkah;
    @WithServiceGroup
    private Long hajjOfficeMadina;

    private String packageNameArabic;

    private String packageNameEnglish;

    @WithPackageType
    private Integer packageTypeCode;
    @CompanyTypeCode
    private Integer companyTypeCode;
    @EstablishmentCode
    private Integer establishmentId;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @HijriDate(minOffset = -1, maxOffset = 1)
    private Long packageStartDate;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @HijriDate(minOffset = -1, maxOffset = 1)
    private Long packageEndDate;

    @RitualTypeCode
    private Integer ritualTypeCode;
    @SeasonYear
    private int seasonYear;

    @Valid
    List<HuicPackageHousing> packageHousings;

    @Valid
    List<HuicPackageTransportation> packageTransportations;

}

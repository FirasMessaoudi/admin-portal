package com.elm.shj.admin.portal.services.data.huic;

import com.elm.shj.admin.portal.services.data.validators.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

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
    private BigInteger companyRefCode;


    @NotNull(message = "validation.data.constraints.msg.20001")
    @NullOrNotBlank(min = 0, max = 650)
    private String companyNameAr;

    @NullOrNotBlank(min = 0, max = 650)
    private String companyNameEn;

    @WithMission
    private Long missionId;

    @NullOrNotBlank(min = 0, max = 50)
    private Long companyContactNumber;

    @NullOrNotBlank(min = 0, max = 200)
    private String website;

    @NullOrNotBlank(min = 0, max = 50)
    private String companyEmail;

    private Long moiNumber;

    private Long crNumber;

    @CompanyTypeCode
    private Integer companyTypeCode;

    @RitualTypeCode
    private Integer ritualTypeCode;
    @SeasonYear
    private int seasonYear;
    @CountryCode
    private Integer country;

    @EstablishmentCode
    private Integer establishmentId;
}

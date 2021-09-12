package com.elm.shj.admin.portal.services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Dto class for the company season package domain.
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@NoArgsConstructor
@Data
public class CompanySeasonPackageDto {

    private static final long serialVersionUID = 1220080282659766125L;

    private long id;

    private Date creationDate;

    private Date updateDate;

//    @JsonBackReference(value = "companyRitualSeason")
//    private CompanyRitualSeasonDto companyRitualSeason;

//    @JsonBackReference(value = "ritualPackage")
//    private RitualPackageDto ritualPackage;


}

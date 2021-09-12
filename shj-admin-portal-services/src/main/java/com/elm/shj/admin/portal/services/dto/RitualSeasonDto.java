package com.elm.shj.admin.portal.services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Dto class for the ritual season domain.
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@NoArgsConstructor
@Data
public class RitualSeasonDto {

    private static final long serialVersionUID = 5471593790584285097L;

    private long id;

    private int seasonYear;

    private String ritualTypeCode;

    private Date seasonStart;

    private Date seasonEnd;

    private boolean activated;

//    @JsonBackReference(value = "companyRitualSeasons")
//    private List<CompanyRitualSeasonDto> companyRitualSeasons;
}

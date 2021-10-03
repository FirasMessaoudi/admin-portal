package com.elm.shj.admin.portal.services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Dto class for the ritual season domain.
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@NoArgsConstructor
@Data
public class RitualSeasonDto implements Serializable {

    private static final long serialVersionUID = 5471593790584285097L;

    private long id;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private int seasonYear;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private String ritualTypeCode;

    private int seasonStart;

    private int seasonEnd;

    private boolean activated;

//    @JsonBackReference
//    private List<CompanyRitualSeasonLiteDto> companyRitualSeasons;
}

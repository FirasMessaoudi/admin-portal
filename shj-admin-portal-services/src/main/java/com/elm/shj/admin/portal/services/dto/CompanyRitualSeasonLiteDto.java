package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Dto class for the CompanyRitualSeason.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class CompanyRitualSeasonLiteDto implements Serializable {

    private static final long serialVersionUID = -4817159149155197430L;

    private long id;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private long seasonStart;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private long seasonEnd;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private RitualSeasonDto ritualSeason;
}

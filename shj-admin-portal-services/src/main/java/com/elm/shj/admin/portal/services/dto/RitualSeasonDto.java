package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    private Date seasonStart;

    private Date seasonEnd;

    private boolean activated;

    @JsonBackReference(value = "companyRitualSeasons")
    private List<CompanyRitualSeasonDto> companyRitualSeasons;
}

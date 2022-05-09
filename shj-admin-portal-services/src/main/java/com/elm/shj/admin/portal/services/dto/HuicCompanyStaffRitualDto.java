/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Dto class for huic company staff ritual data.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class HuicCompanyStaffRitualDto {
    private int season;
    private String ritualTypeCode;
    List<CompanyStaffRitualDto> companyStaffRituals;

}

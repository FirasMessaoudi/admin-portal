/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.company.CompanyRitualSeasonService;
import com.elm.shj.admin.portal.services.dto.CompanyRitualSeasonDto;
import com.elm.shj.admin.portal.services.dto.CompanyStaffRitualDto;
import com.elm.shj.admin.portal.services.dto.StaffApplicantGroupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * Validator for {@link WithCompanyStaff} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
public class WithCompanyRitualSeasonValidator implements ConstraintValidator<WithCompanyRitualSeason, Object> {

    @Autowired
    private CompanyRitualSeasonService companyRitualSeasonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        } else if(value instanceof StaffApplicantGroupDto){
            StaffApplicantGroupDto  staffApplicantGroupDto = (StaffApplicantGroupDto) value;
            CompanyRitualSeasonDto companyRitualSeason = companyRitualSeasonService.getLatestCompanyRitualSeasonByRitualSeason(staffApplicantGroupDto.getCompanyCode(), staffApplicantGroupDto.getRitualTypeCode(), staffApplicantGroupDto.getSeason());
            return companyRitualSeason != null;
        }else{
            CompanyStaffRitualDto companyStaffRitualDto = (CompanyStaffRitualDto) value;
            CompanyRitualSeasonDto companyRitualSeason = companyRitualSeasonService.getLatestCompanyRitualSeasonByRitualSeason(companyStaffRitualDto.getCompanyCode(), companyStaffRitualDto.getTypeCode(), companyStaffRitualDto.getSeason());
            return companyRitualSeason != null;
        }



    }

}

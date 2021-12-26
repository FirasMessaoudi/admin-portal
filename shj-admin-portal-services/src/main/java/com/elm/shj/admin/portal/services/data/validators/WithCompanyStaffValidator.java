/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.ApplicantBasicInfoDto;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.CompanyStaffRitualDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * Validator for {@link WithCompanyStaff} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
public class WithCompanyStaffValidator implements ConstraintValidator<WithCompanyStaff, Object> {

    @Autowired
    private CompanyStaffService companyStaffService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null || !value.getClass().isAssignableFrom(CompanyStaffRitualDto.class)) {
            return false;
        }

        CompanyStaffRitualDto companyStaffRitualDto = (CompanyStaffRitualDto) value;
        return companyStaffService.existsByBasicInfo(companyStaffRitualDto.getIdNumber(), companyStaffRitualDto.getPassportNumber(), companyStaffRitualDto.getDateOfBirthGregorian(), companyStaffRitualDto.getDateOfBirthHijri());

    }


}

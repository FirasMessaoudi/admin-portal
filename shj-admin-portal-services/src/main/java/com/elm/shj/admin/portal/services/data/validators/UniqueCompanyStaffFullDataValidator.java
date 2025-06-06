/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.CompanyStaffDto;
import com.elm.shj.admin.portal.services.dto.CompanyStaffFullDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

/**
 * Validator for {@link UniqueCompanyStaff} annotation
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Slf4j
public class UniqueCompanyStaffFullDataValidator implements ConstraintValidator<UniqueCompanyStaffFullData, Object> {

    @Autowired
    private CompanyStaffService  companyStaffService;

    @Value("${data.request.company.staff.override}")
    private boolean overrideCompanyStaffData;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null || !value.getClass().isAssignableFrom(CompanyStaffFullDataDto.class)) {
            return false;
        }
        // staff should not exist or override flag should be true
        CompanyStaffFullDataDto companyStaffDto = (CompanyStaffFullDataDto) value;
        if (companyStaffDto.getDateOfBirthGregorian() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(companyStaffDto.getDateOfBirthGregorian());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            companyStaffDto.setDateOfBirthGregorian(calendar.getTime());
        }
        return overrideCompanyStaffData || !companyStaffService.existsByBasicInfo(companyStaffDto.getIdNumber(), companyStaffDto.getPassportNumber(), companyStaffDto.getNationalityCode());
    }

}

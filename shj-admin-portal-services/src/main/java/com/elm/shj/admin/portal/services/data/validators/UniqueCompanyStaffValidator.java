/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.ApplicantBasicInfoDto;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.CompanyStaffDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link UniqueCompanyStaff} annotation
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Slf4j
public class UniqueCompanyStaffValidator implements ConstraintValidator<UniqueCompanyStaff, Object> {

    @Autowired
    private CompanyStaffService  companyStaffService;

    @Value("${data.request.company.staff.override}")
    private boolean overrideCompanyStaffData;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null || !value.getClass().isAssignableFrom(CompanyStaffDto.class)) {
            return false;
        }
        // staff should not exist or override flag should be true
        CompanyStaffDto companyStaffDto=   (CompanyStaffDto) value;

        return overrideCompanyStaffData || !companyStaffService.existsByBasicInfo(companyStaffDto.getIdNumber(), companyStaffDto.getPassportNumber(),companyStaffDto.getDateOfBirthGregorian(),companyStaffDto.getDateOfBirthHijri());
    }

}

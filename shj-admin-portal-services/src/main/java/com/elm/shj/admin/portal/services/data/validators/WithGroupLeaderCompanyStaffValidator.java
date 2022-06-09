/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.GroupMainDataDto;
import com.elm.shj.admin.portal.services.dto.StaffApplicantGroupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link WithGroupLeaderCompanyStaff} annotation
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Slf4j
public class WithGroupLeaderCompanyStaffValidator implements ConstraintValidator<WithGroupLeaderCompanyStaff, Object> {

    @Autowired
    private CompanyStaffService companyStaffService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null || (!value.getClass().isAssignableFrom(StaffApplicantGroupDto.class) && !value.getClass().isAssignableFrom(GroupMainDataDto.class))) {
            return false;
        }

        if (value.getClass().isAssignableFrom(StaffApplicantGroupDto.class)) {
            StaffApplicantGroupDto staffApplicantGroupDto = (StaffApplicantGroupDto) value;
            return companyStaffService.existsByBasicInfoAndTitleIsGroupLeader(staffApplicantGroupDto.getStaffIdNumber(), staffApplicantGroupDto.getStaffPassportNumber(), staffApplicantGroupDto.getStaffDateOfBirthGregorian(), staffApplicantGroupDto.getStaffDateOfBirthHijri());
        } else {
            GroupMainDataDto groupMainDataDto = (GroupMainDataDto) value;
            return companyStaffService.existsByBasicInfoAndTitleIsGroupLeader(groupMainDataDto.getStaffIdNumber(), groupMainDataDto.getStaffPassportNumber(), groupMainDataDto.getNationality());

        }


    }


}

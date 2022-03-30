/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.ApplicantBasicInfoDto;
import com.elm.shj.admin.portal.services.dto.ApplicantRelativeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

/**
 * Validator for {@link WithRelative} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
public class WithRelativeValidator implements ConstraintValidator<WithRelative, Object> {

    @Autowired
    private ApplicantService applicantService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null || !value.getClass().isAssignableFrom(ApplicantRelativeDto.class)) {
            return false;
        }
        // search relative applicant by his basic info from the database
        if (((ApplicantRelativeDto) value).getRelativeDateOfBirthGregorian() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(((ApplicantRelativeDto) value).getRelativeDateOfBirthGregorian());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            ((ApplicantRelativeDto) value).setRelativeDateOfBirthGregorian(calendar.getTime());
        }
        return applicantService.existsByBasicInfo(ApplicantBasicInfoDto.fromRelative((ApplicantRelativeDto) value));
    }

}

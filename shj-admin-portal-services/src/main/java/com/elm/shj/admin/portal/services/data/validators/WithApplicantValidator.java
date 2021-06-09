/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.ApplicantBasicInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * Validator for {@link WithApplicant} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
public class WithApplicantValidator implements ConstraintValidator<WithApplicant, Object> {

    @Autowired
    private ApplicantService applicantService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        Field applicantBasicInfoField = ReflectionUtils.findField(value.getClass(), "applicantBasicInfo");
        if (value == null || applicantBasicInfoField == null) {
            return false;
        }
        try {
            // make fields accessible
            ReflectionUtils.makeAccessible(applicantBasicInfoField);
            // get applicant basic info from the current object
            ApplicantBasicInfoDto applicantBasicInfo = (ApplicantBasicInfoDto) applicantBasicInfoField.get(value);
            // search applicant by his basic info from the database
            return applicantService.existsByBasicInfo(applicantBasicInfo);
        } catch (IllegalAccessException e) {
            ReflectionUtils.handleReflectionException(e);
        }
        return false;
    }

}

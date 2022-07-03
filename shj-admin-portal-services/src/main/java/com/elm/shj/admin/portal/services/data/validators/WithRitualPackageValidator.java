/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.RitualPackageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link WithRitualPackage} annotation
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
@Slf4j
public class WithRitualPackageValidator implements ConstraintValidator<WithRitualPackage, Object> {

    @Autowired
    private RitualPackageService ritualPackageService;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {


        return value == null || ritualPackageService.existRitualPackageByReferenceNumber(value.toString());

    }
}

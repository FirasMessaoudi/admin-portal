/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.lookup.MealTypeLookupService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link WithMealType} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
public class WithMealTypeValidator implements ConstraintValidator<WithMealType, Object> {

    @Autowired
    private MealTypeLookupService mealTypeLookupService;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        if (value == null || StringUtils.isBlank(value.toString())) {
            return false;
        } else {
            return mealTypeLookupService.existsByCode(value.toString());
        }
    }
}

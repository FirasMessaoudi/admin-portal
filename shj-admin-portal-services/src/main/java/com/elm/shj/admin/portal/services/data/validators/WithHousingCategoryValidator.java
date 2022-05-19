/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.lookup.HousingCategoryLookupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link WithHousingCategory} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
public class WithHousingCategoryValidator implements ConstraintValidator<WithHousingCategory, Object> {

    @Autowired
    private HousingCategoryLookupService housingCategoryLookupService;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        if (value == null) {
            return true;
        } else {
            return housingCategoryLookupService.existsByCode(value.toString());
        }
    }
}

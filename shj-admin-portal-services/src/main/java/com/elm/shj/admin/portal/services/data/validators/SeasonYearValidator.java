/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.utils.DateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link SeasonYear} annotation
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public class SeasonYearValidator implements ConstraintValidator<SeasonYear, Object> {
    private static final String MSG_20001 = "validation.data.constraints.msg.20001";

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {

        int seasonYear = (Integer) value;
        if (seasonYear == 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MSG_20001).addConstraintViolation();
            return false;
        }
        return (DateUtils.getCurrentHijriYear() == seasonYear || seasonYear == (DateUtils.getCurrentHijriYear() + 1));
    }

}

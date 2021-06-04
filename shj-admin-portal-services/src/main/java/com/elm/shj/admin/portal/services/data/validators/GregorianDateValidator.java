/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * Validator for {@link GregorianDate} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class GregorianDateValidator implements ConstraintValidator<GregorianDate, Object> {

    private static final String MSG_20008 = "validation.data.constraints.msg.20008";
    private static final String MSG_20001 = "validation.data.constraints.msg.20001";

    private Date min;
    private Date max;
    private boolean allowNull;

    /**
     * {@inheritDoc}
     */
    @SneakyThrows
    @Override
    public void initialize(final GregorianDate constraintAnnotation) {
        Date today = new Date();
        min = DateUtils.addYears(today, constraintAnnotation.minOffset());
        max = DateUtils.addYears(today, constraintAnnotation.maxOffset());
        allowNull = constraintAnnotation.allowNull();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        Date dateValue = (Date) value;

        if (dateValue == null) {
            if(!allowNull) {
                // build new violation message and add it
                context.buildConstraintViolationWithTemplate(MSG_20001).addConstraintViolation();
            }
            return allowNull;
        } else if (dateValue.before(min) || dateValue.after(max)) {
            // build new violation message and add it
            context.buildConstraintViolationWithTemplate(MSG_20008).addConstraintViolation();
            return false;
        }

        return true;
    }

}

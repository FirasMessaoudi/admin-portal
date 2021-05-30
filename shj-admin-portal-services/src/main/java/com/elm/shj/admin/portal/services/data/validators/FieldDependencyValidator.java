/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link FieldDependency} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class FieldDependencyValidator implements ConstraintValidator<FieldDependency, Object> {

    private String firstFieldName;
    private String secondFieldName;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final FieldDependency constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

            return firstObj != null || secondObj != null;
        } catch (final Exception ignore) {
            // ignore
        }
        return false;
    }

}

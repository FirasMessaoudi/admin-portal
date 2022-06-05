/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * Validator for {@link EstablishmentCode} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public class EstablishmentCodeValidator implements ConstraintValidator<EstablishmentCode, Object> {

    private final static List<String> ESTABLISHMENT_CODES = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return value == null || ESTABLISHMENT_CODES.stream().anyMatch(value.toString()::equalsIgnoreCase);
    }

}

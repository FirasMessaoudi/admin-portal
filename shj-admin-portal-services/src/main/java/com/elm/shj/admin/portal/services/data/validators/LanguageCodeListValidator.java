/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.lookup.LanguageLookupService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Validator for {@link LanguageCodeList} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class LanguageCodeListValidator implements ConstraintValidator<LanguageCodeList, Object> {

    @Autowired
    private LanguageLookupService languageLookupService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return value == null || Arrays.stream(value.toString().split(",")).allMatch(c -> languageLookupService.existsByCode(c.toUpperCase()));
    }

}

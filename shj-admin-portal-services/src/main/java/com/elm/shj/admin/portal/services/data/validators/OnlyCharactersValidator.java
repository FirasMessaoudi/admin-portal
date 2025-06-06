/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link OnlyCharacters} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class OnlyCharactersValidator implements ConstraintValidator<OnlyCharacters, Object> {

    private static final String REGEX_TPL = "^[${CHARSET}${NUMBERS}${SPECIALS}\\s-_]+$";
    private static final String ARABIC_LETTERS_REGEX = "\\p{InArabic}";
    private static final String LATIN_LETTERS_REGEX = "\\p{IsLatin}";
    private static final String SPECIAL_REGEX = "\\.\\/";
    private static final String NUMBERS_REGEX = "\\p{N}";
    private static final String MSG_20004 = "validation.data.constraints.msg.20004";
    private static final String MSG_20013 = "validation.data.constraints.msg.20013";
    private static final String MSG_20003 = "validation.data.constraints.msg.20003";

    private boolean arabic;
    private boolean allowEmpty;
    private boolean allowNumbers;
    private boolean allowSpecialChars;
    private int min;
    private int max;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final OnlyCharacters constraintAnnotation) {
        arabic = constraintAnnotation.arabic();
        allowEmpty = constraintAnnotation.allowEmpty();
        allowNumbers = constraintAnnotation.allowNumbers();
        allowSpecialChars = constraintAnnotation.allowSpecialChars();
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        String regex = REGEX_TPL.replace("${CHARSET}", arabic ? ARABIC_LETTERS_REGEX : LATIN_LETTERS_REGEX)
                .replace("${NUMBERS}", allowNumbers ? NUMBERS_REGEX : "")
                .replace("${SPECIALS}", allowSpecialChars ? SPECIAL_REGEX : "");
        if (value == null || StringUtils.isBlank(value.toString())) {
            return this.allowEmpty;
        } else if (value.toString().length() < min || value.toString().length() > max) {
            // build new violation message and add it
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MSG_20004).addConstraintViolation();
            return false;
        } else if (!value.toString().matches(regex)) {
            // build new violation message and add it
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(arabic ? MSG_20013 : MSG_20003).addConstraintViolation();
            return false;
        }
        return true;
    }

}

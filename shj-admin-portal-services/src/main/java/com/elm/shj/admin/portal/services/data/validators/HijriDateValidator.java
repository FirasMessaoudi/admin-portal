/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DateTimeException;
import java.time.chrono.HijrahDate;
import java.time.temporal.ChronoField;

/**
 * Validator for {@link HijriDate} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class HijriDateValidator implements ConstraintValidator<HijriDate, Object> {

    private static final String MSG_126 = "validation.data.constraints.msg.126";

    private HijrahDate min;
    private HijrahDate max;

    /**
     * {@inheritDoc}
     */
    @SneakyThrows
    @Override
    public void initialize(final HijriDate constraintAnnotation) {
        HijrahDate todayHijri = HijrahDate.now();
        min = HijrahDate.now().with(ChronoField.YEAR, todayHijri.get(ChronoField.YEAR) + constraintAnnotation.minOffset());
        max = HijrahDate.now().with(ChronoField.YEAR, todayHijri.get(ChronoField.YEAR) + constraintAnnotation.maxOffset());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        Long dateValue = (Long) value;
        HijrahDate hijriValue = null;
        if (StringUtils.length("" + dateValue) == 8) {
            int year = Integer.parseInt(dateValue.toString().substring(0, 4));
            int month = Integer.parseInt(dateValue.toString().substring(4, 6));
            int day = Integer.parseInt(dateValue.toString().substring(6, 8));
            try {
                hijriValue = HijrahDate.now()
                        .with(ChronoField.YEAR, year)
                        .with(ChronoField.MONTH_OF_YEAR, month)
                        .with(ChronoField.DAY_OF_MONTH, day);
            } catch (DateTimeException dte) {
                // ignore
            }
        }

        if (dateValue == null || hijriValue == null) {
            // return default message;
            return false;
        } else if (hijriValue.isBefore(min) || hijriValue.isAfter(max)) {
            // build new violation message and add it
            context.buildConstraintViolationWithTemplate(MSG_126).addConstraintViolation();
            return false;
        }

        return true;
    }

}

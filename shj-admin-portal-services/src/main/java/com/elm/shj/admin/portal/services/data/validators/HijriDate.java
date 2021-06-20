/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validation annotation to validate that a value is a valid hijri date
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = HijriDateValidator.class)
@Documented
public @interface HijriDate {

    String message() default "validation.data.constraints.msg.20005";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return The offset to be used for min date compared to today's date
     */
    int minOffset() default -140;

    /**
     * @return The offset to be used for max date compared to today's date
     */
    int maxOffset() default -11;

    /**
     * @return if to allow empty values
     */
    boolean allowEmpty() default true;

}

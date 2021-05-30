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
 * Validation annotation to validate that a value is a valid gregorian date
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = GregorianDateValidator.class)
@Documented
public @interface GregorianDate {

    String message() default "validation.data.constraints.msg.123";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return The offset to be used for min date compared to today's date
     */
    int minOffset() default -120;

    /**
     * @return The offset to be used for max date compared to today's date
     */
    int maxOffset() default -10;

    /**
     * @return whether to accept null values or not
     */
    boolean allowNull() default false;

}

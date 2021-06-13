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
 * Validation annotation to validate that a value is a valid ritual type code in the database
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = RitualTypeCodeValidator.class)
@Documented
public @interface RitualTypeCode {

    String message() default "validation.data.constraints.msg.20006";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

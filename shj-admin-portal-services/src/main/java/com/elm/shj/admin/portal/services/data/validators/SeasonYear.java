/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validation annotation to validate the season year range
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = SeasonYearValidator.class)
@Documented
public @interface SeasonYear {

    String message() default "validation.data.constraints.msg.20011";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

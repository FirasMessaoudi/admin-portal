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
 * Validation annotation to validate that company ref code  corresponds to a  company record in the database
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = WithServiceGroupValidator.class)
@Documented
public @interface WithServiceGroup {

    String message() default "validation.data.constraints.msg.30020";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

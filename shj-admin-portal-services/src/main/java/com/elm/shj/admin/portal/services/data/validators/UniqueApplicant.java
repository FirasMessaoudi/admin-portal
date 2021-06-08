/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validation annotation to validate that applicant info corresponds to a unique applicant record in the database
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueApplicantValidator.class)
@Documented
public @interface UniqueApplicant {

    String message() default "validation.data.constraints.msg.30001";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

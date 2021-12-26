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
 * Validation annotation to validate that company staff info corresponds to an existing staff in the database and his title  is group leader
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = WithGroupLeaderCompanyStaffValidator.class)
@Documented
public @interface WithGroupLeaderCompanyStaff {

    String message() default "validation.data.constraints.msg.30008";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

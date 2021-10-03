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
 * Validation annotation to validate that ritual package corresponds to an existing ritual package in the database
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = WithRitualPackageValidator.class)
@Documented
public @interface WithRitualPackage {

    String message() default "validation.data.constraints.msg.30004";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

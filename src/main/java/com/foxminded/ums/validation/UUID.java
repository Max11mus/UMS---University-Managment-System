package com.foxminded.ums.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UUIDValidator.class)
@Documented
public @interface UUID {
    String message() default "${validatedValue}  isn't correct " + " UUID. See RFC 4122 - 4.1. Format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

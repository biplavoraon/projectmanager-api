package com.biplav.projectmanager.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Constraint(validatedBy = {RoleValidator.class})
@Retention(RUNTIME)
@Documented
public @interface ValidRole {
    String message() default "Role is invalid";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

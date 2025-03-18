package pl.edu.pja.dziabor.tpo10.Validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidName {
    String message() default "Invalid name"; // Fallback
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
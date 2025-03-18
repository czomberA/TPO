package pl.edu.pja.dziabor.tpo10.Validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUrlValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsUnique {
    String message() default "Already exists"; // Fallback
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
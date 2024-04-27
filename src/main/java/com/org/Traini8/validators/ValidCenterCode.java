package com.org.Traini8.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * This annotation is used to declare a custom constraint for validating center
 * codes. It specifies that the annotated element must conform to a specific
 * format as defined by the CenterCodeValidator class.
 */
@Documented
// Specifies the validator class that implements the constraint logic.
@Constraint(validatedBy = CenterCodeValidator.class)
// Targets indicate where this annotation can be applied (methods, fields, etc.).
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE })
// Specifies that this annotation will be available at runtime and can be accessed via reflection.
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCenterCode {
    // Allows specification of validation groups, to categorize validation
    // constraints.
    Class<?>[] groups() default {};

    // Default error message that is used when the constraint is violated.
    String message() default "Center code must be exactly 12 alphanumeric characters";

    // Can be used by clients of the Bean Validation API to assign custom payload
    // objects to a constraint.
    Class<? extends Payload>[] payload() default {};
}

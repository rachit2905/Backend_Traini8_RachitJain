package com.org.Traini8.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * This annotation defines a custom validation constraint for contact phone
 * numbers. It specifies that the annotated element must conform to the
 * specified format, validated by the ContactPhoneValidator class.
 */
@Documented
// Marks the annotation to be documented by tools like Javadoc.
@Constraint(validatedBy = ContactPhoneValidator.class)
// Specifies the validator class that implements the constraint logic for this annotation.
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE })
// Declares the kinds of program elements to which this annotation can be applied.
@Retention(RetentionPolicy.RUNTIME)
// Indicates that this annotation will be available at runtime through reflection.
public @interface ValidContactPhone {
    // Defines the groups with which the constraint declaration is associated.
    Class<?>[] groups() default {};

    // Specifies the default message that will appear when the validation fails.
    String message() default "Contact phone must be between 10 and 15 digits and may start with a +";

    // Assigns a payload to the constraint declaration, often used to carry metadata
    // information consumed by a validation client.
    Class<? extends Payload>[] payload() default {};
}

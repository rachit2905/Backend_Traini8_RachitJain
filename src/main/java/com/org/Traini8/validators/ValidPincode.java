package com.org.Traini8.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * This annotation defines a custom validation constraint for pincodes. It
 * specifies that the annotated element must conform to the specified pincode
 * format, validated by the PincodeValidator class. The pincode format is
 * expected to be 5 to 6 digits.
 */
@Documented
// Marks the annotation to be documented by tools like Javadoc.
@Constraint(validatedBy = PincodeValidator.class)
// Specifies the validator class that implements the constraint logic for this annotation.
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE })
// Declares the kinds of program elements to which this annotation can be applied.
@Retention(RetentionPolicy.RUNTIME)
// Indicates that this annotation will be available at runtime through reflection.
public @interface ValidPincode {
    // Defines the groups with which the constraint declaration is associated.
    Class<?>[] groups() default {};

    // Specifies the default message that will appear when the validation fails.
    String message() default "Pincode must be 5 to 6 digits";

    // Assigns a payload to the constraint declaration, often used to carry metadata
    // information consumed by a validation client.
    Class<? extends Payload>[] payload() default {};
}

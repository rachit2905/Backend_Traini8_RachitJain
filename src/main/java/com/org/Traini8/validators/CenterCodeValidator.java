package com.org.Traini8.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * This class implements ConstraintValidator to define custom validation logic
 * for a training center's code. It checks if the center code meets a specific
 * pattern.
 */
public class CenterCodeValidator implements ConstraintValidator<ValidCenterCode, String> {
    // Regex pattern for validating the center code. It must be exactly 12
    // alphanumeric characters.
    private static final String CENTER_CODE_PATTERN = "^[A-Za-z0-9]{12}$";

    /**
     * Initializes the validator. This method is used to perform any setup necessary
     * before the validation logic is applied. Here, no setup is required.
     *
     * @param constraint Annotation instance for a given constraint declaration.
     */
    @Override
    public void initialize(final ValidCenterCode constraint) {
        // You can add initialization code here if needed
    }

    /**
     * Implements the validation logic to check if a given string (center code) is
     * valid. The center code is considered valid if it is not null and matches the
     * predefined pattern.
     *
     * @param value   The center code to validate.
     * @param context Context in which the constraint is evaluated.
     * @return true if the center code is valid, false otherwise.
     */
    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (value == null) {
            return false; // returns false if the value is null
        }
        return value.matches(CENTER_CODE_PATTERN); // checks if the value matches the regex pattern
    }
}

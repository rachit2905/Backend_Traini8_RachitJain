package com.org.Traini8.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * This class implements ConstraintValidator to provide custom validation logic
 * for a contact phone number. It verifies if the phone number meets a specific
 * pattern.
 */
public class ContactPhoneValidator implements ConstraintValidator<ValidContactPhone, String> {
    // Regex pattern for validating the phone number. It allows an optional '+'
    // followed by 10 to 15 digits.
    private static final String PHONE_PATTERN = "^\\+?[0-9]{10,15}$";

    /**
     * Initializes the validator. This method can be used for any setup required
     * before the validation logic is executed. Typically used for initializing
     * resources or configuration settings.
     *
     * @param constraint Annotation instance for a given constraint declaration.
     */
    @Override
    public void initialize(final ValidContactPhone constraint) {
        // Optional initialization can be done here
    }

    /**
     * Implements the validation logic to check if a given string (phone number) is
     * valid. The phone number is considered valid if it is not null and matches the
     * predefined regex pattern.
     *
     * @param value   The phone number to validate.
     * @param context Context in which the constraint is evaluated.
     * @return true if the phone number is valid, false otherwise.
     */
    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Returns false if the value is null, enforcing that the phone number must be
                          // provided.
        }
        return value.matches(PHONE_PATTERN); // Checks if the value matches the regex pattern for phone numbers.
    }
}

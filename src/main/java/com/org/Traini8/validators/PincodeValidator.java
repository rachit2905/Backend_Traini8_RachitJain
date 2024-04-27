package com.org.Traini8.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * This class implements the ConstraintValidator interface to provide custom
 * validation logic for postal codes (pincodes). It checks if the pincode
 * conforms to a specific format.
 */
public class PincodeValidator implements ConstraintValidator<ValidPincode, String> {
    // Regex pattern for validating the pincode. It must consist of 5 or 6 digits.
    private static final String PINCODE_PATTERN = "^[0-9]{5,6}$";

    /**
     * Implements the validation logic to check if a given string (pincode) is
     * valid. The pincode is considered valid if it is not null and matches the
     * predefined regex pattern. This method also logs the validation process, which
     * is helpful for debugging.
     *
     * @param value   The pincode to validate.
     * @param context Context in which the constraint is evaluated.
     * @return true if the pincode is valid, false otherwise.
     */
    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return value != null && value.matches(PINCODE_PATTERN);
    }
}

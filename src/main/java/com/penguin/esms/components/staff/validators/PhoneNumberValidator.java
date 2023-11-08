package com.penguin.esms.components.staff.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberFormat, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Null values are considered valid
        }

        // Check if the string length is between 8 and 14 characters and starts with '0'
        return value.length() >= 8 && value.length() <= 14 && value.startsWith("0");
    }
}

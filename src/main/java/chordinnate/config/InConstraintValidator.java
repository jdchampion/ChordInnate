package chordinnate.config;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class InConstraintValidator implements ConstraintValidator<In, String> {

    private String[] values;
    private boolean allowNull;

    public final void initialize(final In annotation) {
        values = annotation.values();
        allowNull = annotation.allowNull();
    }

    public final boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (value == null) {
            return allowNull;
        }

        return Arrays.asList(values).contains(value);
    }

}
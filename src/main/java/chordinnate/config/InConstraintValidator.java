package chordinnate.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class InConstraintValidator implements ConstraintValidator<In, String> {

    private String[] values;

    public final void initialize(final In annotation) {
        values = annotation.values();
    }

    public final boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return Arrays.asList(values).contains(value);
    }

}
package chordinnate.entity.validation;

import chordinnate.entity.ScaleType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ScaleTypeSizeValidator implements ConstraintValidator<ValidateSize, ScaleType> {

    @Override
    public boolean isValid(ScaleType scaleType, ConstraintValidatorContext context) {

        if (scaleType.getIntervals() == null) {
            return false; // should have already failed Phase1Validation before getting here
        }

        return scaleType.getSize() != null && scaleType.getSize().equals(scaleType.getIntervals().length);
    }

}
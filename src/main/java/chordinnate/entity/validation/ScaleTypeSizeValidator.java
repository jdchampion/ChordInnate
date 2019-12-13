package chordinnate.entity.validation;

import chordinnate.annotation.ValidateSize;
import chordinnate.entity.ScaleType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ScaleTypeSizeValidator implements ConstraintValidator<ValidateSize, ScaleType> {

    @Override
    public boolean isValid(ScaleType scaleType, ConstraintValidatorContext context) {

        if (scaleType.getIntervals() == null) {
            return true; // not actually valid -- just suppressing extra constraint violation messages
        }

        return scaleType.getSize() != null && scaleType.getSize().equals(scaleType.getIntervals().length);
    }

}
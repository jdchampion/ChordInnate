package chordinnate.entity.validation;

import chordinnate.entity.ChordType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChordTypeSizeValidator implements ConstraintValidator<ValidateSize, ChordType> {

    @Override
    public boolean isValid(ChordType chordType, ConstraintValidatorContext context) {

        if (chordType.getIntervals() == null) {
            return false; // should have already failed Phase1Validation before getting here
        }

        return chordType.getSize() != null && chordType.getSize().equals(chordType.getIntervals().length);
    }

}
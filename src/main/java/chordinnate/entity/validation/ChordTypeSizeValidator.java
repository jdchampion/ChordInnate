package chordinnate.entity.validation;

import chordinnate.annotation.ValidateSize;
import chordinnate.entity.ChordType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChordTypeSizeValidator implements ConstraintValidator<ValidateSize, ChordType> {

    @Override
    public boolean isValid(ChordType chordType, ConstraintValidatorContext context) {

        if (chordType.getIntervals() == null) {
            return true; // not actually valid -- just suppressing extra constraint violation messages
        }

        return chordType.getSize() != null && chordType.getSize().equals(chordType.getIntervals().length);
    }

}
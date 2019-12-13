package chordinnate.entity.validation;

import chordinnate.annotation.ValidateIntervals;
import chordinnate.entity.ChordType;
import chordinnate.model.musictheory.pitch.interval.Interval;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChordTypeIntervalValidator implements ConstraintValidator<ValidateIntervals, ChordType> {

    @Override
    public void initialize(ValidateIntervals constraintAnnotation) {

    }

    @Override
    public boolean isValid(ChordType chordType, ConstraintValidatorContext context) {

        if (chordType == null || chordType.getIntervals() == null || chordType.getIntervals().length < 2) {
            return true; // not actually valid -- just suppressing extra constraint violation messages
        }

        Interval[] intervals = chordType.getIntervals();

        int comparison = intervals[0].compareTo(intervals[1]);
        for (int i = 2; i < intervals.length + 1; i++) {
            int currentComparison = intervals[i - 2].compareTo(intervals[i - 1]);
            if (currentComparison == 0 || currentComparison != comparison) {
                return false;
            }
            comparison = currentComparison;
        }

        return true;
    }
}

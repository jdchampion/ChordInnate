package chordinnate.entity.validation;

import chordinnate.annotation.ValidateIntervals;
import chordinnate.entity.ScaleType;
import chordinnate.model.musictheory.pitch.interval.Interval;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ScaleTypeIntervalValidator implements ConstraintValidator<ValidateIntervals, ScaleType> {

    @Override
    public void initialize(ValidateIntervals constraintAnnotation) {

    }

    @Override
    public boolean isValid(ScaleType scaleType, ConstraintValidatorContext context) {

        if (scaleType == null || scaleType.getIntervals() == null || scaleType.getIntervals().length < 2) {
            return true; // not actually valid -- just suppressing extra constraint violation messages
        }

        Interval[] intervals = scaleType.getIntervals();

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

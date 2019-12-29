package chordinnate.entity.validation;

import chordinnate.annotation.ValidateIntervals;
import chordinnate.model.musictheory.pitch.interval.Interval;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntervalValidator implements ConstraintValidator<ValidateIntervals, Interval[]> {

    @Override
    public void initialize(ValidateIntervals constraintAnnotation) {

    }

    @Override
    public boolean isValid(Interval[] intervals, ConstraintValidatorContext context) {

        if (intervals == null || intervals.length < 2) {
            return false; // should have already failed Phase1Validation before getting here
        }

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

package chordinnate.entity.validation;

import chordinnate.annotation.ValidateIntervalContour;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.set.IntervalDirection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IntervalContourValidator implements ConstraintValidator<ValidateIntervalContour, Interval[]> {

    private Set<Integer> directions;

    @Override
    public void initialize(ValidateIntervalContour constraintAnnotation) {
        this.directions = Arrays.stream(constraintAnnotation.directions())
                .map(IntervalDirection::getCompareTo)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(Interval[] intervals, ConstraintValidatorContext context) {

        if (intervals == null || intervals.length < 2) {
            return false; // should have already failed Phase1Validation before getting here
        }

        final int firstComparison = intervals[0].compareTo(intervals[1]);

        Predicate<Integer> directionPredicate;
        int start = 2;

        if (directions.contains(0)) {
            // stationary allowed

            if (directions.contains(1) && directions.contains(-1)) {
                // must be going strictly up or strictly down

                directionPredicate = integer -> integer == firstComparison;

                if (firstComparison == 0) {
                    while (++start < intervals.length + 2) {
                        int comparison = intervals[start - 3].compareTo(intervals[start - 2]);
                        if (comparison != 0) {
                            directionPredicate = integer -> integer == 0 || integer == comparison;
                            break;
                        }
                    }
                }

            } else if (directions.contains(1)) {
                // must be going strictly up
                directionPredicate = integer -> integer >= 0;
            } else {
                // must be going strictly down
                directionPredicate = integer -> integer <= 0;
            }
        } else {
            // stationary NOT allowed
            if (firstComparison == 0) {
                return false; // not valid because first two intervals are stationary
            }

            if (directions.contains(1) && directions.contains(-1)) {
                // must be going strictly up or strictly down at each point
                directionPredicate = integer -> integer != 0 && integer == firstComparison;
            } else if (directions.contains(1)) {
                // must be going strictly up at each point
                directionPredicate = integer -> integer > 0;
            } else {
                // must be going strictly down at each point
                directionPredicate = integer -> integer < 0;
            }
        }

        for (int i = start; i < intervals.length + 1; i++) {
            int currentComparison = intervals[i - 2].compareTo(intervals[i - 1]);
            if (!directionPredicate.test(currentComparison)) {
                return false;
            }
        }

        return true;
    }
}

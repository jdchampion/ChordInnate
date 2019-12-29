package chordinnate.annotation;

import chordinnate.entity.validation.IntervalContourValidator;
import chordinnate.model.musictheory.pitch.interval.set.IntervalDirection;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {IntervalContourValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateIntervalContour {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    IntervalDirection[] directions() default {IntervalDirection.ASCENDING, IntervalDirection.DESCENDING, IntervalDirection.STATIONARY};
}
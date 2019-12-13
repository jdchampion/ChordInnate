package chordinnate.annotation;

import chordinnate.entity.validation.ChordTypeIntervalValidator;
import chordinnate.entity.validation.ScaleTypeIntervalValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {ChordTypeIntervalValidator.class, ScaleTypeIntervalValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateIntervals {
    String message() default "Intervals must be increasing or decreasing at each step";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
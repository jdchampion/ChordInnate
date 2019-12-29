package chordinnate.annotation;

import chordinnate.entity.validation.IntervalValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {IntervalValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateIntervals {
    String message() default "{validation.constraints.intervals}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
package chordinnate.annotation;

import chordinnate.entity.validation.ChordTypeSizeValidator;
import chordinnate.entity.validation.ScaleTypeSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {ChordTypeSizeValidator.class, ScaleTypeSizeValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateSize {
    String message() default "{validation.constraints.sizeMatchIntervals}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
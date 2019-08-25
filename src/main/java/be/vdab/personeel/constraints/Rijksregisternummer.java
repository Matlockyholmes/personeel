package be.vdab.personeel.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RijksregisternummerValidator.class)
public @interface Rijksregisternummer {
    String message() default "{be.vdab.groenetenen.constraints.Rijksregisternummer.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

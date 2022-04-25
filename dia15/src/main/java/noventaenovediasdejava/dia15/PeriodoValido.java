package noventaenovediasdejava.dia15;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PeriodoValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PeriodoValido {

    String message() default "período está inválido";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}

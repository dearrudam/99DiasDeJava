package noventaenovediasdejava.dia15;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeriodoValidator implements ConstraintValidator<PeriodoValido, Periodo> {
    @Override
    public boolean isValid(Periodo value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (value.inicio() == null) {
            return false;
        }
        if (value.fim() == null) {
            return false;
        }
        return value.inicio().isBefore(value.fim())
                || value.inicio().isEqual(value.fim());
    }
}

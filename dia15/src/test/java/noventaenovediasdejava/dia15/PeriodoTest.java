package noventaenovediasdejava.dia15;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;

public class PeriodoTest {

    @Test
    @DisplayName("dado um periodo válido, o validator não deve encontrar violações")
    public void test01() {

        var violacoes = getValidator()
                .validate(Periodo.of(LocalDate.of(2022, 1, 1), LocalDate.now()));

        Assertions.assertTrue(violacoes.isEmpty());

    }

    @Test
    @DisplayName("dado um periodo inválido, o validator deve encontrar uma violação")
    public void test02() {

        var violacoes = getValidator()
                .validate(Periodo.of(LocalDate.now(), LocalDate.of(2022, 1, 1)));

        Assertions.assertFalse(violacoes.isEmpty());

    }

    public Validator getValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

}

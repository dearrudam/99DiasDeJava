package noventaenovediasdejava.dia04;

import java.util.stream.Stream;
import noventaenovediasdejava.dia04.NumeroRacional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(OrderAnnotation.class)
class NumeroRacionalTest {
    @Test
    @Order(0)
    void testarInstanciacao() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new NumeroRacional(1, 0),
            "não deve aceitar denominador igual a 0"
        );
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new NumeroRacional(null, 1),
            "não deve aceitar numerador nulo"
        );
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new NumeroRacional(1, null),
            "não deve aceitar denominador nulo"
        );
    }

    @Test
    @Order(1)
    void testarToString() {
        var numeroRacional = new NumeroRacional(2, 3);
        Assertions.assertEquals("2/3", numeroRacional.toString());
    }

    @ParameterizedTest(name = "[{index}] a forma irredutível de {0} deve ser igual a {1}")
    @MethodSource("testarFormaIrredutivelArgs")
    void testarFormaIrredutivel(
        final NumeroRacional numeroRacionalBase,
        final NumeroRacional numeroRacionalNaFormaIrredutivelEsperado
    ) {
        final NumeroRacional numeroRacionalNaFormaIrredutivel = numeroRacionalBase
            .formaIrredutivel();
        Assertions.assertNotNull(
            numeroRacionalNaFormaIrredutivel,
            "não deve ser retornado valor/referência nulo"
        );
        Assertions.assertEquals(
            numeroRacionalNaFormaIrredutivelEsperado,
            numeroRacionalNaFormaIrredutivel
        );
    }

    static Stream<Arguments> testarFormaIrredutivelArgs() {
        return Stream.of(
            Arguments.arguments(
                new NumeroRacional(12, 4),
                new NumeroRacional(3, 1)
            ),
            Arguments.arguments(
                new NumeroRacional(130, 78),
                new NumeroRacional(5, 3)
            ),
            Arguments.arguments(
                new NumeroRacional(-130, 78),
                new NumeroRacional(-5, 3)
            ),
            Arguments.arguments(
                new NumeroRacional(130, -78),
                new NumeroRacional(5, -3)
            )
        );
    }

    @ParameterizedTest(name = "[{index}] {0} + {1} = {2}")
    @MethodSource("testarSomarArgs")
    void testarSomar(
        final NumeroRacional numeroRacional01,
        final NumeroRacional numeroRacional02,
        final NumeroRacional resultadoEsperado
    ) {
        final NumeroRacional resultadoAtual =
            numeroRacional01.somar(numeroRacional02);
        Assertions.
            assertNotNull(resultadoAtual,
                          "não deve retornar valor/referência nula");
        Assertions.
            assertEquals(
                resultadoEsperado,
                resultadoAtual
            );
    }

    static Stream<Arguments> testarSomarArgs() {
        return Stream.of(
            Arguments.arguments(
                new NumeroRacional(1, 4),
                new NumeroRacional(1, 4),
                new NumeroRacional(1, 2)
            ),
            Arguments.arguments(
                new NumeroRacional(2, 3),
                new NumeroRacional(3, 4),
                new NumeroRacional(17, 12)
            ),
            Arguments.arguments(
                new NumeroRacional(5, 3),
                new NumeroRacional(4, 12),
                new NumeroRacional(2, 1)
            )
        );
    }
}

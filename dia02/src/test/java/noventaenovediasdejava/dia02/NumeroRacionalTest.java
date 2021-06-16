package noventaenovediasdejava.dia02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

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
}

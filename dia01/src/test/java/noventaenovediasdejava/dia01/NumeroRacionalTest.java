package noventaenovediasdejava.dia01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumeroRacionalTest {
    @Test
    void test() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new NumeroRacional(1, 0),
            "não deve aceitar denominador igual á 0"
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
}

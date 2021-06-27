package noventaenovediasdejava.dia11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MovimentoJokenpoTest {
    @Test
    void testeDeEstrutura() {
        final var values = MovimentoJokenpo.values();
        Assertions.assertEquals(
            3,
            values.length,
            "Só deve ter 3 tipos de movimentos Jokenpô"
        );
        Assertions.assertEquals("PEDRA", values[0].name());
        Assertions.assertEquals("PAPEL", values[1].name());
        Assertions.assertEquals("TESOURA", values[2].name());
    }

    @Test
    void testarComportamento() {
        Assertions.assertEquals(true,
                                MovimentoJokenpo.PAPEL
                                    .ganhaDe(MovimentoJokenpo.PEDRA));
        Assertions.assertEquals(true,
                                MovimentoJokenpo.PEDRA
                                    .ganhaDe(MovimentoJokenpo.TESOURA));
        Assertions.assertEquals(true,
                                MovimentoJokenpo.TESOURA
                                    .ganhaDe(MovimentoJokenpo.PAPEL));
    }
}
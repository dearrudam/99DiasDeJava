package noventaenovediasdejava.dia08;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JogoJokenpoTest {
    @Test
    void naoDevePermitirInstanciacao() {
        Assertions
            .assertThrows(
                IllegalArgumentException.class,
                () -> new JogoJokenpo(null, null),
                "não deve ser permitido a instanciação do jogo sem jogadores"
            );
        Assertions
            .assertThrows(
                IllegalArgumentException.class,
                () -> new JogoJokenpo(new JogadorJokenpo() {
                }, null),
                "não deve ser permitido a instanciação do jogo só com o jogador 01"
            );
        Assertions
            .assertThrows(
                IllegalArgumentException.class,
                () -> new JogoJokenpo(
                    null,
                    new JogadorJokenpo() {
                    }
                ),
                "não deve ser permitido a instanciação do jogo só com o jogador 02"
            );
    }
}

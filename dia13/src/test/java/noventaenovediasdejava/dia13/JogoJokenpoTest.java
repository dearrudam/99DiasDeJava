package noventaenovediasdejava.dia13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JogoJokenpoTest {
    @Test
    void naoDevePermitirInstanciacao() {
        final JogadorJokenpo jogador = () -> null;
        Assertions
            .assertThrows(
                IllegalArgumentException.class,
                () -> new JogoJokenpo(null, null),
                "não deve ser permitido a instanciação do jogo sem jogadores"
            );
        Assertions
            .assertThrows(
                IllegalArgumentException.class,
                () -> new JogoJokenpo(jogador, null),
                "não deve ser permitido a instanciação do jogo só com o jogador 01"
            );
        Assertions
            .assertThrows(
                IllegalArgumentException.class,
                () -> new JogoJokenpo(
                    null, jogador
                ),
                "não deve ser permitido a instanciação do jogo só com o jogador 02"
            );
    }

    @Test
    void testeExecutarJogada() {
        JogadorJokenpo pietro = () -> MovimentoJokenpo.PEDRA;
        JogadorJokenpo max = () -> MovimentoJokenpo.TESOURA;
        JogoJokenpo jogo = Assertions
            .assertDoesNotThrow(
                () -> new JogoJokenpo(pietro, max),
                "deveria permitir a criação do Jogo"
            );
        JogadaJokenpo jogada = jogo.executarJogada();
        Assertions.assertEquals(
            false,
            jogada.acabouEmpatada()
        );
        Assertions.assertEquals(pietro, jogada.vencedor());
        Assertions.assertEquals(max, jogada.perdedor());
    }
}

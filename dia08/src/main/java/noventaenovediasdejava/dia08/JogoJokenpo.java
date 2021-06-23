package noventaenovediasdejava.dia08;

public class JogoJokenpo {
    public JogoJokenpo(
        final JogadorJokenpo jogador01,
        final JogadorJokenpo jogador02
    ) {
        if (jogador01 == null && jogador02 == null) {
            throw new IllegalArgumentException("jogadores não informados");
        }
        if (jogador01 != null && jogador02 == null) {
            throw new IllegalArgumentException("jogador 02 não informado");
        }
        if (jogador01 == null && jogador02 != null) {
            throw new IllegalArgumentException("jogador 01 não informado");
        }
    }
}

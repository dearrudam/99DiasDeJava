package noventaenovediasdejava.dia10;

public class JogoJokenpo {
    private final JogadorJokenpo jogador01;
    private final JogadorJokenpo jogador02;

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
        this.jogador01 = jogador01;
        this.jogador02 = jogador02;
    }

    public JogadaJokenpo executarJogada() {
        MovimentoJokenpo movimentoJokenpo01 = this.jogador01.jogar();
        MovimentoJokenpo movimentoJokenpo02 = this.jogador02.jogar();
        if (movimentoJokenpo01.ganhaDe(movimentoJokenpo02)) {
            return new JogadaJokenpo(false, jogador01, jogador02);
        }
        if (movimentoJokenpo02.ganhaDe(movimentoJokenpo01)) {
            return new JogadaJokenpo(false, jogador02, jogador01);
        }
        return new JogadaJokenpo(true, null, null);
    }
}

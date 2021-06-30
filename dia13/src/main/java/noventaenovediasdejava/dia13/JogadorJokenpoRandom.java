package noventaenovediasdejava.dia13;

import java.util.Random;

public class JogadorJokenpoRandom implements JogadorJokenpo {
    @Override
    public MovimentoJokenpo jogar() {
        final var movimentosDisponiveis = MovimentoJokenpo.values();
        final int index = new Random().nextInt(movimentosDisponiveis.length);
        return movimentosDisponiveis[index];
    }
}

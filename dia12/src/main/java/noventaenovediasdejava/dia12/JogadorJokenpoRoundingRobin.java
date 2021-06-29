package noventaenovediasdejava.dia12;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class JogadorJokenpoRoundingRobin
    implements JogadorJokenpo {
    private final LinkedList<MovimentoJokenpo> movimentos;

    public JogadorJokenpoRoundingRobin() {
        this.movimentos = Arrays
            .stream(MovimentoJokenpo.values())
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public MovimentoJokenpo jogar() {
        synchronized (this){
            final var movimento = this.movimentos.poll();
            this.movimentos.add(movimento);
            return movimento;
        }
    }
}

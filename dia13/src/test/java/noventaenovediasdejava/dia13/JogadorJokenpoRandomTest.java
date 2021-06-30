package noventaenovediasdejava.dia13;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JogadorJokenpoRandomTest {
    @Test
    void testarJogar() {

        JogadorJokenpo jogador = new JogadorJokenpoRandom();

        final var movimentosExecutados =
            IntStream.range(0, 10)
                .boxed()
                .map(tentativa -> {
                    final var movimento = jogador.jogar();
                    System.out.println("%s = %s".formatted(tentativa, movimento));
                    return movimento;
                })
                .collect(Collectors.toList());

        Assertions.assertEquals(
            10,
            movimentosExecutados
                .stream()
                .filter(Objects::nonNull)
                .count(),
            "total de movimentos executados fora do esperado"
        );
    }
}

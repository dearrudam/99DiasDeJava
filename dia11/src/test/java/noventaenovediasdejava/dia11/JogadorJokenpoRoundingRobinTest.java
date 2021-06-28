package noventaenovediasdejava.dia11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JogadorJokenpoRoundingRobinTest {
    @Test
    void testeJogar() {
        JogadorJokenpo jogador = new JogadorJokenpoRoundingRobin();
        final var movimentosJogados =
            IntStream.range(0, 600) // criando um range de iteração de 0 à 599
                .boxed()
                .parallel()
                .map(i -> {
                    try{
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    var movimento = jogador.jogar();
                    try{
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return Map.of(
                        String.valueOf(movimento),
                        new AtomicInteger(1));
                })
                .collect(Collectors.toList())
                .stream()
                .reduce(new HashMap<>(), (ac, item) -> {
                    item.entrySet()
                        .forEach(entry -> {
                            ac.computeIfAbsent(
                                entry.getKey(),
                                k -> new AtomicInteger(0)
                            )
                                .incrementAndGet();
                        });
                    return ac;
                });
        movimentosJogados
            .entrySet()
            .forEach(System.out::println);
        Assertions.assertEquals(
            Arrays
                .stream(MovimentoJokenpo.values())
                .map(String::valueOf)
                .sorted()
                .collect(Collectors.joining(",")),
            movimentosJogados
                .keySet()
                .stream()
                .map(String::valueOf)
                .sorted()
                .collect(Collectors.joining(",")),
            "tipos de movimentos fora do esperado"
        );
        Arrays.stream(MovimentoJokenpo.values())
            .forEach(movimento -> {
                Assertions.assertEquals(
                    200,
                    movimentosJogados.get(movimento.name()).get(),
                    "numero de jogadas com %s fora do esperado".formatted(movimento)
                );
            });
    }
}

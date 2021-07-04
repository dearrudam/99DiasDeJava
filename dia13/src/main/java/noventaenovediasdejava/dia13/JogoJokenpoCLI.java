package noventaenovediasdejava.dia13;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class JogoJokenpoCLI {

    public static void main(String[] args) {
        EstadoDoJogo estadoAtual = new Inicio();
        while (estadoAtual.podeExecutar()) {
            estadoAtual = estadoAtual.executar(System.in, System.out);
            if (estadoAtual instanceof Sair sair) {
                sair.finalizarJogo(System.out);
            }
        }
    }

    static interface EstadoDoJogo {
        EstadoDoJogo executar(InputStream input, PrintStream output);
        default boolean podeExecutar() {
            return true;
        }
    }

    static class Inicio implements EstadoDoJogo {
        @Override
        public EstadoDoJogo executar(
            final InputStream input, final PrintStream output
        ) {
            output.println("Bem-vindo ao Jogo Jokenpô!!!");
            return new CapturarComandoPlayer01();
        }
    }

    static class CapturarComandoPlayer01 implements EstadoDoJogo {
        @Override
        public EstadoDoJogo executar(
            final InputStream input, final PrintStream output
        ) {
            output.println("Comandos válidos: SAIR, PEDRA, PAPEL ou TESOURA");
            var comando = new Scanner(input).next().trim();
            if ("SAIR".equalsIgnoreCase(comando)) {
                return new Sair();
            }
            final var movimentoDoPlayer01 =
                Arrays.stream(MovimentoJokenpo.values())
                    .filter(m -> m.name().equalsIgnoreCase(comando))
                    .findFirst();
            if (movimentoDoPlayer01.isEmpty()) {
                return new ComandoInvalido(this);
            }
            return new CapturarComandoPlayer02(movimentoDoPlayer01.get());
        }
    }

    static class CapturarComandoPlayer02 implements EstadoDoJogo {
        private final MovimentoJokenpo movimentoPlayer01;

        public CapturarComandoPlayer02(final MovimentoJokenpo movimentoPlayer01) {
            this.movimentoPlayer01 = movimentoPlayer01;
        }

        @Override
        public EstadoDoJogo executar(
            final InputStream input, final PrintStream output
        ) {
            final AtomicReference<MovimentoJokenpo> movimentoCPU = new AtomicReference<>();
            JogadorJokenpo player01 = () -> this.movimentoPlayer01;
            JogadorJokenpo player02 = () -> {
                movimentoCPU.set(new JogadorJokenpoRandom().jogar());
                return movimentoCPU.get();
            };
            JogoJokenpo jogoJokenpo =
                new JogoJokenpo(player01, player02);
            final var jogada = jogoJokenpo.executarJogada();
            if (jogada.acabouEmpatada()) {
                output.println("Quase!!! Deu empate!!!");
            } else if (Objects.equals(player01, jogada.vencedor())) {
                output.println("Wow!!! Você ganhou!!!");
            } else {
                output.println("Oh no!!! Você perdeu !!!");
            }
            output.println("CPU jogou: %s\n".formatted(movimentoCPU.get()));
            return new CapturarComandoPlayer01();
        }
    }

    static class ComandoInvalido implements EstadoDoJogo {
        private final EstadoDoJogo estadoDoJogo;

        public ComandoInvalido(final EstadoDoJogo estadoDoJogo) {
            this.estadoDoJogo = estadoDoJogo;
        }

        @Override
        public EstadoDoJogo executar(
            final InputStream input, final PrintStream output
        ) {
            output.println("Ops!!! Comando inválido!!! Tente novamente!!!");
            return this.estadoDoJogo;
        }
    }

    static class Sair implements EstadoDoJogo {
        @Override
        public EstadoDoJogo executar(final InputStream input, final PrintStream output) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean podeExecutar() {
            return false;
        }

        public void finalizarJogo(final PrintStream output) {
            output.println("Fim de jogo!!!");
        }
    }
}

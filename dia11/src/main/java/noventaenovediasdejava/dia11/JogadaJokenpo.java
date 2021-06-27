package noventaenovediasdejava.dia11;

public record JogadaJokenpo(
    boolean acabouEmpatada,
    JogadorJokenpo vencedor,
    JogadorJokenpo perdedor
) {
}

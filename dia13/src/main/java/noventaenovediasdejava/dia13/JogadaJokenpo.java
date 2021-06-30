package noventaenovediasdejava.dia13;

public record JogadaJokenpo(
    boolean acabouEmpatada,
    JogadorJokenpo vencedor,
    JogadorJokenpo perdedor
) {
}

package noventaenovediasdejava.dia10;

public record JogadaJokenpo(
    boolean acabouEmpatada,
    JogadorJokenpo vencedor,
    JogadorJokenpo perdedor
) {
}

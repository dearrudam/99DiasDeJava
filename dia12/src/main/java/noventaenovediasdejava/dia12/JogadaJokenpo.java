package noventaenovediasdejava.dia12;

public record JogadaJokenpo(
    boolean acabouEmpatada,
    JogadorJokenpo vencedor,
    JogadorJokenpo perdedor
) {
}

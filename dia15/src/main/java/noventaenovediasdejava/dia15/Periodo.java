package noventaenovediasdejava.dia15;

import java.time.LocalDate;

@PeriodoValido
public record Periodo(LocalDate inicio, LocalDate fim) {
    public static Periodo of(LocalDate inicio, LocalDate fim) {
        return new Periodo(inicio, fim);
    }
}

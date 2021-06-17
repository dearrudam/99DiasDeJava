package noventaenovediasdejava.dia02;

public record NumeroRacional(Integer numerador, Integer denominador) {
    public NumeroRacional {
        if (numerador == null) {
            throw new IllegalArgumentException("numerador não pode ser nulo");
        }
        if (denominador == null) {
            throw new IllegalArgumentException("denominador não pode ser nulo");
        }
        if (Integer.valueOf(0).equals(denominador)) {
            throw new IllegalArgumentException("denominador não pode ser igual a 0");
        }
    }

    @Override
    public String toString() {
        return "%s/%s".formatted(this.numerador, this.denominador);
    }

}
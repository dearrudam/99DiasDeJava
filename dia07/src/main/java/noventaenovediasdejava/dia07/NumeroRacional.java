package noventaenovediasdejava.dia07;

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

    public NumeroRacional formaIrredutivel() {
        int numero = Math.abs(this.numerador);
        int maximoDivisorComum = Math.abs(this.denominador);
        int resto = 0;
        do {
            if (resto != 0) {
                numero = maximoDivisorComum;
                maximoDivisorComum = resto;
            }
            resto = numero % maximoDivisorComum;
        } while (resto != 0);
        return new NumeroRacional(
            this.numerador / maximoDivisorComum,
            this.denominador / maximoDivisorComum
        );
    }

    public NumeroRacional somar(final NumeroRacional numeroRacional) {
        //        N1   N2   N1*D2+N2*D1
        //        -- + -- = ----------- =
        //        D1   D2     D1 * D2
        int n1 = this.numerador;
        int d1 = this.denominador;
        int n2 = numeroRacional.numerador;
        int d2 = numeroRacional.denominador;
        final NumeroRacional resultado =
            new NumeroRacional(
                ((n1 * d2) + (n2 * d1)),
                (d1 * d2)
            );
        return resultado.formaIrredutivel();
    }

    public NumeroRacional subtrair(final NumeroRacional numeroRacional) {
        //        N1   N2   N1*D2-N2*D1
        //        -- - -- = ----------- =
        //        D1   D2     D1 * D2
        int n1 = this.numerador;
        int d1 = this.denominador;
        int n2 = numeroRacional.numerador;
        int d2 = numeroRacional.denominador;
        final NumeroRacional resultado =
            new NumeroRacional(
                (n1 * d2 - n2 * d1),
                (d1 * d2)
            );
        return resultado.formaIrredutivel();
    }

    public NumeroRacional multiplicar(
        final NumeroRacional numeroRacional
    ) {
        // (N1 * N2) / (D1 * D2)
        NumeroRacional resultado =
            new NumeroRacional(
                this.numerador * numeroRacional.numerador,
                this.denominador * numeroRacional.denominador
            );
        return resultado.formaIrredutivel();
    }

    public NumeroRacional dividir(final NumeroRacional numeroRacional) {
        // (N1*D2)/(N2*D1)
        NumeroRacional resultado =
            new NumeroRacional(
                this.numerador * numeroRacional.denominador,
                numeroRacional.numerador * this.denominador
            );
        return resultado.formaIrredutivel();
    }
}
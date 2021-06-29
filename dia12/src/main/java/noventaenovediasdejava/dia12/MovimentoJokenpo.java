package noventaenovediasdejava.dia12;

public enum MovimentoJokenpo {
    PEDRA {
        @Override
        public boolean ganhaDe(final MovimentoJokenpo movimento) {
            return TESOURA.equals(movimento);
        }
    },
    PAPEL {
        @Override
        public boolean ganhaDe(final MovimentoJokenpo movimento) {
            return switch (movimento) {
                case PEDRA -> true;
                default -> false;
            };
        }
    },
    TESOURA {
        @Override
        public boolean ganhaDe(final MovimentoJokenpo movimento) {
            return PAPEL.equals(movimento);
        }
    };

    public abstract boolean ganhaDe(final MovimentoJokenpo movimento);
}

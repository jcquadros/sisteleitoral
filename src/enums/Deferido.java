package enums;

public enum Deferido {
    DEFERIDO(1), INDEFERIDO(0);

    int situacao;

    private Deferido(int situacao) {
        if (situacao == 2 | situacao == 16) {
            this.situacao = 1;
        } else {
            this.situacao = 0;
        }
    }

    public static Deferido getDeferido(int deferido) {
        if (deferido == 2 | deferido == 16) {
            return Deferido.DEFERIDO;
        } else {
            return Deferido.INDEFERIDO;
        }
    }

}

package enums;

public enum Eleito {
    ELEITO(1), NAO_ELEITO(0);

    int eleito;

    private Eleito(int eleito) {
        if (eleito == 2 | eleito == 3) {
            this.eleito = 1;
        } else {
            this.eleito = 0;
        }
    }

    public static Eleito getEleito(int eleito) {
        if (eleito == 2 | eleito == 3) {
            return Eleito.ELEITO;
        } else {
            return Eleito.NAO_ELEITO;
        }
    }
}

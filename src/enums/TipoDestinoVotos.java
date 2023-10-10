package enums;

public enum TipoDestinoVotos {
    VALIDO("Válido"), VALIDO_LEGENDA("Válido (legenda)"), NULO("#NULO#"), NULO_TECNICO("Nulo técnico"),
    ANULADO("Anulado"), ANULADO_SUB_JUDICE("Anulado sub judice");

    private String tipoDestinoVotos;

    private TipoDestinoVotos(String tipoDestinoVotos) {
        this.tipoDestinoVotos = tipoDestinoVotos;
    }

    public String getTipoDestinoVotos() {
        return tipoDestinoVotos;
    }

}

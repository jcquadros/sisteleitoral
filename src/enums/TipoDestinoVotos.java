package enums;

/**
 * Esta enumeração representa os tipos de destino de votos, como Válido, Nulo, Anulado, etc.
 */
public enum TipoDestinoVotos {
    VALIDO("Válido"), VALIDO_LEGENDA("Válido (legenda)"), NULO("#NULO#"), NULO_TECNICO("Nulo técnico"),
    ANULADO("Anulado"), ANULADO_SUB_JUDICE("Anulado sub judice");

    private final String tipoDestinoVotos;

    /**
     * Constrói um valor da enumeração com uma descrição do tipo de destino de votos.
     *
     * @param tipoDestinoVotos A descrição do tipo de destino de votos.
     */
    private TipoDestinoVotos(String tipoDestinoVotos) {
        this.tipoDestinoVotos = tipoDestinoVotos;
    }

    /**
     * Obtém a descrição do tipo de destino de votos associada ao valor da enumeração.
     *
     * @return A descrição do tipo de destino de votos.
     */
    public String getTipoDestinoVotos() {
        return tipoDestinoVotos;
    }

    /**
     * Obtém um valor da enumeração com base na descrição do tipo de destino de votos.
     *
     * @param tipoDestinoVotos A descrição do tipo de destino de votos.
     * @return Um valor da enumeração correspondente à descrição do tipo de destino de votos.
     */
    public static TipoDestinoVotos getTipoDestinoVotos(String tipoDestinoVotos) {
        for (TipoDestinoVotos t : TipoDestinoVotos.values()) {
            if (t.getTipoDestinoVotos().equals(tipoDestinoVotos)) {
                return t;
            }
        }
        return null;
    }
}

package enums;

/**
 * Esta enumeração representa os gêneros feminino e masculino.
 */
public enum Genero {
    FEMININO(4), MASCULINO(2);

    private final int genero;

    /**
     * Constrói um valor da enumeração com um código de gênero associado.
     *
     * @param genero O código de gênero.
     */
    private Genero(int genero) {
        this.genero = genero;
    }

    /**
     * Obtém o código de gênero associado ao valor da enumeração.
     *
     * @return O código de gênero.
     */
    public int getGenero() {
        return genero;
    }

    /**
     * Obtém um valor da enumeração com base no código de gênero.
     *
     * @param genero O código de gênero.
     * @return Um valor da enumeração correspondente ao código de gênero.
     */
    public static Genero getGenero(int genero) {
        if (genero == 4) {
            return Genero.FEMININO;
        } else {
            return Genero.MASCULINO;
        }
    }
}

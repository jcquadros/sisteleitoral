package eleicao.exceptions;

/**
 * Exceção lançada quando ocorre um erro durante a leitura de um arquivo.
 */
public class LeituraDeArquivoException extends RuntimeException {

    /**
     * Constrói uma instância da exceção com a mensagem de erro especificada.
     *
     * @param message A mensagem de erro.
     */
    public LeituraDeArquivoException(String message) {
        super(message);
    }

    /**
     * Constrói uma instância da exceção com a mensagem de erro e a causa raiz especificadas.
     *
     * @param message A mensagem de erro.
     * @param cause A causa raiz da exceção.
     */
    public LeituraDeArquivoException(String message, Throwable cause) {
        super(message, cause);
    }
}

package exceptions;

public class LeituraDeArquivoException extends RuntimeException {

    public LeituraDeArquivoException(String message) {
        super(message);
    }

    public LeituraDeArquivoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

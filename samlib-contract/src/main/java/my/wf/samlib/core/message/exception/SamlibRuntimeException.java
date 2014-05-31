package my.wf.samlib.core.message.exception;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class SamlibRuntimeException extends RuntimeException {
    public SamlibRuntimeException() {
    }

    public SamlibRuntimeException(String message) {
        super(message);
    }

    public SamlibRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SamlibRuntimeException(Throwable cause) {
        super(cause);
    }
}

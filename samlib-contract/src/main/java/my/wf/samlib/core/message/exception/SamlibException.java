package my.wf.samlib.core.message.exception;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class SamlibException extends Exception {
    public SamlibException() {
    }

    public SamlibException(String message) {
        super(message);
    }

    public SamlibException(String message, Throwable cause) {
        super(message, cause);
    }

    public SamlibException(Throwable cause) {
        super(cause);
    }


}

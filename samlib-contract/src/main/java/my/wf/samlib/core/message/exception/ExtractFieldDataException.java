package my.wf.samlib.core.message.exception;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class ExtractFieldDataException extends SamlibException {
    public ExtractFieldDataException() {
    }

    public ExtractFieldDataException(String message) {
        super(message);
    }

    public ExtractFieldDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExtractFieldDataException(Throwable cause) {
        super(cause);
    }
}

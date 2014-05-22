package my.wf.samlib.core.message.exception;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class StorageException extends SamlibException {

    private Class storedClass;

    public StorageException() {
    }

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageException(Throwable cause) {
        super(cause);
    }

    public StorageException(Throwable cause, Class storedClass) {
        super(cause);
        this.storedClass = storedClass;
    }

    public StorageException(String message, Throwable cause, Class storedClass) {
        super(message, cause);
        this.storedClass = storedClass;
    }

    public Class getStoredClass() {
        return storedClass;
    }

    public void setStoredClass(Class storedClass) {
        this.storedClass = storedClass;
    }

}

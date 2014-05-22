package my.wf.samlib.storage.json.exception;

import my.wf.samlib.core.message.exception.StorageException;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class JsonStorageException extends StorageException{

    public JsonStorageException() {
    }

    public JsonStorageException(String message) {
        super(message);
    }

    public JsonStorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonStorageException(Throwable cause) {
        super(cause);
    }

    public JsonStorageException(Throwable cause, String fileName) {
        super("", cause);
    }
}

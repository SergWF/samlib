package my.wf.samlib.core.message.exception;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class ExtractFieldDataException extends SamlibException {
    private String fieldName;
    private Class entityClass;

    public String getFieldName() {
        return fieldName;
    }

    public Class getEntityClass() {
        return entityClass;
    }

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

    public ExtractFieldDataException(String message, String fieldName, Class entityClass) {
        super(message);
        this.fieldName = fieldName;
        this.entityClass = entityClass;
    }
    public ExtractFieldDataException(String fieldName, Class entityClass) {
        super("Wrong fieldName [" + fieldName + "] for class ["+entityClass+"]");
        this.fieldName = fieldName;
        this.entityClass = entityClass;
    }
}

package my.wf.samlib.core.message.exception;

import my.wf.samlib.core.dataextract.DataFieldExtractor;
import my.wf.samlib.core.model.entity.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class ExtractFieldDataException extends SamlibRuntimeException {
    private String fieldName;
    private Class entityClass;
    private  Class extractorClass;

    public String getFieldName() {
        return fieldName;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public Class getExtractorClass() {
        return extractorClass;
    }

    public ExtractFieldDataException(String fieldName, Class entityClass) {
        super("Wrong FieldDataExtractor for " + ((null == entityClass)?"NULL":entityClass.getName())+"."+ fieldName);
        this.fieldName = fieldName;
        this.entityClass = entityClass;

    }

    public ExtractFieldDataException(Class extractorClass, Class entityClass) {
        super("Wrong ExtractorClass "+((null==extractorClass)?"NULL":extractorClass.getName())+" + for " + ((null == entityClass)?"NULL":entityClass.getName()));
        this.entityClass = entityClass;
        this.extractorClass = extractorClass;
    }
}

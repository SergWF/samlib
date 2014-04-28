package my.wf.samlib.core.model.dataextract;

import my.wf.samlib.core.message.exception.ExtractFieldDataException;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.ComparableItem;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.extender.Readable;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class DataExtractorFactory {
    private static Map<Class, DataExtractor> map = new HashMap<Class, DataExtractor>();

    private class DataExtractorImpl<T extends BaseEntity> implements DataExtractor<T> {
        public Map<String, DataFieldExtractor> map = new HashMap<String, DataFieldExtractor>();

        @Override
        public <K> K getValue(T entity, ComparableItem<K> item) {
            return (K) map.get(item.getFieldName()).extractData(entity, item);
        }
    }

    public <T extends BaseEntity> DataExtractor<T> getDataExtractor(Class<T> clazz, Customer customer) {
        DataExtractor dataExtractor = map.get(clazz);
        if (null == dataExtractor) {
            dataExtractor = createDataExtractor(clazz, customer);
            map.put(clazz, dataExtractor);
        }
        return dataExtractor;
    }

    private <T extends BaseEntity> DataExtractor<T> createDataExtractor(Class<T> clazz, Customer customer) {
        FastClass fClass = FastClass.create(clazz);
        DataExtractorImpl<T> dataExtractor = new DataExtractorImpl<T>();
        for (Method m : clazz.getMethods()) {
            if (m.isAnnotationPresent(Readable.class)) {
                Readable annotation = m.getAnnotation(Readable.class);
                Class extractorClass = annotation.extractorClass();
                if (extractorClass.equals(DataFieldExtractor.class)) {
                    dataExtractor.map.put(annotation.name(), getDefaultDataFieldExtractor(fClass.getMethod(m)));
                }else{
                    dataExtractor.map.put(annotation.name(), getCustomDataFieldExtractor(extractorClass));
                }


            }
        }
        return dataExtractor;
    }

    private <T extends BaseEntity, K> DataFieldExtractor<T, K> getDefaultDataFieldExtractor(final FastMethod fMethod) {
        return new DataFieldExtractor<T, K>() {
            @Override
            public K extractData(T entity, ComparableItem<K> item) {
                try {
                    return (K) fMethod.invoke(entity, new Object[]{});
                } catch (InvocationTargetException e) {
                    throw new ExtractFieldDataException(e);
                }
            }
        };
    }

    private <T extends BaseEntity, K> DataFieldExtractor<T, K> getCustomDataFieldExtractor(final Class<CustomDataFieldExtractor<T, K>> extractorClass) {
        return new DataFieldExtractor<T, K>() {
            @Override
            public K extractData(T entity, ComparableItem<K> item) {
                try {
                    return extractorClass.newInstance().extractData(entity, item);
                } catch (InstantiationException e) {
                    throw new ExtractFieldDataException(e);
                } catch (IllegalAccessException e) {
                    throw new ExtractFieldDataException(e);
                }
            }
        };
    }
}

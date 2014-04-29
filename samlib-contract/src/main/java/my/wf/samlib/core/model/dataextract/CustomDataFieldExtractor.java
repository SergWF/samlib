package my.wf.samlib.core.model.dataextract;

import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.ComparableItem;

import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface CustomDataFieldExtractor<T extends BaseEntity, K> extends DataFieldExtractor<T, K> {
    //K extractData(T entity, ComparableItem<K> item);
}

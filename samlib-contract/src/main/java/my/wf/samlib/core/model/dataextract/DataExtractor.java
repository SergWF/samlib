package my.wf.samlib.core.model.dataextract;

import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.ComparableItem;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Serg on 27.04.2014.
 */
public interface DataExtractor<T extends BaseEntity> {
    <K> K getValue(T entity, ComparableItem<K> item);
}

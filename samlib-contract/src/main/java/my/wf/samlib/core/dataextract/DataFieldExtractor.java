package my.wf.samlib.core.dataextract;

import my.wf.samlib.core.message.exception.ExtractFieldDataException;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.ComparableItem;

public interface DataFieldExtractor<T extends BaseEntity, K> {
    K extractData(T entity, ComparableItem<K> item) throws ExtractFieldDataException;
}

package my.wf.samlib.core.dataextract;

import my.wf.samlib.core.message.exception.ExtractFieldDataException;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.ComparableItem;

public interface DataExtractor<T extends BaseEntity> {
    <K> K getValue(T entity, ComparableItem<K> item) throws ExtractFieldDataException;
}

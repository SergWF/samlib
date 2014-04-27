package my.wf.samlib.storage.file.storage.filtering;

import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.ComparableItem;

/**
 * Created by Serg on 27.04.2014.
 */
public interface DataExtractor {
    <T extends BaseEntity> String getValue(T entity, ComparableItem<T> item);
}

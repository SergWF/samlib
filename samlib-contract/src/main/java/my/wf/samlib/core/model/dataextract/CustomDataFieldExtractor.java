package my.wf.samlib.core.model.dataextract;

import my.wf.samlib.core.model.entity.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface CustomDataFieldExtractor<T extends BaseEntity, K> extends DataFieldExtractor<T, K> {
    //K extractData(T entity, ComparableItem<K> item);
}

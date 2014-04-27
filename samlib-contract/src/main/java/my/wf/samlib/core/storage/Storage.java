package my.wf.samlib.core.storage;

import my.wf.samlib.core.filtering.CustomerFiltering;
import my.wf.samlib.core.ordering.CustomerOrdering;
import my.wf.samlib.core.model.entity.BaseEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface Storage<T extends BaseEntity> {
    T get(Long id);
    T save(T entity);
    T remove(T entity);
    List<T> list(CustomerFiltering<T> filter, CustomerOrdering<T> order);
}

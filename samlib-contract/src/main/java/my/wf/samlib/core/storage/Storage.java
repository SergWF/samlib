package my.wf.samlib.core.storage;

import my.wf.samlib.core.dataextract.filtering.CustomerFiltering;
import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.message.exception.ExtractFieldDataException;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.BaseEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface Storage<T extends BaseEntity> {
    T get(Long id);
    T save(T entity) throws StorageException;
    T remove(T entity) throws StorageException;
    List<T> list(CustomerFiltering<T> filter, CustomerOrdering<T> order);
}

package my.wf.samlib.core.factory;

import my.wf.samlib.core.filtering.CustomerFiltering;
import my.wf.samlib.core.model.extender.Filterable;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface FilterFactory {
    <T extends Filterable> CustomerFiltering<T> createFilter(Class<T> clazz, Customer customer);
}

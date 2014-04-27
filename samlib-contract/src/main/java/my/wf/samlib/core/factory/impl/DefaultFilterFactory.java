package my.wf.samlib.core.factory.impl;

import my.wf.samlib.core.factory.FilterFactory;
import my.wf.samlib.core.filtering.CustomerFiltering;
import my.wf.samlib.core.model.extender.Filterable;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class DefaultFilterFactory implements FilterFactory {
    @Override
    public <T extends Filterable> CustomerFiltering<T> createFilter(Class<T> clazz, Customer customer) {
        return new CustomerFiltering<T>(clazz, customer);
    }
}

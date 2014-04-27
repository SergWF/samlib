package my.wf.samlib.core.factory.impl;

import my.wf.samlib.core.factory.OrderFactory;
import my.wf.samlib.core.ordering.CustomerOrdering;
import my.wf.samlib.core.model.extender.Orderable;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class DefaultOrderFactory implements OrderFactory {
    @Override
    public <T extends Orderable> CustomerOrdering<T> createOrdering(Class<T> clazz, Customer customer) {
        return new CustomerOrdering<T>(clazz, customer);
    }
}
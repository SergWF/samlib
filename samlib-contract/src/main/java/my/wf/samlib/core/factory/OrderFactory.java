package my.wf.samlib.core.factory;

import my.wf.samlib.core.ordering.CustomerOrdering;
import my.wf.samlib.core.model.extender.Orderable;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface OrderFactory {
    <T extends Orderable> CustomerOrdering<T> createOrdering(Class<T> clazz, Customer customer);
}

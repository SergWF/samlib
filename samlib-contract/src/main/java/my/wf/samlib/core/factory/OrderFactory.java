package my.wf.samlib.core.factory;

import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface OrderFactory {
    <T extends BaseEntity> CustomerOrdering<T> createOrdering(Class<T> clazz, Customer customer);
}

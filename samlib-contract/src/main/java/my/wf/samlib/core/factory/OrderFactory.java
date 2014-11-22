package my.wf.samlib.core.factory;

import my.wf.samlib.core.dataextract.DataExtractorFactory;
import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface OrderFactory {
    <T extends BaseEntity> CustomerOrdering<T> createOrdering(Class<T> clazz, Customer customer);

    void setDataExtractorFactory(DataExtractorFactory dataExtractorFactory);
}

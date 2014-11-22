package my.wf.samlib.core.factory.impl;

import my.wf.samlib.core.dataextract.DataExtractorFactory;
import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.factory.OrderFactory;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class DefaultOrderFactory implements OrderFactory {
    private DataExtractorFactory dataExtractorFactory;
    @Override
    public <T extends BaseEntity> CustomerOrdering<T> createOrdering(Class<T> clazz, Customer customer) {
        return new CustomerOrdering<T>(clazz, customer, dataExtractorFactory.getDataExtractor(clazz));
    }

    @Override
    public void setDataExtractorFactory(DataExtractorFactory dataExtractorFactory) {
        this.dataExtractorFactory = dataExtractorFactory;
    }
}

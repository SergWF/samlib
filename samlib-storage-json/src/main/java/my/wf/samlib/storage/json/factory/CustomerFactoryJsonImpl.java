package my.wf.samlib.storage.json.factory;

import my.wf.samlib.core.factory.CustomerFactory;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.storage.json.model.CustomerJson;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class CustomerFactoryJsonImpl implements CustomerFactory {
    @Override
    public Customer newCustomer(String name) {
        CustomerJson customer = new CustomerJson();
        customer.setName(name);
        return customer;
    }
}

package my.wf.samlib.core.factory.impl;

import my.wf.samlib.core.factory.CustomerFactory;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class DefaultCustomerFactory implements CustomerFactory {
    @Override
    public Customer newCustomer(String name) {
        Customer customer  = new Customer();
        customer.setName(name);
        return customer;
    }
}

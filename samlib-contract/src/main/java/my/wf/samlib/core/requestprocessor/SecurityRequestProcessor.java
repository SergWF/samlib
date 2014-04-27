package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.factory.CustomerFactory;
import my.wf.samlib.core.storage.CustomerStorage;
import my.wf.samlib.core.model.entity.Admin;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class SecurityRequestProcessor {
    CustomerStorage customerStorage;
    CustomerFactory customerFactory;

    public CustomerStorage getCustomerStorage() {
        return customerStorage;
    }

    public void setCustomerStorage(CustomerStorage customerStorage) {
        this.customerStorage = customerStorage;
    }

    public CustomerFactory getCustomerFactory() {
        return customerFactory;
    }

    public void setCustomerFactory(CustomerFactory customerFactory) {
        this.customerFactory = customerFactory;
    }

    public Customer addNewCustomer(String name, String password, Admin admin) {
        Customer customer = customerFactory.newCustomer(name);
        return customerStorage.createNewCustomerRecord(customer, password);
    }

    public Customer getCustomerByCredentials(String customerName, String password){
        return customerStorage.getByCredentials(customerName, password);
    }

    public Customer removeCustomer(Admin admin, Customer customer) {
        return customerStorage.remove(customer);
    }

    public Customer disableCustomer(Admin admin, Customer customer) {
        customer.setEnabled(false);
        return customerStorage.save(customer);
    }
}

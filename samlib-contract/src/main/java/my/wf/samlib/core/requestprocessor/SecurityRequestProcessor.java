package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.factory.EntityFactory;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Admin;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.storage.CustomerStorage;

public class SecurityRequestProcessor {
    CustomerStorage customerStorage;
    EntityFactory entityFactory;
    CredentialsChecker credentialsChecker;

    public void setCustomerStorage(CustomerStorage customerStorage) {
        this.customerStorage = customerStorage;
    }

    public void setCustomerFactory(EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
    }

    public void setCredentialsChecker(CredentialsChecker credentialsChecker) {
        this.credentialsChecker = credentialsChecker;
    }

    public Customer addNewCustomer(String name, String password, Admin admin) {
        Customer customer = entityFactory.createInstance(Customer.class);
        customer.setName(name);
        return customerStorage.createNewCustomerRecord(customer, password);
    }

    public Customer getCustomerByCredentials(String customerName, String password){
        Customer customer = customerStorage.getByName(customerName);
        return credentialsChecker.check(customer, password)?customer:null;
    }

    public Customer removeCustomer(Admin admin, Customer customer) throws StorageException {
        return customerStorage.remove(customer);
    }

    public Customer disableCustomer(Admin admin, Customer customer) throws StorageException {
        customer.setEnabled(false);
        return customerStorage.save(customer);
    }
}

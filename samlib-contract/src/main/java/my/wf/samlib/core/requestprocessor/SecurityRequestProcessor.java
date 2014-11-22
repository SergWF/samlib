package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.factory.CustomerFactory;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Admin;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.storage.CustomerStorage;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class SecurityRequestProcessor {
    CustomerStorage customerStorage;
    CustomerFactory customerFactory;
    CredentialsChecker credentialsChecker;

    public void setCustomerStorage(CustomerStorage customerStorage) {
        this.customerStorage = customerStorage;
    }

    public void setCustomerFactory(CustomerFactory customerFactory) {
        this.customerFactory = customerFactory;
    }

    public void setCredentialsChecker(CredentialsChecker credentialsChecker) {
        this.credentialsChecker = credentialsChecker;
    }

    public Customer addNewCustomer(String name, String password, Admin admin) {
        Customer customer = customerFactory.newCustomer(name);
        return customerStorage.createNewCustomerRecord(customer, password);
    }

    public Customer getCustomerByCredentials(String customerName, String password){
        Customer customer = customerStorage.getByName(customerName);
        return credentialsChecker.checkPassword(customer, password)?customer:null;
    }

    public Customer removeCustomer(Admin admin, Customer customer) throws StorageException {
        return customerStorage.remove(customer);
    }

    public Customer disableCustomer(Admin admin, Customer customer) throws StorageException {
        customer.setEnabled(false);
        return customerStorage.save(customer);
    }
}

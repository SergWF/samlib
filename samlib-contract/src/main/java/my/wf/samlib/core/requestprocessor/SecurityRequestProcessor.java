package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.factory.EntityFactory;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Admin;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.storage.CustomerStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityRequestProcessor {

    private static final Logger logger = LoggerFactory.getLogger(AuthorProcessor.class);

    private CustomerStorage customerStorage;
    private EntityFactory entityFactory;
    private CredentialsChecker credentialsChecker;

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
        logger.info("Add new customer ["+name+"] to the system");
        Customer customer = entityFactory.createInstance(Customer.class);
        customer.setName(name);
        return customerStorage.createNewCustomerRecord(customer, password);
    }

    public Customer getCustomerByCredentials(String customerName, String password){
        Customer customer = customerStorage.getByName(customerName);
        logger.info("Read credentials for customer ["+customerName+"]. customer " + (null==customer?"not found":"found"));
        return credentialsChecker.check(customer, password)?customer:null;
    }

    public Customer removeCustomer(Admin admin, Customer customer) throws StorageException {
        logger.info("Remove customer ["+customer.getName()+"] from the system");
        return customerStorage.remove(customer);
    }

    public Customer disableCustomer(Admin admin, Customer customer) throws StorageException {
        logger.info("Disable customer ["+customer.getName()+"]");
        customer.setEnabled(false);
        return customerStorage.save(customer);
    }
}

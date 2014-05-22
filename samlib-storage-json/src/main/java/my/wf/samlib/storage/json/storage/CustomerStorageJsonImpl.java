package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.storage.CustomerStorage;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class CustomerStorageJsonImpl extends BaseStorageJsonImpl<Customer> implements CustomerStorage {


    public CustomerStorageJsonImpl() {
    }

    @Override
    protected Class<Customer> getStoredClass() {
        return Customer.class;
    }

    @Override
    public Customer getByName(String customerName) {
        for (Customer customer : getEntityCollection()) {
            if(customer.getName().equals(customerName)){
                return customer;
            }
        }
        return null;
    }

    @Override
    public Customer createNewCustomerRecord(Customer customer, String password) {
        return null;
    }

}

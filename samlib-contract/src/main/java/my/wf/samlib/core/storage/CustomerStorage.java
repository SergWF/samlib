package my.wf.samlib.core.storage;

import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface CustomerStorage extends Storage<Customer> {
    Customer getByName(String customerName);
    Customer createNewCustomerRecord(Customer customer, String password);
}

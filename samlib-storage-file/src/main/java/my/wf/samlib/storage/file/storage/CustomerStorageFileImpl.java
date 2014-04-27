package my.wf.samlib.storage.file.storage;

import my.wf.samlib.core.filtering.CustomerFiltering;
import my.wf.samlib.core.ordering.CustomerOrdering;
import my.wf.samlib.core.storage.CustomerStorage;
import my.wf.samlib.core.model.entity.Customer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class CustomerStorageFileImpl extends BaseStorageFileImpl<Customer> implements CustomerStorage {


    private EntityData<Customer> customerData;

    @Override
    protected EntityData<Customer> getData() {
        if(null == customerData){
            customerData = new EntityData<Customer>();
        }
        return customerData;
    }

    @Override
    public Customer getByCredentials(String customerName, String password) {
        return null;
    }

    @Override
    public Customer createNewCustomerRecord(Customer customer, String password) {
        return null;
    }
}

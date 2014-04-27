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
public class CustomerStorageFileImpl implements CustomerStorage {

    private String dataFileName;

    public String getDataFileName() {
        return dataFileName;
    }

    public void setDataFileName(String dataFileName) {
        this.dataFileName = dataFileName;
    }

    @Override
    public Customer getByCredentials(String customerName, String password) {
        return null;
    }

    @Override
    public Customer createNewCustomerRecord(Customer customer, String password) {
        return null;
    }

    @Override
    public Customer get(Long id) {
        return null;
    }

    @Override
    public Customer save(Customer entity) {
        return null;
    }

    @Override
    public Customer remove(Customer entity) {
        return null;
    }

    @Override
    public List<Customer> list(CustomerFiltering<Customer> filter, CustomerOrdering<Customer> order) {
        return null;
    }
}

package my.wf.samlib.storage.jpa.srtorage;

import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.storage.CustomerStorage;
import my.wf.samlib.storage.jpa.model.CustomerJpa;
import my.wf.samlib.storage.jpa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Serg on 22.01.2015.
 */
@Component
public class CustomerStorageJpaImpl implements CustomerStorage {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Customer getByName(String customerName) {
        return customerRepository.getByName(customerName);
    }

    @Override
    public Customer createNewCustomerRecord(Customer customer, String password) {
        return null;
    }

    @Override
    public Customer get(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer save(Customer entity) throws StorageException {
        return customerRepository.save((CustomerJpa) entity);
    }

    @Override
    public Customer remove(Customer entity) throws StorageException {
        customerRepository.delete((CustomerJpa) entity);
        return entity;
    }

    @Override
    public List<Customer> list() {
        return (List<Customer>)(List<?>)customerRepository.findAll();
    }
}

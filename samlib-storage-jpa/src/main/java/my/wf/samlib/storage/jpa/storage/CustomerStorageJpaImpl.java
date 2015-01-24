package my.wf.samlib.storage.jpa.storage;

import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.storage.CustomerStorage;
import my.wf.samlib.storage.jpa.model.CustomerJpa;
import my.wf.samlib.storage.jpa.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Serg on 22.01.2015.
 */
@Component
public class CustomerStorageJpaImpl implements CustomerStorage {

    private static final Logger logger = LoggerFactory.getLogger(CustomerStorageJpaImpl.class);

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Customer getByName(String customerName) {
        logger.debug("Get Customer by name ["+customerName+"]");
        return customerRepository.getByName(customerName);
    }

    @Override
    public Customer createNewCustomerRecord(Customer customer, String password) {
        logger.debug("Create new Customer ["+customer.getName()+"]");
        return customer;
    }

    @Override
    public Customer get(Long id) {
        logger.debug("Get customer by ID ["+id+"]");
        return customerRepository.findOne(id);
    }

    @Override
    public Customer save(Customer entity) throws StorageException {
        logger.debug("Save Customer " + entity.getName());
        return customerRepository.save((CustomerJpa) entity);
    }

    @Override
    public Customer remove(Customer entity) throws StorageException {
        logger.debug("Remove Customer " + entity.getName());
        customerRepository.delete((CustomerJpa) entity);
        return entity;
    }

    @Override
    public List<Customer> list() {
        logger.debug("Get Customer list");
        return (List<Customer>)(List<?>)customerRepository.findAll();
    }
}

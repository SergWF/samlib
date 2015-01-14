package my.wf.samlib.storage.hibernate.storage;

import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CustomerStorageHbTest extends BaseStorageTest {
    CustomerStorageHb storage;
    Customer customer;

    @Before
    public void setUp() throws StorageException {
        super.setUp();
        storage = new CustomerStorageHb(sessionFactory);
        customer = customers.get(1);
    }

    @Test
    public void testGetByName(){
        Customer actual = storage.getByName(customer.getName());
        assertEquals(customer, actual);
    }

    @Test
    public void testGetById(){
        Customer actual = storage.get(customer.getId());
        assertEquals(customer, actual);
    }

    @Test
    public void testList(){
        assertEquals(customers.size(), storage.list(null, null).size());
    }

    @Test
    public void testGetAuthors() throws StorageException {
        customer.getAuthors().add(authors.get(0));
        customer.getAuthors().add(authors.get(2));
        customer.getAuthors().add(authors.get(4));

        customers.get(0).getAuthors().add(authors.get(0));
        customers.get(0).getAuthors().add(authors.get(1));
        customers.get(2).getAuthors().add(authors.get(1));
        customers.get(2).getAuthors().add(authors.get(2));
        customers.get(2).getAuthors().add(authors.get(3));
        customers.get(2).getAuthors().add(authors.get(4));
        for(Customer c: customers){
            storage.save(c);
        }
        Customer actual = storage.get(customer.getId());
        Set<Author> actualAuthors = actual.getAuthors();
        assertEquals(3, actualAuthors.size());
        for(Author a: actualAuthors){
            System.out.println(a);
        }
    }
}

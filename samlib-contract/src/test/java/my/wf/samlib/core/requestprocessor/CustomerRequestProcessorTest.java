package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.BaseTest;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
public class CustomerRequestProcessorTest extends BaseTest {

    private CustomerRequestProcessor customerRequestProcessor;
    private Customer customer;
    private Set<Author> customersAuthors;

    @Before
    public void setUp() throws StorageException {
        initMockedStorages();
        customer = customers.get(1);
        customersAuthors = new HashSet<>(Arrays.asList(authors.get(0), authors.get(1), authors.get(2)));
        customer.getAuthors().addAll(customersAuthors);
        customerRequestProcessor = new CustomerRequestProcessor();
        customerRequestProcessor.setCustomerStorage(customerStorage);
        customerRequestProcessor.setAuthorStorage(authorStorage);
        customerRequestProcessor.setMessageProcessor(messageProcessor);
    }

    @Test
    public void testAddAuthorToCustomer() throws StorageException {
        Author a = authors.get(7);
        //Before start: Check author not in customer's list
        assertFalse(customer.getAuthors().contains(a));
        //add authorTo customer's list
        Author actual = customerRequestProcessor.addAuthor(customer, a);
        assertEquals(a.getId(), actual.getId());
        //Check author in customer's list
        assertTrue(customer.getAuthors().contains(a));
    }

    @Test
    public void behaviourAddAuthorToCustomer() throws StorageException {
        Author a = authors.get(7);
        //Before start: Check author not in customer's list
        assertFalse(customer.getAuthors().contains(a));
        //add authorTo customer's list
        Author actual = customerRequestProcessor.addAuthor(customer, a);
        Mockito.verify(authorStorage, Mockito.never()).save(a);
        Mockito.verify(customerStorage, Mockito.times(1)).save(customer);
    }

    @Test
    public void behaviourAddExistingAuthorToCustomer() throws StorageException {
        Author a = authors.get(1);
        //Before start: Check author not in customer's list
        assertTrue(customer.getAuthors().contains(a));
        //add authorTo customer's list
        Author actual = customerRequestProcessor.addAuthor(customer, a);
        assertEquals(a.getId(), actual.getId());
        Mockito.verify(authorStorage, Mockito.never()).save(a);
        Mockito.verify(customerStorage, Mockito.never()).save(customer);
    }

    @Test
    public void testGetAuthorsNoFilters() throws StorageException {
        Set<Author> actual = customerRequestProcessor.getAuthors(customer, null, false, null);
        assertThat(actual, is(customersAuthors));
    }

    @Test
    public void testGetAuthorsFilterByNameOnlyOne() throws StorageException {
        Set<Author> actual = customerRequestProcessor.getAuthors(customer, "1", false, null);
        assertEquals(1, actual.size());
        assertEquals(authors.get(1), actual.iterator().next());
    }

    @Test
    public void testGetAuthorsFilterByNameAll() throws StorageException {
        Set<Author> actual = customerRequestProcessor.getAuthors(customer, "Author", false, null);
        assertThat(actual, is(customersAuthors));
    }

    @Test
    public void testGetAuthorsFilterByNameNone() throws StorageException {
        Set<Author> actual = customerRequestProcessor.getAuthors(customer, "None", false, null);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void testGetAuthorsFilterByUnreadNone() throws StorageException {
        assertTrue(customer.getUnreadWritings().isEmpty());
        Set<Author> actual = customerRequestProcessor.getAuthors(customer, null, true, null);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void testGetAuthorsFilterByUnread() throws StorageException {
        customer.getUnreadWritings().add(authors.get(0).getWritings().iterator().next());
        customer.getUnreadWritings().add(authors.get(2).getWritings().iterator().next());
        Set<Author> actual = customerRequestProcessor.getAuthors(customer, null, true, null);
        assertEquals(2, actual.size());
        assertTrue(actual.contains(authors.get(0)));
        assertTrue(actual.contains(authors.get(2)));
    }

}
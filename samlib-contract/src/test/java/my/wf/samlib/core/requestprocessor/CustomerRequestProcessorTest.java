package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.EntityCreator;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.core.storage.CustomerStorage;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class CustomerRequestProcessorTest {

    @Test
    public void testMarkAuthorAsRead() throws Exception {
        CustomerRequestProcessor customerRequestProcessor;
        customerRequestProcessor = new CustomerRequestProcessor();
        customerRequestProcessor.setCustomerStorage(Mockito.mock(CustomerStorage.class));

        Writing w1 = EntityCreator.createWriting(10L);
        Writing w2 = EntityCreator.createWriting(20L);
        Writing w3 = EntityCreator.createWriting(30L);
        Writing w4 = EntityCreator.createWriting(40L);
        Writing w5 = EntityCreator.createWriting(50L);
        Writing w6 = EntityCreator.createWriting(60L);
        Customer customer = EntityCreator.createCustomer(w1,w2,w3,w4,w5);
        Author author1 = EntityCreator.createAuthor(1L, w1,w2,w3);
        Author author2 = EntityCreator.createAuthor(2L, w4,w5,w6);
        customer.getAuthors().add(author1);
        customer.getAuthors().add(author2);
        customerRequestProcessor.markAuthorAsRead(customer, author1);
        assertEquals(2, customer.getUnreadWritings().size());
        assertFalse(author1.unreadByCustomer(customer));
        assertTrue(author2.unreadByCustomer(customer));
    }
}
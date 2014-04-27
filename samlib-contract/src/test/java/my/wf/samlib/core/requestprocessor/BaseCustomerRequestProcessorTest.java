package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.BaseTest;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class BaseCustomerRequestProcessorTest extends BaseTest {

    protected Customer customer;
    private Set<Author> authors = new HashSet<Author>();
    private Set<Writing> unreadWritings = new HashSet<Writing>();

    protected void initCustomer(){
        customer  = Mockito.mock(Customer.class);
        Mockito.when(customer.getAuthors()).thenReturn(authors);
        Mockito.when(customer.getUnreadWritings()).thenReturn(unreadWritings);
    }
}

package my.wf.samlib.core;

import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.requestprocessor.MessageProcessor;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.core.storage.CustomerStorage;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serg on 22.01.2015.
 */
public abstract class BaseTest {

    protected List<Author> authors;
    protected List<Customer> customers;
    protected AuthorStorage authorStorage;
    protected CustomerStorage customerStorage;
    protected MessageProcessor messageProcessor;

    protected void createDataStructure(){
        authors = new ArrayList<>();
        customers = new ArrayList<>();
        for(int i = 0; i< 10; i++){
            authors.add(EntityCreator.createAuthor(i, 5));
        }
        for(int i = 0; i< 4; i++){
            Customer c =  EntityCreator.createCustomer(i);
            //c.getAuthors().addAll(Arrays.asList(authors.get(i), authors.get(i+1), authors.get(i+2)));
            customers.add(c);
        }
    }

    protected void initMockedStorages() throws StorageException {
        createDataStructure();
        messageProcessor = Mockito.mock(MessageProcessor.class);

        authorStorage = Mockito.mock(AuthorStorage.class);
        for(int i = 0; i < authors.size(); i++){
            Author a = authors.get(i);
            Mockito.when(authorStorage.get(Long.valueOf(i))).thenReturn(a);
            Mockito.when(authorStorage.save(a)).thenReturn(a);
            Mockito.when(authorStorage.list()).thenReturn(authors);
            Mockito.when(authorStorage.findByLink(a.getLink())).thenReturn(a);
        }

        customerStorage = Mockito.mock(CustomerStorage.class);
        for(int i = 0; i< customers.size(); i++){
            Customer c = customers.get(i);
            Mockito.when(customerStorage.get(Long.valueOf(i))).thenReturn(c);
            Mockito.when(customerStorage.save(c)).thenReturn(c);
            Mockito.when(customerStorage.list()).thenReturn(customers);
            Mockito.when(customerStorage.getByName(c.getName())).thenReturn(c);
        }
    }
}

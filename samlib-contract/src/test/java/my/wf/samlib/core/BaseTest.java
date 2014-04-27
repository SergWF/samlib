package my.wf.samlib.core;

import my.wf.samlib.core.requestprocessor.AuthorProcessor;
import my.wf.samlib.core.requestprocessor.CustomerRequestProcessor;
import my.wf.samlib.core.requestprocessor.MessageProcessor;
import my.wf.samlib.core.sprider.AuthorWebReader;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.core.storage.CustomerStorage;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Writing;
import org.mockito.Mockito;

import java.awt.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class BaseTest {

    public static final String INITIALIZE_STRUCTURE_FIRST_PLEASE = "Initialize Structure first, please";
    CustomerRequestProcessor customerRequestProcessor;
    AuthorProcessor authorProcessor;
    MessageProcessor messageProcessor;

    CustomerStorage customerStorage;
    AuthorWebReader webReader;
    private AuthorStorage authorStorage;

    protected void initBaseStructure(){
        customerRequestProcessor = new CustomerRequestProcessor();
        authorProcessor = new AuthorProcessor();
        messageProcessor = new MessageProcessor();

        customerStorage = Mockito.mock(CustomerStorage.class);
        webReader = Mockito.mock(AuthorWebReader.class);
        authorStorage = Mockito.mock(AuthorStorage.class);

        customerRequestProcessor.setAuthorProcessor(authorProcessor);
        customerRequestProcessor.setCustomerStorage(customerStorage);
        customerRequestProcessor.setMessageProcessor(messageProcessor);
        customerRequestProcessor.setWebReader(webReader);

        authorProcessor.setWebReader(webReader);
        authorProcessor.setMessageProcessor(messageProcessor);
        authorProcessor.setAuthorStorage(authorStorage);
    }

    protected Author createAuthor(Long id, String link, String name, Date lastDate, boolean isUnread){
        Author author = Mockito.mock(Author.class);
        Mockito.when(author.getId()).thenReturn(id);
        Mockito.when(author.getLink()).thenReturn(link);
        Mockito.when(author.getName()).thenReturn(name);
        Mockito.when(author.getLastChanged()).thenReturn(lastDate);
        Mockito.when(author.isUnread()).thenReturn(isUnread);
        return author;
    }

    protected Writing createWriting(Long id, String link, String name, Date lastDate, Author author){
        Writing writing = Mockito.mock(Writing.class);
        Mockito.when(writing.getLink()).thenReturn(link);
        Mockito.when(writing.getId()).thenReturn(id);
        Mockito.when(writing.getName()).thenReturn(name);
        Mockito.when(writing.getLastChanged()).thenReturn(lastDate);
        Mockito.when(writing.getAuthor()).thenReturn(author);
        return writing;
    }

    protected <T extends BaseEntity> T getEntityById(long id, Collection<T> entities){
        for(T entity: entities){
            if(entity.getId().equals(id)){
                return entity;
            }
        }
        return null;
    }

    protected CustomerRequestProcessor getCustomerRequestProcessor() {
        if(null == customerRequestProcessor){
            throw new IllegalComponentStateException(INITIALIZE_STRUCTURE_FIRST_PLEASE);
        }
        return customerRequestProcessor;
    }

    protected AuthorProcessor getAuthorProcessor() {
        if(null == authorProcessor){
            throw new IllegalComponentStateException(INITIALIZE_STRUCTURE_FIRST_PLEASE);
        }
        return authorProcessor;
    }

    protected MessageProcessor getMessageProcessor() {
        if(null == messageProcessor){
            throw new IllegalComponentStateException(INITIALIZE_STRUCTURE_FIRST_PLEASE);
        }
        return messageProcessor;
    }

    protected CustomerStorage getCustomerStorage() {
        if(null == customerStorage){
            throw new IllegalComponentStateException(INITIALIZE_STRUCTURE_FIRST_PLEASE);
        }
        return customerStorage;
    }

    protected AuthorWebReader getWebReader() {
        if(null == webReader){
            throw new IllegalComponentStateException(INITIALIZE_STRUCTURE_FIRST_PLEASE);
        }
        return webReader;
    }

    protected AuthorStorage getAuthorStorage() {
        if(null == authorStorage){
            throw new IllegalComponentStateException(INITIALIZE_STRUCTURE_FIRST_PLEASE);
        }
        return authorStorage;
    }
}

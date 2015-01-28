package my.wf.samlib.storage.jpa.impl;

import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.factory.EntityFactory;
import my.wf.samlib.core.message.exception.ExtractFieldDataException;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.core.requestprocessor.CustomerRequestProcessor;
import my.wf.samlib.core.requestprocessor.MessageProcessor;
import my.wf.samlib.core.sprider.AuthorWebReader;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.core.storage.CustomerStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Serg on 27.01.2015.
 */
@Component
public class CustomerRequestProcessorJpaImpl extends CustomerRequestProcessor {

    @Override
    @Autowired(required = true)
    public void setCustomerStorage(CustomerStorage customerStorage) {
        super.setCustomerStorage(customerStorage);
    }

    @Override
    @Autowired(required = true)
    public void setWebReader(AuthorWebReader webReader) {
        super.setWebReader(webReader);
    }

    @Override
    @Autowired(required = true)
    public void setMessageProcessor(MessageProcessor messageProcessor) {
        super.setMessageProcessor(messageProcessor);
    }

    @Override
    @Autowired(required = true)
    public void setAuthorStorage(AuthorStorage authorStorage) {
        super.setAuthorStorage(authorStorage);
    }

    @Override
    @Autowired(required = true)
    public void setCustomerFactory(EntityFactory entityFactory) {
        super.setCustomerFactory(entityFactory);
    }

    protected Customer refresh(Customer customer){
        return customerStorage.get(customer.getId());
    }

    protected Author refresh(Author author){
        return authorStorage.get(author.getId());
    }

    @Override
    @Transactional
    public Set<Author> getAuthors(Customer customer, String namePattern, boolean unreadOnly, CustomerOrdering customerOrdering) throws StorageException, ExtractFieldDataException {
        return super.getAuthors(refresh(customer), namePattern, unreadOnly, customerOrdering);
    }

    @Override
    @Transactional
    public Set<Writing> getWritings(Customer customer, Author author, String namePattern, boolean unreadOnly, CustomerOrdering customerOrdering) throws ExtractFieldDataException {
        return super.getWritings(refresh(customer), refresh(author), namePattern, unreadOnly, customerOrdering);
    }

    @Override
    @Transactional
    public Author addAuthor(Customer customer, String authorLink) throws IOException, StorageException {
        return super.addAuthor(refresh(customer), authorLink);
    }

    @Override
    @Transactional
    public Author addAuthor(Customer customer, Author author) throws StorageException {
        return super.addAuthor(refresh(customer), author);
    }

    @Override
    @Transactional
    public Author removeAuthor(Customer customer, Author author) throws StorageException {
        return super.removeAuthor(refresh(customer), author);
    }

    @Override
    @Transactional
    public Author markAuthorAsRead(Customer customer, Author author) throws StorageException {
        return super.markAuthorAsRead(refresh(customer), author);
    }

    @Override
    @Transactional
    public Writing changeReadFlag(Customer customer, Writing writing, boolean isRead) {
        return super.changeReadFlag(refresh(customer), writing, isRead);
    }
}

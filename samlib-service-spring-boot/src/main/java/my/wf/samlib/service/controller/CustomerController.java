package my.wf.samlib.service.controller;

import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.core.requestprocessor.CustomerRequestProcessor;
import my.wf.samlib.core.storage.AuthorStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class CustomerController {
    @Autowired
    CustomerRequestProcessor customerRequestProcessor;
    @Autowired
    AuthController authController;
    @Autowired
    AuthorStorage authorStorage;


    private Customer getCustomer(){
        return authController.getActiveCustomer();
    }

    public Set<Author> getAuthors(String namePattern, Boolean unreadOnly) throws StorageException {
        return customerRequestProcessor.getAuthors(getCustomer(), namePattern, unreadOnly, null);
    }

    public Set<Writing> getWritings(Long authorId, String namePattern, Boolean unreadOnly){
        return customerRequestProcessor.getWritings(getCustomer(), authorStorage.get(authorId), namePattern, unreadOnly, null);
    }

    public Author addAuthor(Author authorLink) throws StorageException {
        return  customerRequestProcessor.addAuthor(getCustomer(), authorLink);
    }

    public Author removeAuthor(Long authorId) throws StorageException {
        return  customerRequestProcessor.removeAuthor(getCustomer(), authorStorage.get(authorId));
    }

    public Author markAuthorAsRead(Long authorId) throws StorageException {
        return  customerRequestProcessor.markAuthorAsRead(getCustomer(), authorStorage.get(authorId));
    }

    public Writing changeReadFlag(Long writingId, Boolean isRead){
        return customerRequestProcessor.changeReadFlag(getCustomer(), authorStorage.getWriting(writingId), isRead);
    }

}

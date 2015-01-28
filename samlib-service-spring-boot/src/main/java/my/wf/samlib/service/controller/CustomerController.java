package my.wf.samlib.service.controller;

import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.core.requestprocessor.CustomerRequestProcessor;
import my.wf.samlib.core.storage.AuthorStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.util.Set;

@Controller
public class CustomerController {
    @Autowired
    CustomerRequestProcessor customerRequestProcessor;
    @Autowired
    AuthController authController;
    @Autowired
    AuthorStorage authorStorage;

    @PreAuthorize("isAnonymous()")
    private Customer getCustomer(){
        return authController.getActiveCustomer();
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Transactional
    public Set<Author> getAuthors(String namePattern, Boolean unreadOnly) throws StorageException {
        return customerRequestProcessor.getAuthors(getCustomer(), namePattern, unreadOnly, null);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Set<Writing> getWritings(Long authorId, String namePattern, Boolean unreadOnly){
        return customerRequestProcessor.getWritings(getCustomer(), authorStorage.get(authorId), namePattern, unreadOnly, null);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Author addAuthor(Author authorLink) throws StorageException {
        return  customerRequestProcessor.addAuthor(getCustomer(), authorLink);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Author removeAuthor(Long authorId) throws StorageException {
        return  customerRequestProcessor.removeAuthor(getCustomer(), authorStorage.get(authorId));
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Author markAuthorAsRead(Long authorId) throws StorageException {
        return  customerRequestProcessor.markAuthorAsRead(getCustomer(), authorStorage.get(authorId));
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Writing changeReadFlag(Long writingId, Boolean isRead){
        return customerRequestProcessor.changeReadFlag(getCustomer(), authorStorage.getWriting(writingId), isRead);
    }

}

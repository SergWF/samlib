package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.factory.EntityFactory;
import my.wf.samlib.core.message.exception.ExtractFieldDataException;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.core.sprider.AuthorWebReader;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.core.storage.CustomerStorage;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class CustomerRequestProcessor {

    public static final String AUTHOR_WAS_ADDED = "author.was.added";
    public static final String AUTHOR_ALREADY_IN_LIST = "author.already.in.list";
    public static final String AUTHOR_WAS_REMOVED = "author.was.removed";
    AuthorProcessor authorProcessor;
    CustomerStorage customerStorage;
    AuthorStorage authorStorage;
    AuthorWebReader webReader;
    MessageProcessor messageProcessor;
    EntityFactory entityFactory;

    public void setAuthorProcessor(AuthorProcessor authorProcessor) {
        this.authorProcessor = authorProcessor;
    }

    public void setCustomerStorage(CustomerStorage customerStorage) {
        this.customerStorage = customerStorage;
    }

    public void setWebReader(AuthorWebReader webReader) {
        this.webReader = webReader;
    }

    public void setMessageProcessor(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    public void setAuthorStorage(AuthorStorage authorStorage) {
        this.authorStorage = authorStorage;
    }

    public void setCustomerFactory(EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
    }

    private <T extends BaseEntity> Boolean isNameMatched(T entity, String namePattern) {
        return null == namePattern || 0 == namePattern.trim().length() || entity.getName().trim().replaceAll("\\s+", " ").toUpperCase().contains(namePattern.toUpperCase());
    }

    public Set<Author> getAuthors(Customer customer, String namePattern, boolean unreadOnly, CustomerOrdering customerOrdering) throws StorageException, ExtractFieldDataException {
        Comparator<Author> comparator = new Comparator<Author>() {
            @Override
            public int compare(Author o1, Author o2) {
                int res = o1.getLastChangedDate().compareTo(o2.getLastChangedDate());
                return 0 != res?res:o1.getName().compareTo(o2.getName());
            }
        };
        Set<Author> result = new TreeSet<>(comparator);
        for(Author author: customer.getAuthors()){
            if(isNameMatched(author, namePattern) && (!unreadOnly || author.getUneadWritngsCount(customer) > 0)){
                result.add(author);
            }
        }
        return result;
    }

    public Set<Writing> getWritings(Customer customer, Author author, String namePattern, boolean unreadOnly, CustomerOrdering customerOrdering) throws ExtractFieldDataException {
        Comparator<Writing> comparator = new Comparator<Writing>() {
            @Override
            public int compare(Writing o1, Writing o2) {
                int res = o1.getLastChangedDate().compareTo(o2.getLastChangedDate());
                return 0 != res?res:o1.getName().compareTo(o2.getName());
            }
        };
        Set<Writing> writings = new TreeSet<>(comparator);
        for(Writing writing: writings){
            if(isNameMatched(author, namePattern) && (!unreadOnly || author.getUneadWritngsCount(customer) > 0)){
                writings.add(writing);
            }
        }
        return writings;
    }

    public Author addAuthor(Customer customer, String authorLink) throws IOException, StorageException {
        return addAuthor(customer, authorProcessor.addNewAuthor(authorLink));
    }

    protected boolean isAuthorInCustomerList(Customer customer, Author author){
        return customer.getAuthors().contains(author);
    }

    public Author addAuthor(Customer customer, Author author) throws StorageException {
        if(isAuthorInCustomerList(customer, author)){
            messageProcessor.addWarnMessage(customer, AUTHOR_ALREADY_IN_LIST, new String[]{author.getName()});
            return author;
        }
        customer.getAuthors().add(author);
        customerStorage.save(customer);
        messageProcessor.addInfoMessage(customer, AUTHOR_WAS_ADDED, new String[]{author.getName()});
        return author;
    }

    public Author removeAuthor(Customer customer, Author author) throws StorageException {
        customer.getAuthors().remove(author);
        customer.getUnreadWritings().removeAll(author.getWritings());
        customerStorage.save(customer);
        messageProcessor.addInfoMessage(customer, AUTHOR_WAS_REMOVED, new String[]{author.getName()});
        return author;
    }

    public Author markAuthorAsRead(Customer customer, Author author) throws StorageException {
        Set<Writing> forDelete = new HashSet<Writing>();
        for (Writing writing : customer.getUnreadWritings()) {
            if (writing.getAuthor().equals(author)) {
                forDelete.add(writing);
            }
        }
        if (!forDelete.isEmpty()) {
            customer.getUnreadWritings().removeAll(forDelete);
        }
        customerStorage.save(customer);
        return author;
    }

    public Writing changeReadFlag(Customer customer, Writing writing, boolean isRead) {
        if(isRead) {
            customer.getUnreadWritings().add(writing);
        }else{
            customer.getUnreadWritings().remove(writing);
        }
        return writing;
    }

}

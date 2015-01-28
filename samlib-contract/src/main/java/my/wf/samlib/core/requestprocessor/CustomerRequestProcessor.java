package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.dataextract.comparator.AuthorComparator;
import my.wf.samlib.core.dataextract.comparator.EntityMatcher;
import my.wf.samlib.core.dataextract.comparator.WritingComparator;
import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.factory.EntityFactory;
import my.wf.samlib.core.message.exception.ExtractFieldDataException;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.core.model.ext.ReadMark;
import my.wf.samlib.core.sprider.AuthorWebReader;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.core.storage.CustomerStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;


public class CustomerRequestProcessor {

    private static final Logger logger = LoggerFactory.getLogger(AuthorProcessor.class);

    public static final String AUTHOR_WAS_ADDED = "author.was.added";
    public static final String AUTHOR_ALREADY_IN_LIST = "author.already.in.list";
    public static final String AUTHOR_WAS_REMOVED = "author.was.removed";
    protected AuthorProcessor authorProcessor;
    protected CustomerStorage customerStorage;
    protected AuthorStorage authorStorage;
    protected AuthorWebReader webReader;
    protected MessageProcessor messageProcessor;
    protected EntityFactory entityFactory;

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

    public Set<Author> getAuthors(Customer customer, String namePattern, boolean unreadOnly, CustomerOrdering customerOrdering) throws StorageException, ExtractFieldDataException {
        logger.debug("read customer's["+customer.getName()+"] authorList");
        return filterAndOrder(customer.getAuthors(), new EntityMatcher<Author>(customer, namePattern, unreadOnly), new AuthorComparator(customerOrdering, customer));
    }

    protected <T extends BaseEntity & ReadMark> Set<T> filterAndOrder(Collection<T> entities, EntityMatcher<T> matcher, Comparator<T> comparator) {
        Set<T> result = new TreeSet<>(comparator);
        for(T entity: entities){
            if(matcher.isMatched(entity)){
                result.add(entity);
            }
        }
        return result;
    }

    public Set<Writing> getWritings(Customer customer, Author author, String namePattern, boolean unreadOnly, CustomerOrdering customerOrdering) throws ExtractFieldDataException {
        logger.debug("read customer's["+customer.getName()+"] writings of  author " + author.getName());
        return filterAndOrder(author.getWritings(), new EntityMatcher<Writing>(customer, namePattern, unreadOnly), new WritingComparator(customerOrdering, customer));
    }

    public Author addAuthor(Customer customer, String authorLink) throws IOException, StorageException {
        logger.info("Add author by link ["+authorLink+"] for customer ["+customer.getName()+"]");
        return addAuthor(customer, authorProcessor.addNewAuthor(authorLink));
    }

    protected boolean isAuthorInCustomerList(Customer customer, Author author){
        return customer.getAuthors().contains(author);
    }

    public Author addAuthor(Customer customer, Author author) throws StorageException {
        logger.info("Add author ["+author.getName() +"] for customer ["+customer.getName()+"]");
        if(isAuthorInCustomerList(customer, author)){
            logger.warn("Author ["+author.getName()+"] is already in the customer's ["+customer.getName()+"] list");
            messageProcessor.addWarnMessage(customer, AUTHOR_ALREADY_IN_LIST, new String[]{author.getName()});
            return author;
        }
        customer.getAuthors().add(author);
        customerStorage.save(customer);
        messageProcessor.addInfoMessage(customer, AUTHOR_WAS_ADDED, new String[]{author.getName()});
        return author;
    }

    public Author removeAuthor(Customer customer, Author author) throws StorageException {
        logger.debug("Remove author [" + author.getName() + "] from customer's [" + customer.getName() + "] list");
        customer.getAuthors().remove(author);
        customer.getUnreadWritings().removeAll(author.getWritings());
        customerStorage.save(customer);
        logger.debug("author [" + author.getName() + "] was removed from customer's [" + customer.getName() + "] list");
        messageProcessor.addInfoMessage(customer, AUTHOR_WAS_REMOVED, new String[]{author.getName()});
        return author;
    }

    public Author markAuthorAsRead(Customer customer, Author author) throws StorageException {
        logger.debug("Author [" + author.getName() + "] was marked as already read by customer [" + customer.getName() + "]");
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
            logger.debug("writing [" + writing.getLink() + "] was removed from customer's [" + customer.getName() + "] unread list");
            customer.getUnreadWritings().remove(writing);
        } else {
            logger.debug("writing [" + writing.getLink() + "] was added to customer's ["+customer.getName()+"] unread list");
            customer.getUnreadWritings().add(writing);
        }
        return writing;
    }

}

package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.factory.FilterFactory;
import my.wf.samlib.core.factory.OrderFactory;
import my.wf.samlib.core.filtering.CustomerFiltering;
import my.wf.samlib.core.ordering.CustomerOrdering;
import my.wf.samlib.core.factory.CustomerFactory;
import my.wf.samlib.core.sprider.AuthorWebReader;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.core.storage.CustomerStorage;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class CustomerRequestProcessor {

    public static final String AUTHOR_WAS_ADDED = "author.was.added";
    public static final String AUTHOR_WAS_REMOVED = "author.was.removed";
    AuthorProcessor authorProcessor;
    CustomerStorage customerStorage;
    AuthorStorage authorStorage;
    AuthorWebReader webReader;
    MessageProcessor messageProcessor;
    FilterFactory filterFactory;
    OrderFactory orderFactory;
    CustomerFactory customerFactory;
    //private CustomerOrdering<Author> defaultOrder = CustomerOrdering.create(Author.class).add("", CustomerOrdering.Direction.DESC);

    public AuthorProcessor getAuthorProcessor() {
        return authorProcessor;
    }

    public void setAuthorProcessor(AuthorProcessor authorProcessor) {
        this.authorProcessor = authorProcessor;
    }

    public CustomerStorage getCustomerStorage() {
        return customerStorage;
    }

    public void setCustomerStorage(CustomerStorage customerStorage) {
        this.customerStorage = customerStorage;
    }

    public AuthorWebReader getWebReader() {
        return webReader;
    }

    public void setWebReader(AuthorWebReader webReader) {
        this.webReader = webReader;
    }

    public MessageProcessor getMessageProcessor() {
        return messageProcessor;
    }

    public void setMessageProcessor(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    public AuthorStorage getAuthorStorage() {
        return authorStorage;
    }

    public void setAuthorStorage(AuthorStorage authorStorage) {
        this.authorStorage = authorStorage;
    }

    public CustomerFactory getCustomerFactory() {
        return customerFactory;
    }

    public void setCustomerFactory(CustomerFactory customerFactory) {
        this.customerFactory = customerFactory;
    }

    public FilterFactory getFilterFactory() {
        return filterFactory;
    }

    public void setFilterFactory(FilterFactory filterFactory) {
        this.filterFactory = filterFactory;
    }

    public OrderFactory getOrderFactory() {
        return orderFactory;
    }

    public void setOrderFactory(OrderFactory orderFactory) {
        this.orderFactory = orderFactory;
    }

    private <T extends BaseEntity> Boolean isNameMatched(T entity, String namePattern) {
        return null == namePattern || 0 == namePattern.trim().length() || entity.getName().trim().replaceAll("\\s+", " ").toUpperCase().contains(namePattern.toUpperCase());
    }

    private Set<Author> getUnreadAuthors(Customer customer) {
        Set<Author> authors = new HashSet<Author>();
        for (Writing w : customer.getUnreadWritings()) {
            authors.add(w.getAuthor());
        }
        return authors;
    }

    public List<Author> getAuthors(Customer customer, String namePattern, boolean unreadOnly, CustomerOrdering<Author> customerOrdering) {
        CustomerFiltering<Author> filter = filterFactory.createFilter(Author.class, customer).add("name", namePattern).add("unread", Boolean.toString(unreadOnly));
        return authorStorage.list(filter, customerOrdering);
    }

    public List<Writing> getWritings(Customer customer, Author author, String namePattern, boolean unreadOnly, CustomerOrdering<Writing> customerOrdering) {
        CustomerFiltering<Writing> filter = filterFactory.createFilter(Writing.class, customer).add("name", namePattern).add("unread", Boolean.toString(unreadOnly));
        return authorStorage.getAuthorsWritings(author, filter, customerOrdering);
    }

    public Author addAuthor(Customer customer, String authorLink) throws IOException {
        return addAuthor(customer, authorProcessor.addNewAuthor(authorLink));
    }

    public Author addAuthor(Customer customer, Author author) {
        customer.getAuthors().add(author);
        customerStorage.save(customer);
        messageProcessor.addInfoMessage(customer, AUTHOR_WAS_ADDED, new String[]{author.getName()});
        return author;
    }

    public Author removeAuthor(Customer customer, Author author) {
        customer.getAuthors().remove(author);
        customerStorage.save(customer);
        messageProcessor.addInfoMessage(customer, AUTHOR_WAS_REMOVED, new String[]{author.getName()});
        return author;
    }

    public Author markAuthorAsRead(Customer customer, Author author) {
        Set<Writing> forDelete = new HashSet<Writing>();
        for (Writing writing : customer.getUnreadWritings()) {
            if (writing.getAuthor().equals(author)) {
                forDelete.add(writing);
            }
        }
        if (!forDelete.isEmpty()) {
            customer.getUnreadWritings().removeAll(forDelete);
        }
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

package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.storage.json.model.AuthorJson;
import my.wf.samlib.storage.json.model.CustomerJson;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class SamlibData {
    protected Set<CustomerJson> customers;
    protected Set<AuthorJson> authors;
    private Map<Class<? extends BaseEntity>, Set<? extends BaseEntity>> map = new HashMap<Class<? extends BaseEntity>, Set<? extends BaseEntity>>();
    protected Long sequence;


    public SamlibData() {
        customers = new HashSet<CustomerJson>();
        authors = new HashSet<AuthorJson>();
        map.put(Customer.class, customers);
        map.put(Author.class, authors);
    }

    public Set<CustomerJson> getCustomers() {
        return customers;
    }

    public Set<AuthorJson> getAuthors() {
        return authors;
    }

    public Set<? extends BaseEntity> getData(Class<?> clazz) {
        return map.get(clazz);
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public <T extends BaseEntity> Long generateNewId() {
        sequence = (null == sequence) ? 1 : ++sequence;
        return sequence;
    }

    public void postJsonReadProcessing() {
        for (CustomerJson customer : customers) {
            postJsonReadCustomer(customer);
        }
        for (AuthorJson author : authors) {
            postJsonReadAuthor(author);
        }
    }

    protected void postJsonReadCustomer(CustomerJson customer) {
        for (Long id : customer.getAuthorIds()) {
            customer.getAuthors().add(findInCollection(id, authors));
        }
        for (Long id : customer.getUnreadWritingIds()) {
            customer.getUnreadWritings().add(findWriting(id, customer.getAuthors()));
        }
    }

    protected void postJsonReadAuthor(AuthorJson author) {
        for (Long id : author.getAuthorIds()) {
            customer.getAuthors().add(findInCollection(id, authors));
        }
    }

    protected <T extends BaseEntity> T findInCollection(Long id, Collection<T> collection){
        for (T entity : collection) {
            if(entity.getId().equals(id)){
                return entity;
            }
        }
        return  null;
    }

    protected Writing findWriting(Long writingId, Collection<Author> authors){

    }
}

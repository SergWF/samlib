package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.storage.json.model.AuthorJson;
import my.wf.samlib.storage.json.model.CustomerJson;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class SamlibData {
    private Set<CustomerJson> customers;
    private Set<AuthorJson> authors;
    private Map<Class<? extends BaseEntity>, Set<?  extends BaseEntity>> map = new HashMap<Class<? extends BaseEntity>, Set<? extends BaseEntity>>();
    private Map<Class<? extends BaseEntity>, Long> sequenceIds = new HashMap<Class<? extends BaseEntity>, Long>();


    public SamlibData() {
        customers = new HashSet<CustomerJson>();
        authors = new HashSet<AuthorJson>();
        map.put(Customer.class, customers);
        map.put(Author.class, authors);
    }

    public Set<CustomerJson> getCustomers(){
        return customers;
    }

    public Set<AuthorJson> getAuthors(){
        return authors;
    }

    public Set<? extends BaseEntity> getData(Class<?> clazz){
        return map.get(clazz);
    }

    public <T extends BaseEntity> Long generateNewId(Class<T> clazz){
        Long id = sequenceIds.get(clazz);
        sequenceIds.put(clazz, ++id);
        return id;
    }
}

package my.wf.samlib.storage.hibernate.storage;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.storage.CustomerStorage;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class CustomerStorageHb extends  BaseStorageHb<Customer> implements CustomerStorage {

    public CustomerStorageHb() {
    }

    public CustomerStorageHb(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Customer getByName(String customerName) {
        try (CloseableSession session = new CloseableSession(getSession(), false)) {
            return (Customer) session.delegate().createCriteria(Author.class).add(Restrictions.eq("name", customerName)).uniqueResult();
        }

    }

    @Override
    public Customer createNewCustomerRecord(Customer customer, String password) {
        return null;
    }

    @Override
    protected Class<Customer> getEntityClass() {
        return Customer.class;
    }
}

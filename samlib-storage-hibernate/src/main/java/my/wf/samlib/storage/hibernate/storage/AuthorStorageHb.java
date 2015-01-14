package my.wf.samlib.storage.hibernate.storage;

import my.wf.samlib.core.dataextract.filtering.CustomerFiltering;
import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.core.storage.AuthorStorage;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Serg on 09.01.2015.
 */
public class AuthorStorageHb extends  BaseStorageHb<Author> implements AuthorStorage {

    public AuthorStorageHb() {
    }
    public AuthorStorageHb(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Author findByLink(String authorLink) throws StorageException {
        try (CloseableSession session = new CloseableSession(getSession(), false)) {
            return (Author) session.delegate().createCriteria(getEntityClass()).add(Restrictions.eq("link", authorLink)).uniqueResult();
        }
    }

    @Override
    public List<Writing> getAuthorsWritings(Author author, CustomerFiltering<Writing> filter, CustomerOrdering<Writing> order) {
        try (CloseableSession session = new CloseableSession(getSession(), false)) {
            Criteria criteria = session.delegate().createCriteria(Writing.class, "writing");
            criteria.createAlias("writing.author", "author");
            criteria.add(Restrictions.eq("author.id", author.getId()));
            criteria = addFilters(criteria, filter);
            criteria = addOrder(criteria, order);
            return criteria.list();
        }
    }

    @Override
    public List<Author> getCustomerList(Customer customer) {
        return null;
    }

    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }
}

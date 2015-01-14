package my.wf.samlib.storage.hibernate.storage;

import my.wf.samlib.core.dataextract.filtering.CustomerFiltering;
import my.wf.samlib.core.dataextract.filtering.FilterItem;
import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.dataextract.ordering.OrderItem;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.storage.Storage;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Serg on 09.01.2015.
 */
public abstract class BaseStorageHb<T extends BaseEntity> implements Storage<T> {

    private SessionFactory sessionFactory;

    protected abstract Class<T> getEntityClass();

    public BaseStorageHb() {
    }

    public BaseStorageHb(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public T get(Long id) {
        try (CloseableSession session = new CloseableSession(getSession(), true)) {
            return (T) session.delegate().get(getEntityClass(), id);
        }
    }

    @Override
    public T save(T entity) throws StorageException {
        try (CloseableSession session = new CloseableSession(getSession(), true)) {
            try {
                return (T) session.delegate().merge(entity);
            } catch (HibernateException e) {
                session.rollback();
                throw new StorageException("Exception on save [class=" + entity.getClass().getSimpleName() + ", id=" + entity.getId() + ", name=" + entity.getName() + "]", e);
            }
        }
    }

    @Override
    public T remove(T entity) throws StorageException {
        try (CloseableSession session = new CloseableSession(getSession(), true)) {
            try {
                session.delegate().delete(entity);
                return entity;
            } catch (HibernateException e) {
                session.rollback();
                throw new StorageException("Exception on delete [class=" + entity.getClass().getSimpleName() + ", id=" + entity.getId() + ", name=" + entity.getName() + "]", e);
            }
        }
    }

    @Override
    public List<T> list(CustomerFiltering<T> filter, CustomerOrdering<T> order) {
        try (CloseableSession session = new CloseableSession(getSession(), false)) {

            Criteria criteria = session.delegate().createCriteria(getEntityClass());
            criteria = addFilters(criteria, filter);
            criteria = addOrder(criteria, order);
            return criteria.list();
        }
    }

    protected <R extends BaseEntity> Criteria addFilters(Criteria criteria, CustomerFiltering<R> filter){
        if (null != filter) {
            for (FilterItem filterItem : filter.getItems()) {
                switch (filterItem.getFieldValueClass().getSimpleName()) {
                    case "String":
                        criteria.add(Restrictions.like(filterItem.getFieldName(), filterItem.getFilterValue()));
                        break;
                    default:
                        break;
                }
            }
        }
        return criteria;
    }

    protected <R extends BaseEntity> Criteria addOrder(Criteria criteria, CustomerOrdering<R> order){
        if (null != order) {
            for (OrderItem orderItem : order.getOrderItems()) {
                if (CustomerOrdering.Direction.DESC.equals(orderItem.getDirection())) {
                    criteria.addOrder(Order.desc(orderItem.getFieldName()));
                } else {
                    criteria.addOrder(Order.asc(orderItem.getFieldName()));
                }
            }
        }
        return criteria;
    }
}

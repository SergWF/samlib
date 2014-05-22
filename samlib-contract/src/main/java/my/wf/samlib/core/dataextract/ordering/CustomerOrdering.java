package my.wf.samlib.core.dataextract.ordering;

import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class CustomerOrdering<T extends BaseEntity> {

    public static enum Direction{
        ASC, DESC
    }
    private List<OrderItem> orderItems = new LinkedList<OrderItem>();
    private Class<T> orderableClass;
    private Customer customer;


    public CustomerOrdering(Class<T> clazz, Customer customer) {
        this.orderableClass = clazz;
        this.customer = customer;
    }

    public CustomerOrdering<T> add(String fieldName, Direction direction) {
        orderItems.add(new OrderItem(fieldName, direction, customer));
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Class<T> getOrderableClass() {
        return orderableClass;
    }
}
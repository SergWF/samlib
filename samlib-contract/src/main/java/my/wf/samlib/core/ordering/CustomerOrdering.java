package my.wf.samlib.core.ordering;

import my.wf.samlib.core.model.extender.Orderable;
import my.wf.samlib.core.model.entity.Customer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class CustomerOrdering<T extends Orderable> {

    public static enum Direction{
        ASC, DESC
    }
    private List<OrderItem<T>> orderItems = new LinkedList<OrderItem<T>>();
    private Class<T> orderableClass;
    private Customer customer;


    public CustomerOrdering(Class<T> clazz, Customer customer) {
        this.orderableClass = clazz;
        this.customer = customer;
    }

    public CustomerOrdering<T> add(String fieldName, Direction direction) {
        orderItems.add(new OrderItem<T>(fieldName, direction, orderableClass));
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
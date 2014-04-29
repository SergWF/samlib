package my.wf.samlib.core.ordering;

import my.wf.samlib.core.model.entity.ComparableItem;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class OrderItem<K> implements ComparableItem<K> {
    private String fieldName;
    private CustomerOrdering.Direction direction;
    private Customer customer;

    public OrderItem(String fieldName, CustomerOrdering.Direction direction, Customer customer) {
        this.fieldName = fieldName;
        this.direction = direction;
        this.customer = customer;
    }
 
    @Override
    public String getFieldName() {
        return fieldName;
    }

    public CustomerOrdering.Direction getDirection() {
        return direction;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }
}

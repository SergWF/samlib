package my.wf.samlib.core.ordering;

import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.ComparableItem;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class OrderItem<T extends BaseEntity> implements ComparableItem<T> {
    private String fieldName;
    private CustomerOrdering.Direction direction;
    private Class<T> processedClass;
    private Customer customer;

    public OrderItem(String fieldName, CustomerOrdering.Direction direction, Class<T> processedClass, Customer customer) {
        this.fieldName = fieldName;
        this.direction = direction;
        this.processedClass = processedClass;
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
    public Class<T> getProcessedClass() {
        return processedClass;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }
}

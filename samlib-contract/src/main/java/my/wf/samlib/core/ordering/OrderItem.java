package my.wf.samlib.core.ordering;

import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.ComparableItem;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class OrderItem<T extends BaseEntity, K> implements ComparableItem<K> {
    private String fieldName;
    private CustomerOrdering.Direction direction;
    private Class<T> processedClass;
    private Customer customer;
    private Class<K> fieldValueClass;

    public OrderItem(String fieldName, CustomerOrdering.Direction direction, Class<T> processedClass, Customer customer, Class<K> fieldValueClazz) {
        this.fieldName = fieldName;
        this.direction = direction;
        this.processedClass = processedClass;
        this.customer = customer;
        this.fieldValueClass = fieldValueClazz;
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

    @Override
    public Class<K> getFieldClassValue() {
        return fieldValueClass;
    }
}

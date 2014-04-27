package my.wf.samlib.core.ordering;

import my.wf.samlib.core.model.extender.Orderable;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class OrderItem<T extends Orderable> {
    private String fieldName;
    private CustomerOrdering.Direction direction;
    private Class<T> orderableClazz;

    public OrderItem(String fieldName, CustomerOrdering.Direction direction, Class<T> orderableClazz) {
        this.fieldName = fieldName;
        this.direction = direction;
        this.orderableClazz = orderableClazz;
    }

    public String getFieldName() {
        return fieldName;
    }

    public CustomerOrdering.Direction getDirection() {
        return direction;
    }

    public Class<T> getOrderableClazz() {
        return orderableClazz;
    }
}

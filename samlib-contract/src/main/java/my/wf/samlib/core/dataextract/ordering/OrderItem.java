package my.wf.samlib.core.dataextract.ordering;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class OrderItem {
    private String fieldName;
    private CustomerOrdering.Direction direction;

    public OrderItem(String fieldName, CustomerOrdering.Direction direction) {
        this.fieldName = fieldName;
        this.direction = direction;
    }
 
    public String getFieldName() {
        return fieldName;
    }

    public CustomerOrdering.Direction getDirection() {
        return direction;
    }
}

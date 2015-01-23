package my.wf.samlib.core.dataextract.ordering;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class CustomerOrdering {

    public static enum Direction{
        ASC, DESC
    }
    private List<OrderItem> orderItems = new ArrayList<>();

    public CustomerOrdering add(String fieldName, Direction direction) {
        orderItems.add(new OrderItem(fieldName, direction));
        return this;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

}
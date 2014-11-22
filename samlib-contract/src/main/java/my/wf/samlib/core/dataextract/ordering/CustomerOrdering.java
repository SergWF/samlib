package my.wf.samlib.core.dataextract.ordering;

import my.wf.samlib.core.dataextract.DataExtractor;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class CustomerOrdering<T extends BaseEntity> implements Comparator<T> {
    private DataExtractor<T> dataExtractor;

    public static enum Direction{
        ASC, DESC
    }
    private List<OrderItem> orderItems = new LinkedList<OrderItem>();
    private Class<T> orderableClass;
    private Customer customer;


    public CustomerOrdering(Class<T> clazz, Customer customer, DataExtractor<T> dataExtractor) {
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

    @Override
    public int compare(T o1, T o2) {
        if(null == o1){
            return -1;
        }
        if(null == o2){
            return 1;
        }
        for(OrderItem item: getOrderItems()){
            Comparable v1 = (Comparable) dataExtractor.getValue(o1, item);
            Comparable v2 = (Comparable) dataExtractor.getValue(o2, item);
            int res = (Direction.ASC == item.getDirection())?v1.compareTo(v2):v2.compareTo(v1);
            if(0 != res){
                return res;
            }
        }
        return 0;
    }

}
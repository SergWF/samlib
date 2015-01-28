package my.wf.samlib.core.dataextract.comparator;

import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.dataextract.ordering.OrderItem;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.ext.HasDate;

import java.util.Comparator;

/**
 * Created by Serg on 28.01.2015.
 */
public abstract class EntityComparator<T extends BaseEntity & HasDate> implements Comparator<T> {
    CustomerOrdering ordering;

    public EntityComparator(CustomerOrdering ordering) {
        this.ordering = ordering;
    }

    @Override
    public int compare(T o1, T o2) {
        if(null == o1){
            return -1;
        }
        if(null ==o2){
            return 1;
        }

        if(null == ordering || ordering.getOrderItems().isEmpty()){
            return  defaultCompare(o1, o2);
        }
        for(OrderItem item: ordering.getOrderItems()){
            int res = compareByItem(item, o1, o2);
            if(res != 0){
                return res;
            }
        }
        return 0;
    }

    private int compareByItem(OrderItem item, T o1, T o2){
        Comparable val1 = getFieldValue(item.getFieldName(), o1);
        Comparable val2 = getFieldValue(item.getFieldName(), o2);
        return val1.compareTo(val2) * item.getDirection().getValue();
    }

    protected abstract Comparable getFieldValue(String fieldName, T o1);

    protected int defaultCompare(T o1, T o2){
        if(null == o1.getLastChangedDate()){
            return -1;
        }
        if(null ==o2.getLastChangedDate()){
            return 1;
        }
        int res = o1.getLastChangedDate().compareTo(o2.getLastChangedDate()) * CustomerOrdering.Direction.DESC.getValue();
        return 0 != res?res:o1.getName().compareTo(o2.getName());
    }
}

package my.wf.samlib.storage.file.storage.ordering;

import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.ordering.CustomerOrdering;
import my.wf.samlib.core.ordering.OrderItem;
import my.wf.samlib.storage.file.storage.DataFieldReader;

import java.util.*;

/**
 * Created by Serg on 27.04.2014.
 */
public class MemoryOrder {
    private DataFieldReader dataFieldReader;

    public DataFieldReader getDataFieldReader() {
        return dataFieldReader;
    }

    public void setDataFieldReader(DataFieldReader dataFieldReader) {
        this.dataFieldReader = dataFieldReader;
    }

    public <T extends BaseEntity> List<T> sort(Collection<T> collection, CustomerOrdering<T> order){
        List<T> list = new ArrayList<T>(collection);
        Collections.sort(list, createComparator(order));
        return list;
    }

    private <T extends BaseEntity> Comparator<T> createComparator(final CustomerOrdering<T> order) {
        return new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                for(OrderItem<T> item: order.getOrderItems()){
                    String val1 = dataFieldReader.getValue(o1, item);
                    String val2 = dataFieldReader.getValue(o2, item);
                    int res = val1.compareTo(val2);
                    if(res != 0){
                        return res;
                    }
                }
                return 0;
            }
        };
    }
}

package my.wf.samlib.storage.json.ordering;

import my.wf.samlib.core.dataextract.DataExtractor;
import my.wf.samlib.core.dataextract.DataExtractorFactory;
import my.wf.samlib.core.message.exception.ExtractFieldDataException;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.dataextract.ordering.OrderItem;

import java.util.*;

/**
 * Created by Serg on 27.04.2014.
 */
public class MemoryOrder {

    private DataExtractorFactory dataExtractorFactory;

    public DataExtractorFactory getDataExtractorFactory() {
        return dataExtractorFactory;
    }

    public void setDataExtractorFactory(DataExtractorFactory dataExtractorFactory) {
        this.dataExtractorFactory = dataExtractorFactory;
    }

    public <T extends BaseEntity> List<T> sort(Collection<T> collection, CustomerOrdering<T> order){
        List<T> list = new ArrayList<T>(collection);
        Collections.sort(list, createComparator(order));
        return list;
    }

    private <T extends BaseEntity> Comparator<T> createComparator(final CustomerOrdering<T> order) {
        final DataExtractor<T> dataExtractor = dataExtractorFactory.getDataExtractor(order.getOrderableClass());
        return new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                for(OrderItem item: order.getOrderItems()){
                    Comparable val1 = null;
                    Comparable val2 = null;
                    try {
                        val1 = (Comparable)dataExtractor.getValue(o1, item);
                        val2 = (Comparable)dataExtractor.getValue(o2, item);
                    } catch (ExtractFieldDataException e) {
                        e.printStackTrace();
                    }
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

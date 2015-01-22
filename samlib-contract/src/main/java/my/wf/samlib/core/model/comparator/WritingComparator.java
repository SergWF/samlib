package my.wf.samlib.core.model.comparator;

import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.model.entity.Writing;

import java.util.Comparator;

/**
 * Created by Serg on 21.01.2015.
 */
public class WritingComparator implements Comparator<Writing> {

    CustomerOrdering ordering;

    public WritingComparator(CustomerOrdering ordering) {
        this.ordering = ordering;
    }

    @Override
    public int compare(Writing o1, Writing o2) {
        return 0;
    }
}

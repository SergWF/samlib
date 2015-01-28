package my.wf.samlib.core.dataextract.comparator;

import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.ext.ReadMark;

/**
 * Created by Serg on 28.01.2015.
 */
public class EntityMatcher<R extends BaseEntity & ReadMark> {
    private String namePattern;
    private Boolean unreadByCustomer;
    private Customer customer;

    public EntityMatcher(Customer customer, String namePattern, Boolean unreadByCustomer) {
        this.customer = customer;
        this.namePattern = namePattern;
        this.unreadByCustomer = unreadByCustomer;
    }

    public boolean isMatched(R entity){
        return (null ==namePattern || entity.getName().contains(namePattern))
                && (null == unreadByCustomer || !unreadByCustomer ||  entity.unreadByCustomer(customer));
    }
}

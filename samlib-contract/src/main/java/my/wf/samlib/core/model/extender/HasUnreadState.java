package my.wf.samlib.core.model.extender;

import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface HasUnreadState {
    Boolean unreadByCustomer(Customer customer);
}

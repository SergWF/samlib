package my.wf.samlib.core.model.ext;

import my.wf.samlib.core.model.entity.Customer;

/**
 * Created by Serg on 28.01.2015.
 */
public interface ReadMark {
    Boolean unreadByCustomer(Customer customer);
}

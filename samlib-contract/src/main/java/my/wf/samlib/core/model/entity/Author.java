package my.wf.samlib.core.model.entity;

import java.beans.Transient;
import java.util.Date;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface Author extends BaseEntity {

    String getLink();
    void setLink(String link);
    Set<Writing> getWritings();

    Set<Customer> getCustomers();
    Date getLastChangedDate();

    Boolean unreadByCustomer(Customer customer);

    @Transient
    int getUneadWritngsCount(Customer customer);
}

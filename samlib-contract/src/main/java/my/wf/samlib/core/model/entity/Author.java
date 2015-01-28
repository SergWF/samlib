package my.wf.samlib.core.model.entity;

import my.wf.samlib.core.model.ext.HasDate;
import my.wf.samlib.core.model.ext.ReadMark;

import java.beans.Transient;
import java.util.Date;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface Author extends BaseEntity, HasDate, ReadMark {

    String getLink();
    void setLink(String link);
    Set<Writing> getWritings();

    Date getLastChangedDate();

    @Transient
    @Override
    Boolean unreadByCustomer(Customer customer);

    @Transient
    int getUneadWritngsCount(Customer customer);
}

package my.wf.samlib.core.model.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface Writing extends BaseEntity {
    String getLink();
    void setLink(String link);

    public Author getAuthor();
    void setAuthor(Author author);


    String getDescription();
    void setDescription(String description);

    String getSize();
    void setSize(String size);

    Date getLastChangedDate();
    void setLastChangedDate(Date lastChangedDate);

    String getGroupName();
    void setGroupName(String groupName);

    Boolean unreadByCustomer(Customer customer);
}

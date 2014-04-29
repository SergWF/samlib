package my.wf.samlib.core.model.entity;

import my.wf.samlib.core.model.dataextract.DataExtractor;
import my.wf.samlib.core.model.dataextract.impl.WritingIsUnreadExtractor;
import my.wf.samlib.core.model.extender.*;
import my.wf.samlib.core.model.extender.Readable;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class Writing extends BaseEntity implements LastChanged, HasLink, HasUnreadState {
    private String link;
    private Author author;
    private String description;
    private String groupName;
    private String size;
    private Date lastChangedDate;

    @Override
    @Readable(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    @Readable(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Readable(name="size")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    @Readable(name="lasChangedDate")
    public Date getLastChangedDate() {
        return lastChangedDate;
    }

    public void setLastChangedDate(Date lastChangedDate) {
        this.lastChangedDate = lastChangedDate;
    }

    @Readable(name="groupName")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    @Override
    @Readable(name="unread", extractorClass = WritingIsUnreadExtractor.class)
    public Boolean unreadByCustomer(Customer customer) {
        return customer.getUnreadWritings().contains(this);
    }
}

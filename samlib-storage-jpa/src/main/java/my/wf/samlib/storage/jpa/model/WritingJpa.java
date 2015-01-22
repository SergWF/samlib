package my.wf.samlib.storage.jpa.model;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Serg on 21.01.2015.
 */
@Entity
@Table(name="writing")
public class WritingJpa implements Writing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "link")
    private String link;
    @Column(name = "description")
    private String description;
    @Column(name = "size")
    private String size;
    @Column(name = "last_change_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChangedDate;
    @Column(name = "group_name")
    private String groupName;
    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name="fk_writing_author"))
    private AuthorJpa author;

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public Author getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(Author author) {
        this.author = (AuthorJpa) author;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public Date getLastChangedDate() {
        return lastChangedDate;
    }

    @Override
    public void setLastChangedDate(Date lastChangedDate) {
        this.lastChangedDate = lastChangedDate;
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public Boolean unreadByCustomer(Customer customer) {
        return customer.getUnreadWritings().contains(this);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}

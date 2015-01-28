package my.wf.samlib.storage.jpa.model;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Serg on 21.01.2015.
 */
@Entity
@Table(name="customer")
public class CustomerJpa implements Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "user_name")
    private String userName;
    @ManyToMany
    @JoinTable(name = "customer_author",
            foreignKey = @ForeignKey(name="fk_customer_author"),
            joinColumns={@JoinColumn(name="customer_id", referencedColumnName = "id")},
            inverseJoinColumns={@JoinColumn(name="author_id", referencedColumnName = "id")}
    )
    private Set<AuthorJpa> authors = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "customer_writing",
            foreignKey = @ForeignKey(name="fk_customer_writing"),
            joinColumns={@JoinColumn(name="customer_id", referencedColumnName = "id")},
            inverseJoinColumns={@JoinColumn(name="writing_id", referencedColumnName = "id")}
    )
    private Set<WritingJpa> unreadWritings = new HashSet<>();


    @Override
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Set<Author> getAuthors() {
        return (Set<Author>)(Set<?>)authors;
    }

    @Override
    public Set<Writing> getUnreadWritings() {
        return (Set<Writing>)(Set<?>)unreadWritings;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

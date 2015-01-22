package my.wf.samlib.storage.jpa.model;

import my.wf.samlib.core.dataextract.helper.AuthorPropertyHelper;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="author")
public class AuthorJpa implements Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "link")
    private String link;
    @OneToMany(mappedBy = "author", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<WritingJpa> writings = new HashSet<>();
    @ManyToMany(mappedBy = "authors")
    private Set<CustomerJpa> customers = new HashSet<>();

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public void setLink(String link) {
this.link = link;
    }

    @Override
    public Set<Writing> getWritings() {
        return (Set<Writing>)(Set<?>)writings;
    }

    @Override
    public Set<Customer> getCustomers() {
        return (Set<Customer>)(Set<?>)customers;
    }

    @Override
    public Date getLastChangedDate() {
        return AuthorPropertyHelper.getAuthorLastChangeDate(this);
    }

    @Override
    public Boolean unreadByCustomer(Customer customer) {
        return 0 < getUneadWritngsCount(customer);
    }

    @Override
    public int getUneadWritngsCount(Customer customer) {
        return AuthorPropertyHelper.getAuthorUnreadWritingsCount(customer, this);
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

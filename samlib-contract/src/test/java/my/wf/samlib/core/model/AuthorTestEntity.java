package my.wf.samlib.core.model;

import my.wf.samlib.core.dataextract.helper.AuthorPropertyHelper;
import my.wf.samlib.core.dataextract.helper.BaseEntityHelper;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Serg on 22.01.2015.
 */
public class AuthorTestEntity implements Author {
    private Long id;
    private String name;
    private String link;
    private Set<WritingTestEntity> writings = new HashSet<>();

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

    @Override
    public boolean equals(Object o) {
        return BaseEntityHelper.getEntityEquals(this, o);
    }

    @Override
    public int hashCode() {
        return BaseEntityHelper.getEntityHash(this);
    }
}

package my.wf.samlib.core.model;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Serg on 22.01.2015.
 */
public class CustomerTestEntity implements Customer {
    private Long id;
    private String name;
    private boolean enabled;
    private Set<AuthorTestEntity> authors = new HashSet<>();
    private Set<WritingTestEntity> unreadWritings = new HashSet<>();


    @Override
    public String getUserName() {
        return name;
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

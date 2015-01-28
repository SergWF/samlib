package my.wf.samlib.service.controller;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Serg on 24.01.2015.
 */
public class Anonymous implements Customer {
    public static final String NAME = "anonymous";
    @Override
    public String getUserName() {
        return NAME;
    }

    @Override
    public Set<Author> getAuthors() {
        return new HashSet<>();
    }

    @Override
    public Set<Writing> getUnreadWritings() {
        return new HashSet<>();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public Long getId() {
        return -1L;
    }

    @Override
    public void setId(Long id) {}

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void setName(String name) { }
}

package my.wf.samlib.core.model.entity;

import my.wf.samlib.core.model.extender.*;
import my.wf.samlib.core.model.extender.Readable;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public abstract class BaseEntity{
    private Long id;
    private String name;


    @Readable
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Readable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

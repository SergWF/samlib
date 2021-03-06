package my.wf.samlib.core.model.entity;

import my.wf.samlib.core.model.extender.Readable;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */

public abstract class BaseEntity{
    private Long id;
    private String name;


    @Readable(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Readable(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;

        BaseEntity that = (BaseEntity) o;
        if(id != null){
            return id.equals(that.id);
        }else{
            return super.equals(o);
        }
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

package my.wf.samlib.core.model.entity;

import my.wf.samlib.core.helper.LastChangedDateHelper;
import my.wf.samlib.core.model.extender.*;
import my.wf.samlib.core.model.extender.Readable;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class Author extends BaseEntity implements LastChanged, HasLink {

    private Set<Writing> writings = new HashSet<Writing>();
    private String link;
    private Date lastChangedDate;

    @Override
    @Readable
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Set<Writing> getWritings(){
        return writings;
    }

    public void setWritings(Set<Writing> writings) {
        this.writings = writings;
    }

    @Override
    @Readable
    public Date getLastChangedDate() {
        return LastChangedDateHelper.getLastChanged(writings);
    }

}

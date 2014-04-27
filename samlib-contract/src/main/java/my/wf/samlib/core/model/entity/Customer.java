package my.wf.samlib.core.model.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class Customer extends BaseEntity {
    private Set<Author> authors = new HashSet<Author>();
    private Set<Writing> unreadWritings = new HashSet<Writing>();
    private boolean enabled;

    public Set<Author> getAuthors(){
        return authors;
    }
    public Set<Writing> getUnreadWritings(){
        return unreadWritings;
    }
    public boolean isEnabled(){
        return enabled;
    }
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
}

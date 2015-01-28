package my.wf.samlib.core.model.entity;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface Customer extends BaseEntity {
    String getUserName();
    Set<Author> getAuthors();
    Set<Writing> getUnreadWritings();
    boolean isEnabled();
    void setEnabled(boolean enabled);
}

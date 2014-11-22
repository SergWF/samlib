package my.wf.samlib.core.dataextract.impl;

import my.wf.samlib.core.dataextract.DataFieldExtractor;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.ComparableItem;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorIsUnreadExtractor implements DataFieldExtractor<Author, Boolean> {
    @Override
    public Boolean extractData(Author entity, ComparableItem<Boolean> item) {
       return entity.unreadByCustomer(item.getCustomer());
    }
}

package my.wf.samlib.core.model.dataextract.impl;

import my.wf.samlib.core.model.dataextract.CustomDataFieldExtractor;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.ComparableItem;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorIsUnreadExtractor implements CustomDataFieldExtractor<Author, Boolean> {
    @Override
    public Boolean extractData(Author entity, ComparableItem<Boolean> item) {
       return entity.unreadByCustomer(item.getCustomer());
    }
}

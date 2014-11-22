package my.wf.samlib.core.dataextract.impl;

import my.wf.samlib.core.dataextract.DataFieldExtractor;
import my.wf.samlib.core.model.entity.ComparableItem;
import my.wf.samlib.core.model.entity.Writing;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class WritingIsUnreadExtractor implements DataFieldExtractor<Writing, Boolean> {
    @Override
    public Boolean extractData(Writing entity, ComparableItem<Boolean> item) {
        return entity.unreadByCustomer(item.getCustomer());
    }
}

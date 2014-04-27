package my.wf.samlib.storage.file.storage.filtering;

import my.wf.samlib.core.filtering.FilterItem;
import my.wf.samlib.core.model.entity.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serg on 27.04.2014.
 */
public class WritingDataExtractor implements DataExtractor{

    private static Map<String, DataFieldExtractor> map = new HashMap<String, DataFieldExtractor>();

    static {
        DataFieldExtractor<Writing> nameExtractor = new DataFieldExtractor<Writing>() {
            @Override
            public String extractData(Writing entity, ComparableItem<Writing> item) {
                return entity.getName();
            }
        };
        DataFieldExtractor<Writing> unreadExtractor = new DataFieldExtractor<Writing>() {
            @Override
            public String extractData(Writing entity, ComparableItem<Writing> item) {
                return String.valueOf(item.getCustomer().getUnreadWritings().contains(entity));
            }
        };
        map.put("name", nameExtractor);
        map.put("unread", unreadExtractor);
    }

    private static boolean checkAuthorUnread(Customer customer, Author author){
        for(Writing writing: customer.getUnreadWritings()){
            if(writing.getAuthor().equals(author)){
                return true;
            }
        }
        return false;
    }

    @Override
    public <T extends BaseEntity> String getValue(T entity, ComparableItem<T> item) {
        return map.get(item.getFieldName()).extractData(entity, item);
    }
}

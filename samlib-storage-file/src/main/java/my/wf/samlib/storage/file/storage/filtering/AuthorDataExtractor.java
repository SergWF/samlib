package my.wf.samlib.storage.file.storage.filtering;

import my.wf.samlib.core.filtering.FilterItem;
import my.wf.samlib.core.model.entity.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serg on 27.04.2014.
 */
public class AuthorDataExtractor implements DataExtractor{

    private static Map<String, DataFieldExtractor> map = new HashMap<String, DataFieldExtractor>();

    static {
        DataFieldExtractor<Author> nameExtractor = new DataFieldExtractor<Author>() {
            @Override
            public String extractData(Author entity, ComparableItem<Author> item) {
                return entity.getName();
            }
        };
        DataFieldExtractor<Author> unreadExtractor = new DataFieldExtractor<Author>() {
            @Override
            public String extractData(Author entity, ComparableItem<Author> item) {
                return String.valueOf(checkAuthorUnread(item.getCustomer(), entity));
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

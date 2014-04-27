package my.wf.samlib.storage.file.storage;

import my.wf.samlib.core.filtering.FilterItem;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.ComparableItem;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.storage.file.storage.filtering.AuthorDataExtractor;
import my.wf.samlib.storage.file.storage.filtering.DataExtractor;
import my.wf.samlib.storage.file.storage.filtering.WritingDataExtractor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serg on 27.04.2014.
 */
public class DataFieldReader {
    private static Map<Class, DataExtractor> map = new HashMap<Class, DataExtractor>();

    static {
        map.put(Author.class, new AuthorDataExtractor());
        map.put(Writing.class, new WritingDataExtractor());
    }

    public <T extends BaseEntity> String getValue(T entity, ComparableItem<T> item) {
        DataExtractor extractor = map.get(entity.getClass());
        return extractor.getValue(entity, item);
    }

}

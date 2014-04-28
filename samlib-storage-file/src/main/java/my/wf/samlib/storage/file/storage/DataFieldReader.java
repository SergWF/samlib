package my.wf.samlib.storage.file.storage;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.ComparableItem;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.storage.file.storage.dataextractor.AuthorDataExtractorImpl;
import my.wf.samlib.core.model.dataextract.DataExtractor;
import my.wf.samlib.storage.file.storage.dataextractor.WritingDataExtractorImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serg on 27.04.2014.
 */
public class DataFieldReader {
    private static Map<Class, DataExtractor> map = new HashMap<Class, DataExtractor>();

    static {
        map.put(Author.class, new AuthorDataExtractorImpl());
        map.put(Writing.class, new WritingDataExtractorImpl());
    }

    public <T extends BaseEntity, K> K getValue(T entity, ComparableItem<K> item) {
        DataExtractor extractor = map.get(entity.getClass());
        return (K)extractor.getValue(entity, item);
    }

}

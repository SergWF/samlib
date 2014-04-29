package my.wf.samlib.storage.file.storage.filtering;

import my.wf.samlib.core.filtering.CustomerFiltering;
import my.wf.samlib.core.filtering.FilterItem;
import my.wf.samlib.core.model.dataextract.DataExtractor;
import my.wf.samlib.core.model.dataextract.DataExtractorFactory;
import my.wf.samlib.core.model.entity.BaseEntity;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class MemoryDataFilter {

    private DataExtractorFactory dataExtractorFactory;

    public DataExtractorFactory getDataExtractorFactory() {
        return dataExtractorFactory;
    }

    public void setDataExtractorFactory(DataExtractorFactory dataExtractorFactory) {
        this.dataExtractorFactory = dataExtractorFactory;
    }


    public <T extends BaseEntity> List<T> doFilter(Collection<T> list, CustomerFiltering<T> filtering) {
        List<T> result = new LinkedList<T>();
        for (T entity : list) {
            if (filterMatched(entity, filtering)) {
                result.add(entity);
            }
        }
        return result;
    }

    private <T extends BaseEntity> boolean filterMatched(T entity, CustomerFiltering<T> filtering) {
        DataExtractor<T> dataExtractor = (DataExtractor<T>) dataExtractorFactory.getDataExtractor(entity.getClass());
        for (FilterItem item : filtering.getItems()) {
            if (! match(dataExtractor.getValue(entity, item), item)) {
                return false;
            }
        }
        return true;
    }

    protected <T extends BaseEntity, K> boolean match(K value, FilterItem<K> item){
        return DataMatcher.match(value, item);
    }
}

package my.wf.samlib.core.factory;

import my.wf.samlib.core.dataextract.DataExtractorFactory;
import my.wf.samlib.core.dataextract.filtering.CustomerFiltering;
import my.wf.samlib.core.dataextract.matcher.MatcherFactory;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface FilterFactory {
    <T extends BaseEntity> CustomerFiltering<T> createFilter(Class<T> clazz, Customer customer);

    void setMatcherFactory(MatcherFactory matcherFactory);

    void setDataExtractorFactory(DataExtractorFactory dataExtractorFactory);
}

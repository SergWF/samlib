package my.wf.samlib.core.factory.impl;

import my.wf.samlib.core.dataextract.DataExtractorFactory;
import my.wf.samlib.core.dataextract.filtering.CustomerFiltering;
import my.wf.samlib.core.dataextract.matcher.MatcherFactory;
import my.wf.samlib.core.factory.FilterFactory;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class DefaultFilterFactory implements FilterFactory {
    private DataExtractorFactory dataExtractorFactory;
    private MatcherFactory matcherFactory;


    @Override
    public <T extends BaseEntity> CustomerFiltering<T> createFilter(Class<T> clazz, Customer customer) {
        return new CustomerFiltering<T>(clazz, customer, dataExtractorFactory.getDataExtractor(clazz), matcherFactory);
    }

    @Override
    public void setMatcherFactory(MatcherFactory matcherFactory) {
        this.matcherFactory = matcherFactory;
    }

    @Override
    public void setDataExtractorFactory(DataExtractorFactory dataExtractorFactory) {
        this.dataExtractorFactory = dataExtractorFactory;
    }
}

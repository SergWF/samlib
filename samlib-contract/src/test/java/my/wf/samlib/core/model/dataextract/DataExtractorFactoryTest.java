package my.wf.samlib.core.model.dataextract;

import my.wf.samlib.core.model.entity.ComparableItem;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class DataExtractorFactoryTest {
    @Test
    public void testGetDataExtractor() throws Exception {
        DataExtractorFactory factory = new DataExtractorFactory();
        Customer customer = new Customer();
        DataExtractor<Writing> writingDataExtractor = factory.getDataExtractor(Writing.class, customer);
        Writing w = new Writing();
        w.setLink("htp://link1");
        ComparableItem<String> item = new ComparableItem<String>() {
            @Override
            public String getFieldName() {
                return "link";
            }

            @Override
            public Customer getCustomer() {
                return null;
            }

            @Override
            public Class<String> getFieldClassValue() {
                return String.class;
            }
        };
        System.out.println(writingDataExtractor.getValue(w, item));
    }
}

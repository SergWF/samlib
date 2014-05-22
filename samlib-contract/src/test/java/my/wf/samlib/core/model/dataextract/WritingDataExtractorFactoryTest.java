package my.wf.samlib.core.model.dataextract;

import my.wf.samlib.core.EntityCreator;
import my.wf.samlib.core.dataextract.DataExtractor;
import my.wf.samlib.core.dataextract.DataExtractorFactory;
import my.wf.samlib.core.model.entity.ComparableItem;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
@RunWith(Parameterized.class)
public class WritingDataExtractorFactoryTest {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    public static final Long EXPECTED_ID = 10L;


    private String testName;
    private String fieldName;
    private Object expectedValue;
    private Boolean writingIsUnread;

    public WritingDataExtractorFactoryTest(String testName, String fieldName, Object expectedValue, Boolean writingIsUnread) {
        this.testName = testName;
        this.fieldName = fieldName;
        this.expectedValue = expectedValue;
        this.writingIsUnread = writingIsUnread;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws ParseException {
        Object[][] data = new Object[][]{
                {"Read Id", "id", EXPECTED_ID, false}
                , {"Read Link", "link", EntityCreator.getExpectedWritingLink(EXPECTED_ID), false}
                , {"Read Name", "name", EntityCreator.getExpectedWritingName(EXPECTED_ID), false}
                , {"Read Description", "description", EntityCreator.getExpectedWritingDescr(EXPECTED_ID), false}
                , {"Read LastChangedDate", "lasChangedDate", EntityCreator.getExpectedWritingDate(EXPECTED_ID), false}
                , {"Read isUnread  true", "unread", true, true}
                , {"Read isUnread false", "unread", false, false}
                , {"Read size", "size", EntityCreator.getExpectedWritingSize(EXPECTED_ID), false}
                , {"Read group", "groupName", EntityCreator.getExpectedWritingGroup(EXPECTED_ID), false}
        };
        return Arrays.asList(data);
    }


    @Test
    public void testGetDataExtractor() throws Exception {
        DataExtractorFactory factory = new DataExtractorFactory();
        Writing writing = EntityCreator.createWriting(EXPECTED_ID);
        Customer customer = EntityCreator.createCustomer();
        if (writingIsUnread) {
            customer.getUnreadWritings().add(writing);
        }
        ComparableItem<String> item = EntityCreator.createItem(fieldName, expectedValue.getClass(), customer);
        DataExtractor<Writing> writingDataExtractor = factory.getDataExtractor(Writing.class);
        if (writingIsUnread) {
            item.getCustomer().getUnreadWritings().add(writing);
        }
        Object actual = writingDataExtractor.getValue(writing, item);
        assertEquals(testName + ": expected " + expectedValue + " but actual is " + actual, expectedValue, actual);
    }
}

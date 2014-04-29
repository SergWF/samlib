package my.wf.samlib.core.model.dataextract;

import my.wf.samlib.core.EntityCreator;
import my.wf.samlib.core.model.entity.Author;
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
public class AuthorDataExtractorFactoryTest {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    public static final Long EXPECTED_ID = 1L;
    public static final Long EXPECTED_WRITING_ID = 10L;


    private String testName;
    private String fieldName;
    private Object expectedValue;
    private Boolean authorIsUnread;

    public AuthorDataExtractorFactoryTest(String testName, String fieldName, Object expectedValue, Boolean authorIsUnread) {
        this.testName = testName;
        this.fieldName = fieldName;
        this.expectedValue = expectedValue;
        this.authorIsUnread = authorIsUnread;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws ParseException {
        Object[][] data = new Object[][]{
                {"Read Id", "id", EXPECTED_ID, false}
                , {"Read Link", "link", EntityCreator.getExpectedAuthorLink(EXPECTED_ID), false}
                , {"Read Name", "name", EntityCreator.getExpectedAuthorName(EXPECTED_ID), false}
                , {"Read LastChangedDate", "lastChangedDate", EntityCreator.getExpectedWritingDate(EXPECTED_WRITING_ID + 20), false}
                , {"Read isUnread  true", "unread", true, true}
                , {"Read isUnread false", "unread", false, false}
        };
        return Arrays.asList(data);
    }


    @Test
    public void testGetDataExtractor() throws Exception {
        DataExtractorFactory factory = new DataExtractorFactory();
        Writing writing1 = EntityCreator.createWriting(EXPECTED_WRITING_ID);
        Writing writing2 = EntityCreator.createWriting(EXPECTED_WRITING_ID + 10);
        Writing writing3 = EntityCreator.createWriting(EXPECTED_WRITING_ID + 20);
        Author author = EntityCreator.createAuthor(1L, writing1, writing2, writing3);
        Customer customer = EntityCreator.createCustomer();
        if (authorIsUnread) {
            customer.getUnreadWritings().add(writing1);
        }
        ComparableItem<String> item = EntityCreator.createItem(fieldName, expectedValue.getClass(), customer);
        DataExtractor<Author> authorDataExtractor = factory.getDataExtractor(Author.class);
        Object actual = authorDataExtractor.getValue(author, item);
        assertEquals(testName + ": expected " + expectedValue + " but actual is " + actual, expectedValue, actual);
    }
}

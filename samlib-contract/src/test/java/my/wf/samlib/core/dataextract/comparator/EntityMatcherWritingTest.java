package my.wf.samlib.core.dataextract.comparator;

import my.wf.samlib.core.EntityCreator;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class EntityMatcherWritingTest {
    EntityMatcher<Writing> writingMatcher;
    Customer customer;
    Author author;
    Writing writing;

    private String testName;
    private String writingName;
    private Boolean unreadValue;
    private Boolean expectedResult;
    private Boolean hasUnread;


    private static final String MATCHED_NAME="matched name";

    public EntityMatcherWritingTest(String testName, String writingName, Boolean unreadValue, Boolean hasUnread, Boolean expectedResult) {
        this.testName = testName;
        this.writingName = writingName;
        this.unreadValue = unreadValue;
        this.expectedResult = expectedResult;
        this.hasUnread = hasUnread;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws ParseException {
        Object[][] data = new Object[][]{
                {"Matched: Same, noUnread", "matched name", false, false, true},
                {"Matched: Include, noUnread", "some matched name value", false, false, true},
                {"Matched: Start, noUnread", "matched name value", false, false, true},
                {"Matched: End, noUnread", "some matched name", false,false,  true},
                {"Matched: Unread, notChecking", "matched name", false, true, true},
                {"Matched: Same, Unread", "matched name", true, true, true},

                {"Not Matched: noUnread", "some name", false, false, false},
                {"Not Matched: Same, Unread", "matched name", true, false, false},
        };
        return Arrays.asList(data);
    }


    @Before
    public void setUp() throws Exception {
        customer = EntityCreator.createCustomer(1);
        author = EntityCreator.createAuthor(1, 3);
        customer.getAuthors().add(author);
        writing = author.getWritings().iterator().next();
    }

    @Test
    public void test() throws Exception {
        writing.setName(writingName);
        if(hasUnread){
            customer.getUnreadWritings().add(writing);
        }
        writingMatcher = new EntityMatcher<Writing>(customer, MATCHED_NAME, unreadValue);
        assertEquals(testName, expectedResult, writingMatcher.isMatched(writing));
    }
}
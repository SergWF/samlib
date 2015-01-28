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

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class EntityMatcherAuthorTest {
    EntityMatcher<Author> authorMatcher;
    EntityMatcher<Writing> writingMatcher;
    Customer customer;
    Author a1;
    Author a2;
    Author a3;
    Author a4;
    Author a5;

    private String testName;
    private String authorName;
    private Boolean unreadValue;
    private Boolean expectedResult;
    private Boolean hasUnread;


    private static final String MATCHED_NAME="matched name";

    public EntityMatcherAuthorTest(String testName, String authorName, Boolean unreadValue, Boolean hasUnread, Boolean expectedResult) {
        this.testName = testName;
        this.authorName = authorName;
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
        a1 = EntityCreator.createAuthor(1, 3);
        a2 = EntityCreator.createAuthor(2, 3);
        a3 = EntityCreator.createAuthor(3, 3);
        a4 = EntityCreator.createAuthor(3, 3);
        a5 = EntityCreator.createAuthor(3, 3);
        customer.getAuthors().add(a1);
        customer.getAuthors().add(a3);
        customer.getAuthors().add(a5);
    }

    @Test
    public void test() throws Exception {
        a1.setName(authorName);
        if(hasUnread){
            customer.getUnreadWritings().add(a1.getWritings().iterator().next());
        }
        authorMatcher = new EntityMatcher<Author>(customer, MATCHED_NAME, unreadValue);
        assertEquals(testName, expectedResult, authorMatcher.isMatched(a1));
    }
}
package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.ordering.CustomerOrdering;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Writing;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import java.util.*;

import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
@RunWith(Parameterized.class)
public class CustomerRequestProcessorWritingsTest extends BaseCustomerRequestProcessorTest {

    private String testName;
    private String namePattern;
    private Boolean unreadOnly;
    private CustomerOrdering orderBy;
    private  long[] expectedIds;
    Author author1;
    Author author2;
    Set<Writing> writings1 = new HashSet<Writing>();
    Set<Writing> writings2 = new HashSet<Writing>();


    public CustomerRequestProcessorWritingsTest(String testName, String namePattern, Boolean unreadOnly, CustomerOrdering orderBy, long[] expectedIds) {
        this.testName = testName;
        this.namePattern = namePattern;
        this.unreadOnly = unreadOnly;
        this.orderBy = orderBy;
        this.expectedIds = expectedIds;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {"SimpleAll", null, false, null, new long[]{15L, 12L, 11L, 13L, 14L}},
                {"SimpleUnread", null, true, null, new long[]{12L, 11L, 14L}},
                {"FilterAll", "_writing", false, null, new long[]{15L, 12L, 14L}},
                {"FilterUnread", "_writing", true, null, new long[]{12L, 14L}}
        };
        return Arrays.asList(data);
    }
    @Before
    public void setUp(){
        initBaseStructure();
        initCustomer();
        author1 = createAuthor(1L, "http://link1", "Author1", new Date(), false);
        author2 = createAuthor(2L, "http://link2", "Author2", new Date(), false);
        Mockito.when(author1.getWritings()).thenReturn(writings1);
        Mockito.when(author2.getWritings()).thenReturn(writings2);
        customer.getAuthors().add(author1);
        customer.getAuthors().add(author2);

        author1.getWritings().add(createWriting(11L, "http://link11", "writing11", new Date(1397030530000L), author1));
        author1.getWritings().add(createWriting(12L, "http://link12", "_writing12", new Date(1397030530100L), author1));
        author1.getWritings().add(createWriting(13L, "http://link13", "writing13", new Date(1397030530000L), author1));
        author1.getWritings().add(createWriting(14L, "http://link14", "_writing14", new Date(1397030530000L), author1));
        author1.getWritings().add(createWriting(15L, "http://link15", "_writing15", new Date(1397030530400L), author1));

        author2.getWritings().add(createWriting(21L, "http://link21", "writing21", new Date(1397030539000L), author2));
        author2.getWritings().add(createWriting(22L, "http://link22", "_writing22", new Date(1397030530400L), author2));
        author2.getWritings().add(createWriting(23L, "http://link23", "writing23", new Date(1397030530400L), author2));
        author2.getWritings().add(createWriting(24L, "http://link24", "_writing24", new Date(1397030530400L), author2));
        author2.getWritings().add(createWriting(25L, "http://link25", "writing25", new Date(1397030530400L), author2));
        author2.getWritings().add(createWriting(26L, "http://link26", "writing26", new Date(1397030539000L), author2));

        customer.getUnreadWritings().add(getEntityById(11L, author1.getWritings()));
        customer.getUnreadWritings().add(getEntityById(12L, author1.getWritings()));
        customer.getUnreadWritings().add(getEntityById(14L, author1.getWritings()));
        customer.getUnreadWritings().add(getEntityById(24L, author2.getWritings()));
        customer.getUnreadWritings().add(getEntityById(26L, author2.getWritings()));
    }

    @Test
    public void testGetWritings() throws Exception {
        List<Writing> writings = getCustomerRequestProcessor().getWritings(customer, author1, namePattern, unreadOnly, orderBy);
        assertTrue(check(writings, expectedIds));
    }
    private boolean check(List<Writing> actual, long[] expectedIds){
        if(expectedIds.length != actual.size()){
            System.out.println(testName + ": Sizes do not matched: actual=" + actual.size() + " expected=" + expectedIds.length);
            return false;
        }
        for(int i = 0; i< expectedIds.length; i++){
            if(!actual.get(i).getId().equals(expectedIds[i])){
                System.out.println(testName + ": " +i + ": Expected " + expectedIds[i]+ " actual=" + actual.get(i).getId());
                return false;
            }
        }
        return true;
    }
}

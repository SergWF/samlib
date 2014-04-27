package my.wf.samlib.core.requestprocessor;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
@RunWith(value = Parameterized.class)
public class CustomerRequestProcessorAuthorCustomerOrderingTest extends BaseCustomerRequestProcessorTest {
/*
    private String testName;
    private String namePattern;
    private OrderBy orderBy;
    private List<Long> ids;

    private static Date date1 = new Date(1397030530000L);
    private static Date date2 = new Date(1397030530100L);
    private static Date date3 = new Date(1397030530200L);
    private static Date date4 = new Date(1397030530300L);

    public CustomerRequestProcessorAuthorCustomerOrderingTest(String testName, String namePattern, OrderBy orderBy, List<Long> ids) {
        this.testName = testName;
        this.orderBy = orderBy;
        this.namePattern = namePattern;
        this.ids = ids;
    }

    private void createAuthors(){
        customer.getAuthors().add(createAuthor(1L, "http://link1", "NAME3", date3, false));
        customer.getAuthors().add(createAuthor(2L, "http://link2", "NAME1", date2, true));
        customer.getAuthors().add(createAuthor(3L, "http://link3", "NAME2", date1, true));
        customer.getAuthors().add(createAuthor(4L, "http://link4", "AME4", date4, false));
    }

    @Parameters
    public static Collection<Object[]> data() {
        OrderBy byNameAsc = OrderBy.create(BaseEntity.FIELD.NAME, OrderDirection.ASC);
        OrderBy byNameDesc = OrderBy.create(BaseEntity.FIELD.NAME, OrderDirection.DESC);
        OrderBy byDateAsc = OrderBy.create(BaseEntity.FIELD.LAST_CHANGED, OrderDirection.ASC);
        OrderBy byDateDesc = OrderBy.create(BaseEntity.FIELD.LAST_CHANGED, OrderDirection.DESC);
        OrderBy byUnreadAsc = OrderBy.create(BaseEntity.FIELD.IS_UNREAD, OrderDirection.ASC);
        OrderBy byUnreadDesc = OrderBy.create(BaseEntity.FIELD.IS_UNREAD, OrderDirection.DESC);

        Object[][] data = new Object[][]{
                  {"BY NAME ASC", null, byNameAsc, Arrays.asList(4L, 2L, 3L, 1L)}
                , {"BY NAME DESC", null, byNameDesc, Arrays.asList(1L, 3L, 2L, 4L)}
                , {"BY DATE ASC", null, byDateAsc, Arrays.asList(3L, 2L, 1L, 4L)}
                , {"BY DATE DESC", null, byDateDesc, Arrays.asList(4L, 1L, 2L, 3L)}
                , {"BY UNREAD ASC", null, byUnreadAsc, Arrays.asList(1L, 4L, 2L, 3L)}
                , {"BY UNREAD DESC", null, byUnreadDesc, Arrays.asList(2L, 3L, 1L, 4L)}

                , {"BY NAME ASC WITH EMPTY FILTER ", "", byNameAsc, Arrays.asList(4L, 2L, 3L, 1L)}
                , {"BY NAME ASC WITH SPACED FILTER ", "   ", byNameAsc, Arrays.asList(4L, 2L, 3L, 1L)}

                , {"BY NAME ASC WITH FILTER 'NAME'", "NAME", byNameAsc, Arrays.asList(2L, 3L, 1L)}
                , {"BY NAME ASC WITH FILTER 'NOT'", "NOT", byNameAsc, new ArrayList<Long>()}
        };
        return Arrays.asList(data);
    }

    @Before
    public void setUp(){
        initBaseStructure();
        initCustomer();
    }

    @Test
    public void testOrderBy() {
        createAuthors();
        List<Author> actual = getCustomerRequestProcessor().getAuthors(customer, namePattern, false, orderBy);
        assertTrue(testName, checkResult(actual, ids, testName));
    }

    private boolean checkResult(List<Author> authors, List<Long> ids, String testName){
        if(authors.size() != ids.size()){
            System.out.println(testName + ": Sizes do not matched!");
            return false;
        }
        for(int i = 0; i< ids.size(); i++){
            if(!authors.get(i).getId().equals(ids.get(i))){
                System.out.println(testName + ": " +i + ": Expected " + ids.get(i)+ " actual=" + authors.get(i).getId());
                return false;
            }
        }
        return true;
    }

*/
}

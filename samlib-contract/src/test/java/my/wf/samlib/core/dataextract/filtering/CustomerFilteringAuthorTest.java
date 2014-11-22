package my.wf.samlib.core.dataextract.filtering;

import my.wf.samlib.core.EntityCreator;
import my.wf.samlib.core.dataextract.DataExtractorFactory;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CustomerFilteringAuthorTest {

    private static Author author;
    private static Customer customer;

    private String testName;
    private String[] fieldNames;
    private Object[] fieldValues;
    private Boolean expected;


    static {

        Writing w1 = EntityCreator.createWriting(100L);
        Writing w2 = EntityCreator.createWriting(200L);
        Writing w3 = EntityCreator.createWriting(300L);
        Writing w4 = EntityCreator.createWriting(400L);
        Writing w5 = EntityCreator.createWriting(500L);
        Writing w6 = EntityCreator.createWriting(600L);

        author = EntityCreator.createAuthor(10L,w1,w2,w3,w4,w5,w6);
        customer = EntityCreator.createCustomer();
        customer.getAuthors().add(author);
        customer.getUnreadWritings().add(w2);
        customer.getUnreadWritings().add(w3);

    }

    public CustomerFilteringAuthorTest(String testName, String[] fieldNames, Object[] fieldValues, Boolean expected) {
        this.testName = testName;
        this.fieldNames = fieldNames;
        this.fieldValues = fieldValues;
        this.expected = expected;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() throws ParseException {
        Object[][] data = new Object[][]{
                {"Found by id", new String[]{"id"}, new Object[]{author.getId()}, true}
                , {"Found by link", new String[]{"link"}, new Object[]{author.getLink()}, true}
                , {"Found by name", new String[]{"name"}, new Object[]{author.getName()}, true}
                , {"Found by lastChangedDate", new String[]{"lastChangedDate"}, new Object[]{author.getLastChangedDate()}, true}
                , {"Found by unread", new String[]{"unread"}, new Object[]{author.unreadByCustomer(customer)}, true}

                , {"Not Found by id", new String[]{"id"}, new Object[]{991L}, false}
                , {"Not Found by link", new String[]{"link"}, new Object[]{"wrong link"}, false}
                , {"Not Found by name", new String[]{"name"}, new Object[]{"wrong name"}, false}
                , {"Not Found by lastChangedDate", new String[]{"lastChangedDate"}, new Object[]{new Date()}, false}
                , {"Not Found by unread", new String[]{"unread"}, new Object[]{false}, false}
        };
        return Arrays.asList(data);
    }

    @Test
    public void doTest(){
        CustomerFiltering<Author>  customerFiltering = new CustomerFiltering<>(Author.class, customer,  new DataExtractorFactory().getDataExtractor(Author.class));
        for(int i = 0; i< fieldNames.length; i++){
            customerFiltering.add(fieldNames[i], fieldValues[i]);
        }
        Boolean actual = customerFiltering.matched(author);
        assertTrue(testName + ": expected " + expected + " but actual is " + actual, expected == actual);
    }
}
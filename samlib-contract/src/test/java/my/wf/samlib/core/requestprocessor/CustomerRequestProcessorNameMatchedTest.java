package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.BaseEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
@RunWith(value = Parameterized.class)
public class CustomerRequestProcessorNameMatchedTest extends BaseCustomerRequestProcessorTest {
/*
    Author author = createAuthor(1L, "http://link1",  "John Doe", new Date(), false);
    private String patterName;
    private Boolean matched;

    public CustomerRequestProcessorNameMatchedTest(String patterName, Boolean matched) {
        this.patterName = patterName;
        this.matched = matched;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {"John", true},
                {"JOHN DOE", true},
                {"jOhN", true},
                {"john doe", true},
                {"doe", true},
                {"", true},
                {"\t", true},
                {"NOT", false},
                {"John Doe With Extra", false},
                {"Extra with John Doe", false}
        };
        return Arrays.asList(data);
    }

    @Before
    public void setUp(){
        initBaseStructure();
        initCustomer();
    }

    @Test
    public void testIsNameMatched() throws Exception {
        CustomerRequestProcessor customerRequestProcessor = getCustomerRequestProcessor();
        Method isNameMatched = CustomerRequestProcessor.class.getDeclaredMethod("isNameMatched", BaseEntity.class, String.class);
        isNameMatched.setAccessible(true);
        Object result = isNameMatched.invoke(customerRequestProcessor, author, patterName);
        String message = String.format("Name must %s be matched with [%s]", matched ? "" : "NOT", patterName);
        assertEquals(message, matched, (Boolean) result);

    }
*/
}

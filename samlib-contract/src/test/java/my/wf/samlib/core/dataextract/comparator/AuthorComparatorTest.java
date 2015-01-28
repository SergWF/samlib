package my.wf.samlib.core.dataextract.comparator;

import my.wf.samlib.core.EntityCreator;
import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class AuthorComparatorTest {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date FIRST_DATE;
    public static Date SECOND_DATE;
    public static Date THIRD_DATE;

    static {
        try {
            FIRST_DATE  = sdf.parse("2015-01-01 11:00:00");
            SECOND_DATE = sdf.parse("2015-01-01 11:00:00");
            THIRD_DATE  = sdf.parse("2015-01-01 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static final String FIRST_AUTHOR = "First Author";
    public static final String SECOND_AUTHOR = "Second Author";
    public static final String THIRD_AUTHOR = "Third Author";
    AuthorComparator comparator;

    Customer customer;
    Author a1;
    Author a2;
    Author a3;

    @Before
    public void setUp() throws Exception {
        customer = EntityCreator.createCustomer(1);
        a1 = EntityCreator.createAuthor(1, 3);
        a2 = EntityCreator.createAuthor(2, 3);
        a3 = EntityCreator.createAuthor(3, 3);

        a1.setName(FIRST_AUTHOR);
        a2.setName(SECOND_AUTHOR);
        a3.setName(THIRD_AUTHOR);

        a1.getWritings().iterator().next().setLastChangedDate(FIRST_DATE);
        a2.getWritings().iterator().next().setLastChangedDate(SECOND_DATE);
        a3.getWritings().iterator().next().setLastChangedDate(THIRD_DATE);
    }

    @Test
    public void testGetFieldValue(){
        comparator = new AuthorComparator(null, customer);
        assertEquals(FIRST_AUTHOR, comparator.getFieldValue("name", a1));
        assertEquals(FIRST_DATE, comparator.getFieldValue("lastChangedDate", a1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFieldValueWrongField(){
        new AuthorComparator(null, customer).getFieldValue("wrong", a1);
    }

    @Test
    public void testGetCompareWithEmptyOrdering() throws Exception {
        assertTrue(0 > new AuthorComparator(new CustomerOrdering(), customer).compare(a1, a2));
    }

    @Test
    public void testGetCompareWithNullOrdering() throws Exception {
        assertTrue(0 > new AuthorComparator(null, customer).compare(a1, a2));
    }

    @Test
    public void testGetCompareOrderedByNameDesc() throws Exception {
        CustomerOrdering ordering = new CustomerOrdering().add("name", CustomerOrdering.Direction.DESC);
        assertTrue(0 < new AuthorComparator(ordering, customer).compare(a1, a2));
    }

    @Test
    public void testGetCompareOrderedByNameAsc() throws Exception {
        CustomerOrdering ordering = new CustomerOrdering().add("name", CustomerOrdering.Direction.ASC);
        assertTrue(0 > new AuthorComparator(ordering, customer).compare(a1, a2));
    }

    @Test
    public void testGetCompareOrderedByLastDateDesc() throws Exception {
        CustomerOrdering ordering = new CustomerOrdering().add("lastChangedDate", CustomerOrdering.Direction.DESC);
        assertTrue(0 > new AuthorComparator(ordering, customer).compare(a2, a3));
    }

}

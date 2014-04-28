package my.wf.samlib.storage.file.storage.filtering;

import my.wf.samlib.core.model.entity.ComparableItem;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.storage.file.storage.dataextractor.WritingDataExtractorImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
@RunWith(Parameterized.class)
public class WritingDataExtractorTest {

    private static String NAME="name";
    private static String DESCRIPTION="description";
    private static String LINK="link";
    private static String LAST_CHANGED="lastChangedDate";
    private static String ID="id";
    private static String GROUPNAME="groupName";
    private static String SIZE="size";
    private static String UNREAD="unread";

    private static Map<String, Object> map = new HashMap<String, Object>();
    static {
        map.put(NAME, "writing1");
        map.put(DESCRIPTION, "description1");
        map.put(LINK, "linhk1");
        map.put(LAST_CHANGED, new Date());
        map.put(ID, 100L);
        map.put(GROUPNAME, "groupName1");
        map.put(SIZE, "100k");
    }

    private String testName;
    private Object expected;
    private Boolean inUnreadList;
    private String fieldName;
    private Class fieldValueClass;


    private static class ComparableItemImpl<K> implements ComparableItem<K>{

        private String fieldName;
        private Customer customer;
        private Class<K> fieldValueClass;

        private ComparableItemImpl(String fieldName, Customer customer, Class<K> fieldValueClass) {
            this.fieldName = fieldName;
            this.customer = customer;
            this.fieldValueClass = fieldValueClass;
        }

        @Override
        public String getFieldName() {
            return fieldName;
        }

        @Override
        public Customer getCustomer() {
            return customer;
        }

        @Override
        public Class<K> getFieldClassValue() {
            return fieldValueClass;
        }
    }

    public WritingDataExtractorTest(String testName, Object expected, Boolean inUnreadList, String fieldName, Class fieldValueClass) {
        this.testName = testName;
        this.expected = expected;
        this.inUnreadList = inUnreadList;
        this.fieldName = fieldName;
        this.fieldValueClass = fieldValueClass;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {"Read name", map.get(NAME), false, NAME, String.class}
                , {"Read unread true", true, true, UNREAD, Boolean.class}
                , {"Read unread false", false, false, UNREAD, Boolean.class}
                , {"Read description",  map.get(DESCRIPTION), false, DESCRIPTION, String.class}
                , {"Read link",  map.get(LINK), false, LINK, String.class}
                , {"Read Last Changed",  map.get(LAST_CHANGED), false, LAST_CHANGED, Date.class}
                , {"Read Id",  map.get(ID), false, ID, Long.class}
                , {"Read groupName",  map.get(GROUPNAME), false, GROUPNAME, String.class}
        };
        return Arrays.asList(data);
    }

    private <K> ComparableItem<K> createComparableItem(String fieldName, Customer customer, Class<K> clazz) {
        ComparableItem< K> item = new ComparableItemImpl(fieldName, customer, clazz);
        return item;
    }

    private static Writing createWriting(Customer unreadByCustomer) {
        Writing writing = new Writing();
        writing.setName((String)map.get(NAME));
        writing.setLink((String)map.get(LINK));
        writing.setDescription((String)map.get(DESCRIPTION));
        writing.setGroupName((String)map.get(GROUPNAME));
        writing.setSize((String)map.get(SIZE));
        writing.setId((Long) map.get(ID));
        writing.setLastChangedDate((Date) map.get(LAST_CHANGED));
        if(null != unreadByCustomer){
            unreadByCustomer.getUnreadWritings().add(writing);
        }
        return writing;
    }

    @Test
    public void getValueTest(){
        WritingDataExtractorImpl extractor = new WritingDataExtractorImpl();
        Customer customer =  new Customer();
        Writing writing = createWriting(inUnreadList?customer:null);
        ComparableItem<?> comparableItem = createComparableItem(fieldName, customer, fieldValueClass);
        Object actual = extractor.getValue(writing, comparableItem);
        assertEquals(testName + ": expected " + expected + " but actual is " + actual, expected, actual);
    }

}

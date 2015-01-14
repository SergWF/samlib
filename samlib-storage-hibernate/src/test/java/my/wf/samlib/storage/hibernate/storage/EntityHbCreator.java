package my.wf.samlib.storage.hibernate.storage;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.ComparableItem;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class EntityHbCreator {
    public static Author createAuthorFull(Long authorIndex, int writingCount){
        Author author = new Author();
        author.setName(getExpectedAuthorName(authorIndex));
        author.setLink(getExpectedAuthorLink(authorIndex));
        for(int i = 0; i < writingCount; i++){
            Writing w = createWriting(author, authorIndex * 100+i);
            author.getWritings().add(w);
        }
        return author;
    }

    public static String getExpectedAuthorLink(Long authorId) {
        return "http://author_link" + authorId;
    }

    public static String getExpectedAuthorName(Long authorId) {
        return "Author name" + authorId;
    }

    public static String getExpectedCustomerName(Long customerId) {
        return "Customer name" + customerId;
    }

    public static Writing createWriting(Author author, Long writingIndex){
        Writing writing = new Writing();
        writing.setAuthor(author);
        writing.setName(getExpectedWritingName(writingIndex));
        writing.setLink(getExpectedWritingLink(author.getLink(), writingIndex));
        writing.setDescription(getExpectedWritingDescr(writingIndex));
        writing.setGroupName(getExpectedWritingGroup(writingIndex));
        writing.setSize(getExpectedWritingSize(writingIndex));
        writing.setLastChangedDate(getExpectedWritingDate(writingIndex));
        return writing;
    }

    public static Date getExpectedWritingDate(Long writingIndex) {
        Integer day = Long.valueOf(writingIndex).intValue();
        Calendar c = Calendar.getInstance();
        //c.setTime(new Date());
        c.set(Calendar.YEAR, 2014);
        c.set(Calendar.MONTH, Calendar.MARCH);
        c.set(Calendar.DAY_OF_YEAR, 14);
        c.set(Calendar.HOUR, 14);
        c.set(Calendar.MINUTE, 14);
        c.set(Calendar.SECOND, 14);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date changeDay(Date date, int day){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        return c.getTime();
    }

    public static String getExpectedWritingSize(Long writingId) {
        return writingId + "0k";
    }

    public static String getExpectedWritingGroup(Long writingId) {
        return "writing group" + writingId;
    }

    public static String getExpectedWritingDescr(Long writingId) {
        return "writing description" + writingId;
    }

    public static String getExpectedWritingLink(String authorLink, Long writingId) {
        return authorLink+"/writing_link_"+ writingId;
    }

    public static String getExpectedWritingName(Long writingId) {
        return "writing name"+writingId;
    }

    public static ComparableItem<String> createItem(final String fieldName, final Class fieldClass, final Customer customer) {
        return new ComparableItem<String>() {
            @Override
            public String getFieldName() {
                return fieldName;
            }
            @Override
            public Customer getCustomer() {
                return customer;
            }
        };
    }

    public static Customer createCustomer(Long customerIndex){
        Customer customer = new Customer();
        customer.setName(getExpectedCustomerName(customerIndex));
        return customer;
    }
}

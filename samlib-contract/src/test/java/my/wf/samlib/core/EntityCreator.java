package my.wf.samlib.core;

import my.wf.samlib.core.model.entity.*;
import my.wf.samlib.core.requestprocessor.AuthorProcessor;
import my.wf.samlib.core.requestprocessor.CustomerRequestProcessor;
import my.wf.samlib.core.requestprocessor.MessageProcessor;
import my.wf.samlib.core.sprider.AuthorWebReader;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.core.storage.CustomerStorage;
import org.mockito.Mockito;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class EntityCreator {
    public static Author createAuthor(Long authorId, Writing... authorWritings){
        Author author = new Author();
        author.setId(authorId);
        author.setName(getExpectedAuthorName(authorId));
        author.setLink(getExpectedAuthorLink(authorId));
        for(Writing w: authorWritings){
            author.getWritings().add(w);
            w.setAuthor(author);
        }
        return author;
    }

    public static String getExpectedAuthorLink(Long authorId) {
        return "http://author_link" + authorId;
    }

    public static String getExpectedAuthorName(Long authorId) {
        return "Author name" + authorId;
    }

    public static Writing createWriting(Long writingId){
        Writing writing = new Writing();
        writing.setId(writingId);
        writing.setName(getExpectedWritingName(writingId));
        writing.setLink(getExpectedWritingLink(writingId));
        writing.setDescription(getExpectedWritingDescr(writingId));
        writing.setGroupName(getExpectedWritingGroup(writingId));
        writing.setSize(getExpectedWritingSize(writingId));
        writing.setLastChangedDate(getExpectedWritingDate(writingId));
        return writing;
    }

    public static Date getExpectedWritingDate(Long writingId) {
        Integer day = Long.valueOf(writingId).intValue();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.YEAR, 2014);
        c.set(Calendar.MONTH, Calendar.MARCH);
        c.set(Calendar.DAY_OF_YEAR, day);
        c.set(Calendar.HOUR, 10);
        c.set(Calendar.MINUTE, 30);
        c.set(Calendar.SECOND, 55);
        c.set(Calendar.MILLISECOND, 0);
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

    public static String getExpectedWritingLink(Long writingId) {
        return "http://writing_link"+ writingId;
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

    public static Customer createCustomer(Writing ... unreadWritings){
        Customer customer = new Customer();
        for(Writing w: unreadWritings){
            customer.getUnreadWritings().add(w);
        }
        return customer;
    }
}

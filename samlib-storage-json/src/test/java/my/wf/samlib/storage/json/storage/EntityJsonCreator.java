package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.ComparableItem;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.storage.json.model.AuthorJson;
import my.wf.samlib.storage.json.model.CustomerJson;
import my.wf.samlib.storage.json.model.WritingJson;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class EntityJsonCreator {
    public static AuthorJson createAuthor(Long authorId, Writing... authorWritings){
        AuthorJson author = new AuthorJson();
        author.setId(authorId);
        author.setName(getExpectedAuthorName(authorId));
        author.setLink(getExpectedAuthorLink(authorId));
        for(Writing w: authorWritings){
            author.getWritings().add(w);
            w.setAuthor(author);
        }
        return author;
    }

    public static AuthorJson createAuthorWithWritings(Long authorId, int writingCount){
        AuthorJson author = createAuthor(authorId);
        for(int i = 1; i<= writingCount; i++){
            Writing w = createWriting(authorId * 1000 + i);
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

    public static WritingJson createWriting(Long writingId){
        WritingJson writing = new WritingJson();
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

    public static CustomerJson createCustomer(Writing ... unreadWritings){
        CustomerJson customer = new CustomerJson();
        for(Writing w: unreadWritings){
            customer.getUnreadWritings().add(w);
        }
        return customer;
    }
}

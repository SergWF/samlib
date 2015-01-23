package my.wf.samlib.core;

import my.wf.samlib.core.model.AuthorTestEntity;
import my.wf.samlib.core.model.CustomerTestEntity;
import my.wf.samlib.core.model.WritingTestEntity;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class EntityCreator {
    public static Date BASE_DATE;

    static{
        try {
            BASE_DATE = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse("2014.11.21 13:30:50");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getExpectedAuthorLink(int authorIndex) {
        return "http://author_link" + authorIndex;
    }

    public static String getExpectedAuthorName(int authorIndex) {
        return "Author name" + authorIndex;
    }

    public static String getExpectedCustomerName(int customerIndex) {
        return "Customer name" + customerIndex;
    }

    public static String getExpectedWritingLink(Author author, int writingIndex) {
        return author.getLink() + "/writing" + writingIndex;
    }

    public static String getExpectedWritingName(Author author, int writingIndex) {
        return author.getName() + " writing " + writingIndex;
     }


    public static Author createAuthor(int authorIndex, int writingCount){
        Author author = new AuthorTestEntity();
        author.setId(Long.valueOf(authorIndex));
        author.setLink(getExpectedAuthorLink(authorIndex));
        author.setName(getExpectedAuthorName(authorIndex));
        for(int i = 0; i< writingCount; i++){
            author.getWritings().add(createWriting(author, i));
        }
        return author;
    }

    public static Writing createWriting(Author author, int writingIndex){
        Writing writing = new WritingTestEntity();
        writing.setAuthor(author);
        writing.setId(author.getId()*1000 + writingIndex);
        writing.setLink(getExpectedWritingLink(author, writingIndex));
        writing.setName(getExpectedWritingName(author, writingIndex));
        writing.setLastChangedDate(BASE_DATE);
        return writing;
    }

    public static Customer createCustomer(int customerIndex){
        Customer customer = new CustomerTestEntity();
        customer.setId(Long.valueOf(customerIndex));
        customer.setName(getExpectedCustomerName(customerIndex));
        customer.setEnabled(true);
        return customer;
    }

/*
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

    public static Author copyAuthor(Author author){
        Author copy = new Author();
        copy.setName(author.getName());
        copy.setLink(author.getLink());
        for(Writing w: author.getWritings()){
            Writing wCopy = copyWriting(w);
            wCopy.setAuthor(copy);
            copy.getWritings().add(wCopy);
        }
        return copy;
    }

    public static Writing copyWriting(Writing writing){
        Writing copy = new Writing();
        copy.setLink(writing.getLink());
        copy.setDescription(writing.getDescription());
        copy.setLastChangedDate(writing.getLastChangedDate());
        copy.setSize(writing.getSize());
        copy.setGroupName(writing.getGroupName());
        copy.setName(writing.getName());
        return copy;
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
    */
}

package my.wf.samlib.core;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class EntityCreator {
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

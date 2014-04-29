package my.wf.samlib.core.storage;

import my.wf.samlib.core.EntityCreator;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class CustomerOrderingTest extends EntityCreator {
/*

    Author author1;
    Author author2;


    private void createAuthors() {
        try {
            author1 = createAuthor(1L, "http://link1", "Name1", new SimpleDateFormat("yyyy.MM.dd").parse("2000.10.20"), false);
            author2 = createAuthor(1L, "http://link2", "Name2", new SimpleDateFormat("yyyy.MM.dd").parse("2000.10.21"), true);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {
        OrderBy orderBy = OrderBy.create().add(BaseEntity.FIELD.NAME, OrderDirection.ASC).add(BaseEntity.FIELD.LAST_CHANGED, OrderDirection.DESC);
        assertTrue(orderBy.getOrder().size() == 2);
        assertEquals(orderBy.getOrder().get(0).getFieldName(), BaseEntity.FIELD.NAME);
        assertEquals(orderBy.getOrder().get(1).getFieldName(), BaseEntity.FIELD.LAST_CHANGED);
        assertEquals(orderBy.getOrder().get(0).getDirection(), OrderDirection.ASC);
        assertEquals(orderBy.getOrder().get(1).getDirection(), OrderDirection.DESC);
    }

    @Test
    public void testCreate() {
        OrderBy orderBy = OrderBy.create();
        assertTrue(orderBy.getOrder().size() == 0);
    }

    @Test
    public void testCreate1() {
        OrderBy orderBy = OrderBy.create(BaseEntity.FIELD.NAME, OrderDirection.DESC);
        assertTrue(orderBy.getOrder().size() == 1);
        assertEquals(orderBy.getOrder().get(0).getFieldName(), BaseEntity.FIELD.NAME);
        assertEquals(orderBy.getOrder().get(0).getDirection(), OrderDirection.DESC);
    }

    @Test
    public void testComparatorByNameAsc() {
        createAuthors();
        OrderBy orderBy = OrderBy.create(BaseEntity.FIELD.NAME, OrderDirection.ASC);
        int compared = orderBy.getComparator().compareByType(author1, author2);
        assertTrue(compared < 0);
    }

    @Test
    public void testComparatorByNameDesc() {
        createAuthors();
        OrderBy orderBy = OrderBy.create(BaseEntity.FIELD.NAME, OrderDirection.DESC);
        int compared = orderBy.getComparator().compareByType(author1, author2);
        assertTrue(compared > 0);
    }

    @Test
    public void testComparatorByDateAsc() {
        createAuthors();
        OrderBy orderBy = OrderBy.create(BaseEntity.FIELD.LAST_CHANGED, OrderDirection.ASC);
        int compared = orderBy.getComparator().compareByType(author1, author2);
        assertTrue(compared < 0);
    }

    @Test
    public void testComparatorByDateDesc() {
        createAuthors();
        OrderBy orderBy = OrderBy.create(BaseEntity.FIELD.LAST_CHANGED, OrderDirection.DESC);
        int compared = orderBy.getComparator().compareByType(author1, author2);
        assertTrue(compared > 0);
    }

    @Test
    public void testComparatorByUnreadAsc() {
        createAuthors();
        OrderBy orderBy = OrderBy.create(BaseEntity.FIELD.IS_UNREAD, OrderDirection.ASC);
        int compared = orderBy.getComparator().compareByType(author1, author2);
        assertTrue(compared < 0);
    }
    @Test
    public void testComparatorByUnreadDesc() {
        createAuthors();
        OrderBy orderBy = OrderBy.create(BaseEntity.FIELD.IS_UNREAD, OrderDirection.DESC);
        int compared = orderBy.getComparator().compareByType(author1, author2);
        assertTrue(compared > 0);
    }

    @Test
    public void testComparatorByNameAscDateDesc() throws ParseException {
        createAuthors();
        Author author3 = createAuthor(3L, "http://link1", "Name1", new SimpleDateFormat("yyyy.MM.dd").parse("2000.10.21"), true);
        OrderBy orderBy = OrderBy.create(BaseEntity.FIELD.NAME, OrderDirection.ASC).add(BaseEntity.FIELD.LAST_CHANGED, OrderDirection.DESC);
        int compared = orderBy.getComparator().compareByType(author1, author3);
        assertTrue(compared > 0);
    }

    @Test
    public void testComparatorByNameAscDateDesc1() throws ParseException {
        createAuthors();
        OrderBy orderBy = OrderBy.create(BaseEntity.FIELD.NAME, OrderDirection.ASC).add(BaseEntity.FIELD.LAST_CHANGED, OrderDirection.DESC);
        int compared = orderBy.getComparator().compareByType(author1, author2);
        assertTrue(compared < 0);
    }
    */
}

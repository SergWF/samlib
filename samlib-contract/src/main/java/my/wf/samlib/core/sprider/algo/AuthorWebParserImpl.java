package my.wf.samlib.core.sprider.algo;

import my.wf.samlib.core.factory.EntityFactory;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.sprider.AuthorWebParser;

/**
 * Created by Serg on 21.01.2015.
 */
public class AuthorWebParserImpl implements AuthorWebParser {
    private EntityFactory entityFactory;

    public void setEntityFactory(EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
    }

    @Override
    public Author parseAuthor(String htmlData) {
        Author author = entityFactory.createInstance(Author.class);
        return author;
    }
}

package my.wf.samlib.core.factory.impl;

import my.wf.samlib.core.factory.AuthorFactory;
import my.wf.samlib.core.model.entity.Author;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class DefaultAuthorFactory implements AuthorFactory {
    @Override
    public Author newAuthor(String link) {
        Author author = new Author();
        author.setLink(link);
        return author;
    }
}

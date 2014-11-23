package my.wf.samlib.core.sprider;

import my.wf.samlib.core.model.entity.Author;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface AuthorWebParser<T extends Author> {
    T parseAuthor(String htmlData);
}

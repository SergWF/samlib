package my.wf.samlib.core.factory;

import my.wf.samlib.core.model.entity.Author;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface  AuthorFactory {
    Author newAuthor(String link);
}

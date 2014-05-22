package my.wf.samlib.storage.json.factory;

import my.wf.samlib.core.factory.AuthorFactory;
import my.wf.samlib.storage.json.model.AuthorJson;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorJsonFactoryImpl implements AuthorFactory {
    @Override
    public AuthorJson newAuthor(String link) {
        AuthorJson author = new AuthorJson();
        author.setLink(link);
        return author;
    }
}

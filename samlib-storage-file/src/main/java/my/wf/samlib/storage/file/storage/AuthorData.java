package my.wf.samlib.storage.file.storage;

import my.wf.samlib.core.model.entity.Author;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorData {
    private Map<Long, Author> authors = new HashMap<Long, Author>();

    public Author getByLink(String authorLink) {
        if(null == authorLink || 0 == authorLink.trim().length()){
            return null;
        }
        for(Author author: authors.values()){
            if(authorLink.equals(author.getLink())){
                return author;
            }
        }
        return null;
    }
}

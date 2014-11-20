package my.wf.samlib.core.sprider;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.core.sprider.algo.WebReaderAlgo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorWebReader {
    public static final String DEFAULT_ENCODING = "windows-1251";
    AuthorWebParser parser;


    public AuthorWebParser getParser() {
        return parser;
    }

    public void setParser(AuthorWebParser parser) {
        this.parser = parser;
    }

    public Author readAuthorByLink(Author author,  Date readDate) throws IOException {
        String htmlData = WebReaderAlgo.readAuthorPage(author.getLink(), DEFAULT_ENCODING);
        Author newAuthor = parser.parseAuthor(htmlData);
        WebReaderAlgo.refreshAuthor(author, newAuthor, readDate);
        return newAuthor;
    }
}

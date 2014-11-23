package my.wf.samlib.core.sprider;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.sprider.algo.WebReaderAlgo;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorWebReader<T extends Author> {
    private static final Logger logger = Logger.getLogger(AuthorWebReader.class.getName());

    public static final String DEFAULT_ENCODING = "windows-1251";
    AuthorWebParser<T> parser;


    public AuthorWebParser getParser() {
        return parser;
    }

    public void setParser(AuthorWebParser parser) {
        this.parser = parser;
    }

    public T readAuthorByLink(String link) throws IOException {
        logger.info("Read author by link " + link);
        String htmlData = WebReaderAlgo.readAuthorPage(link, DEFAULT_ENCODING);
        logger.fine("Read OK. size:" + htmlData.length());
        T newAuthor = parser.parseAuthor(htmlData);
        logger.fine("Author read OK. name= "+newAuthor.getName()+", Writings: " + newAuthor.getWritings().size());
        return newAuthor;
    }
}

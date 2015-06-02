package my.wf.samlib.core.sprider;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.sprider.algo.WebReaderAlgo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorWebReader {
    private static final Logger logger = LoggerFactory.getLogger(AuthorWebReader.class.getName());

    public static final String DEFAULT_ENCODING = "windows-1251";
    AuthorWebParser parser;


    public AuthorWebParser getParser() {
        return parser;
    }

    public void setParser(AuthorWebParser parser) {
        this.parser = parser;
    }

    public Author readAuthorByLink(String link) throws IOException {
        logger.debug("Read author by link " + link);
        String htmlData = WebReaderAlgo.readAuthorPage(link, DEFAULT_ENCODING);
        logger.debug("Read OK. size:" + htmlData.length());
        Author newAuthor = parser.parseAuthor(htmlData);
        logger.debug("Author read OK. name= "+newAuthor.getName()+", Writings: " + newAuthor.getWritings().size());
        return newAuthor;
    }
}

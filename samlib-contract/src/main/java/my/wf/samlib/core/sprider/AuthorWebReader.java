package my.wf.samlib.core.sprider;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Writing;

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
    private Pattern charsetPattern = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");

    public AuthorWebParser getParser() {
        return parser;
    }

    public void setParser(AuthorWebParser parser) {
        this.parser = parser;
    }

    public Author readAuthorByLink(Author author,  Date readDate) throws IOException {
        String htmlData = readAuthorPage(author.getLink());
        Author newAuthor = parser.parseAuthor(htmlData);
        refreshAuthor(author, newAuthor, readDate);
        return newAuthor;
    }

    protected void refreshAuthor(Author author, Author newAuthor, Date readDate) {
        for(Writing writinig: newAuthor.getWritings()){
            Writing old = findByLink(writinig.getLink(), author.getWritings());
            if(null == old || checkChanges(old, writinig)){
                writinig.setLastChangedDate(readDate);
            }
        }
    }

    private boolean checkChanges(Writing old, Writing writinig) {
        return !old.getDescription().equals(writinig.getDescription()) ||
               !old.getSize().equals(writinig.getSize());
    }

    private Writing findByLink(String link, Collection<Writing> writings){
        for(Writing writing: writings){
            if(writing.getLink().equals(link)){
               return writing;
            }
        }
        return null;
    }

    private String readAuthorPage(String link) throws IOException {
        URL url = new URL(link);
        URLConnection con = url.openConnection();
        Matcher m = charsetPattern.matcher(con.getContentType());
        String charset = m.matches() ? m.group(1) : DEFAULT_ENCODING;
        Reader r = new InputStreamReader(con.getInputStream(), charset);
        StringBuilder buf = new StringBuilder();
        while (true) {
            int ch = r.read();
            if (ch < 0)
                break;
            buf.append((char) ch);
        }
        return buf.toString();
    }
}

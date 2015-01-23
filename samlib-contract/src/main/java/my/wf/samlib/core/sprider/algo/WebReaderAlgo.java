package my.wf.samlib.core.sprider.algo;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Writing;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Serg on 20.11.2014.
 */
public class WebReaderAlgo {
    private static final Pattern charsetPattern = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");

    public static String getCharset(String contentType, String encoding){
        if(null == contentType){
            return encoding;
        }
        Matcher m = charsetPattern.matcher(contentType);
        return m.matches() ? m.group(1) : encoding;
    }

    public static String readAuthorPage(String link, String encoding) throws IOException {
        URL url = new URL(link);
        URLConnection con = url.openConnection();
        Reader r = new InputStreamReader(con.getInputStream(), getCharset(con.getContentType(), encoding));
        StringBuilder buf = new StringBuilder();
        while (true) {
            int ch = r.read();
            if (ch < 0)
                break;
            buf.append((char) ch);
        }
        return buf.toString();
    }

    public static void refreshAuthor(Author author, Author newAuthor) {
        /*
        author.setName(newAuthor.getName());
        Set<Writing> writingsForRemove = new HashSet<Writing>();
        for (Writing writing : author.getWritings()) {
            Writing newWriting = SearchHelper.searchByLink(writing.getLink(), newAuthor.getWritings());
            if (null == newWriting) {
                writingsForRemove.add(writing);
            } else {
                if (checkChanges(writing, newWriting)) {
                    writing.setLastChangedDate(newWriting.getLastChangedDate());
                }
                newAuthor.getWritings().remove(newWriting);
            }
        }
        author.getWritings().removeAll(writingsForRemove);
        author.getWritings().addAll(newAuthor.getWritings());
        */
    }

    public static boolean checkChanges(Writing old, Writing writing) {
        return !old.getDescription().equals(writing.getDescription()) ||
                !old.getSize().equals(writing.getSize());
    }
}

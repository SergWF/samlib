package my.wf.samlib.core.sprider.algo;

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

    public static void refreshAuthor(Author author, Author newAuthor, Date readDate) {
        for(Writing writinig: newAuthor.getWritings()){
            Writing old = findByLink(writinig.getLink(), author.getWritings());
            if(null == old || checkChanges(old, writinig)){
                writinig.setLastChangedDate(readDate);
            }
        }
    }

    public static boolean checkChanges(Writing old, Writing writinig) {
        return !old.getDescription().equals(writinig.getDescription()) ||
                !old.getSize().equals(writinig.getSize());
    }

    public static Writing findByLink(String link, Collection<Writing> writings){
        for(Writing writing: writings){
            if(writing.getLink().equals(link)){
                return writing;
            }
        }
        return null;
    }
}

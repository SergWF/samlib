package my.wf.samlib.core.dataextract.helper;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by Serg on 21.01.2015.
 */
public class AuthorPropertyHelper {
    private static final Logger logger = LoggerFactory.getLogger(AuthorPropertyHelper.class);

    public static Date getAuthorLastChangeDate(Author author){
        Date lastUpdatedDate = null;
        logger.debug("calculate Author's ["+author.getName()+"] last update date. Writings count:" + author.getWritings().size());
        for(Writing writing: author.getWritings()){
            if(null ==  lastUpdatedDate || writing.getLastChangedDate().after(lastUpdatedDate)){
                lastUpdatedDate = writing.getLastChangedDate();
            }
        }
        logger.debug("calculate Author's ["+author.getName()+"] last update date.:" + lastUpdatedDate);
        return lastUpdatedDate;
    }
    public static int getAuthorUnreadWritingsCount(Customer customer, Author author){
        logger.debug("calculates Author's ["+author.getName()+"] writings unread by customer ["+customer.getName()+"]");
        int unread = 0;
        for (Writing writing : customer.getUnreadWritings()) {
            if(writing.getAuthor().equals(author)){
                unread++;
            }
        }
        logger.debug("calculates Author's ["+author.getName()+"] writings unread by customer ["+customer.getName()+"]: " + unread);
        return unread;
    }
}

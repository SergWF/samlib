package my.wf.samlib.core.dataextract.helper;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;

import java.util.Date;

/**
 * Created by Serg on 21.01.2015.
 */
public class AuthorPropertyHelper {
    public static Date getAuthorLastChangeDate(Author author){
        Date lastUpdatedDate = null;
        for(Writing writing: author.getWritings()){
            if(null ==  lastUpdatedDate || writing.getLastChangedDate().after(lastUpdatedDate)){
                lastUpdatedDate = writing.getLastChangedDate();
            }
        }
        return lastUpdatedDate;
    }
    public static int getAuthorUnreadWritingsCount(Customer customer, Author author){
        int unread = 0;
        for (Writing writing : customer.getUnreadWritings()) {
            if(writing.getAuthor().equals(author)){
                unread++;
            }
        }
        return unread;
    }
}

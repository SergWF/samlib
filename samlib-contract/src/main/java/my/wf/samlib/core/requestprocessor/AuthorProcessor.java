package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.sprider.AuthorWebReader;
import my.wf.samlib.core.sprider.algo.WebReaderAlgo;
import my.wf.samlib.core.storage.AuthorStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AuthorProcessor {
    public static final String MSG_KEY_AUTHOR_IS_ALREADY_PRESENT = "author.is.already.present";
    private static final Logger logger = LoggerFactory.getLogger(AuthorProcessor.class);

    AuthorStorage authorStorage;
    AuthorWebReader webReader;
    MessageProcessor messageProcessor;


    public void setAuthorStorage(AuthorStorage authorStorage) {
        this.authorStorage = authorStorage;
    }

    public void setWebReader(AuthorWebReader webReader) {
        this.webReader = webReader;
    }

    public void setMessageProcessor(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    public Author addNewAuthor(String authorLink) throws IOException, StorageException {
        logger.debug("adds new author by link " + authorLink);
        Author author = authorStorage.findByLink(authorLink);
        if (null == author) {
            author = authorStorage.save(webReader.readAuthorByLink(authorLink));
            logger.info("Author with link ["+author.getLink()+"] was added to storage with id = " + author.getId());
        } else {
            logger.warn("Author with link [" + author.getLink() + "] is already present in storage with id = " + author.getId());
            messageProcessor.addWarnMessage(null, MSG_KEY_AUTHOR_IS_ALREADY_PRESENT, new String[]{authorLink});
        }
        return author;
    }

    public Author removeAuthor(Author author) throws StorageException {
        return authorStorage.remove(author);
    }

    public Author updateAuthor(Author author) throws IOException, StorageException {
        Author update = webReader.readAuthorByLink(author.getLink());
        WebReaderAlgo.refreshAuthor(author, update);
        return authorStorage.save(author);
    }
}

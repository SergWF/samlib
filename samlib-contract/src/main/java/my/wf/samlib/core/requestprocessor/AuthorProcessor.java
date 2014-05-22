package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.factory.AuthorFactory;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.sprider.AuthorWebReader;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.core.model.entity.Author;

import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorProcessor {
    public static final String MSG_KEY_AUTHOR_IS_ALREADY_PRESENT = "author.is.already.present";

    AuthorStorage authorStorage;
    AuthorWebReader webReader;
    MessageProcessor messageProcessor;
    AuthorFactory authorFactory;


    public AuthorStorage getAuthorStorage() {
        return authorStorage;
    }

    public void setAuthorStorage(AuthorStorage authorStorage) {
        this.authorStorage = authorStorage;
    }

    public AuthorWebReader getWebReader() {
        return webReader;
    }

    public void setWebReader(AuthorWebReader webReader) {
        this.webReader = webReader;
    }

    public MessageProcessor getMessageProcessor() {
        return messageProcessor;
    }

    public void setMessageProcessor(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    public AuthorFactory getAuthorFactory() {
        return authorFactory;
    }

    public void setAuthorFactory(AuthorFactory authorFactory) {
        this.authorFactory = authorFactory;
    }

    public Author addNewAuthor(String authorLink) throws IOException, StorageException {
        Author author = authorStorage.findByLink(authorLink);
        if (null == author) {
            author = authorStorage.save(webReader.readAuthorByLink(authorFactory.newAuthor(authorLink), new Date()));
        } else {
            messageProcessor.addWarnMessage(null, MSG_KEY_AUTHOR_IS_ALREADY_PRESENT, new String[]{authorLink});
        }
        return author;
    }

    public Author removeAuthor(Author author) throws StorageException {
        return authorStorage.remove(author);
    }

    public Author updateAuthor(Author author) throws IOException, StorageException {
        return authorStorage.save(webReader.readAuthorByLink(author, new Date()));
    }
}

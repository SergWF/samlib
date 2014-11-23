package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.factory.AuthorFactory;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.sprider.AuthorWebReader;
import my.wf.samlib.core.sprider.algo.WebReaderAlgo;
import my.wf.samlib.core.storage.AuthorStorage;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorProcessor<T extends Author> {
    public static final String MSG_KEY_AUTHOR_IS_ALREADY_PRESENT = "author.is.already.present";

    AuthorStorage<T> authorStorage;
    AuthorWebReader<T> webReader;
    MessageProcessor messageProcessor;
    AuthorFactory<T> authorFactory;


    public void setAuthorStorage(AuthorStorage authorStorage) {
        this.authorStorage = authorStorage;
    }

    public void setWebReader(AuthorWebReader webReader) {
        this.webReader = webReader;
    }

    public void setMessageProcessor(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    public void setAuthorFactory(AuthorFactory authorFactory) {
        this.authorFactory = authorFactory;
    }

    public T addNewAuthor(String authorLink) throws IOException, StorageException {
        T author = authorStorage.findByLink(authorLink);
        if (null == author) {
            author = authorStorage.save(webReader.readAuthorByLink(authorLink));
        } else {
            messageProcessor.addWarnMessage(null, MSG_KEY_AUTHOR_IS_ALREADY_PRESENT, new String[]{authorLink});
        }
        return author;
    }

    public T removeAuthor(T author) throws StorageException {
        return authorStorage.remove(author);
    }

    public T updateAuthor(T author) throws IOException, StorageException {
        T update = webReader.readAuthorByLink(author.getLink());
        WebReaderAlgo.refreshAuthor(author, update);
        return authorStorage.save(author);
    }
}

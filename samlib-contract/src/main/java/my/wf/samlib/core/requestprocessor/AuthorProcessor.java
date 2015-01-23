package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.sprider.AuthorWebReader;
import my.wf.samlib.core.sprider.algo.WebReaderAlgo;
import my.wf.samlib.core.storage.AuthorStorage;

import java.io.IOException;

public class AuthorProcessor {
    public static final String MSG_KEY_AUTHOR_IS_ALREADY_PRESENT = "author.is.already.present";

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
        Author author = authorStorage.findByLink(authorLink);
        if (null == author) {
            author = authorStorage.save(webReader.readAuthorByLink(authorLink));
        } else {
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

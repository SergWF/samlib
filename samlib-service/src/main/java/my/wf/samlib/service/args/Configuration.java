package my.wf.samlib.service.args;

import my.wf.samlib.core.factory.AuthorFactory;
import my.wf.samlib.core.factory.impl.DefaultAuthorFactory;
import my.wf.samlib.core.requestprocessor.AuthorProcessor;
import my.wf.samlib.core.requestprocessor.CredentialsChecker;
import my.wf.samlib.core.requestprocessor.CustomerRequestProcessor;
import my.wf.samlib.core.requestprocessor.MessageProcessor;
import my.wf.samlib.core.sprider.AuthorWebParser;
import my.wf.samlib.core.sprider.AuthorWebReader;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.core.storage.CustomerStorage;

/**
 * Created by Serg on 14.01.2015.
 */
public class Configuration {
    private AuthorWebParser parser;
    private AuthorWebReader reader = new AuthorWebReader();
    private AuthorProcessor authorProcessor = new AuthorProcessor();
    private CredentialsChecker credentialsChecker = new CredentialsChecker();
    private CustomerRequestProcessor customerRequestProcessor = new CustomerRequestProcessor();
    private MessageProcessor messageProcessor = new MessageProcessor();
    private AuthorFactory authorFactory = new DefaultAuthorFactory();

    private AuthorStorage authorStorage;
    private CustomerStorage customerStorage;

//    authorProcessor.setAuthorFactory(authorFactory);
//    authorProcessor.setAuthorStorage();

}

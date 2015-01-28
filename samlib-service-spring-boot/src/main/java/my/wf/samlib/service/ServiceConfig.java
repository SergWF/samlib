package my.wf.samlib.service;

import my.wf.samlib.core.factory.EntityFactory;
import my.wf.samlib.core.requestprocessor.*;
import my.wf.samlib.core.sprider.AuthorWebParser;
import my.wf.samlib.core.sprider.AuthorWebReader;
import my.wf.samlib.core.sprider.algo.AuthorWebParserImpl;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.core.storage.CustomerStorage;
import my.wf.samlib.storage.jpa.JpaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JpaConfig.class)
public class ServiceConfig {

    @Autowired
    CredentialsChecker credentialsChecker;
    @Autowired
    EntityFactory entityFactory;
    @Autowired
    CustomerStorage customerStorage;
    @Autowired
    AuthorStorage authorStorage;

    @Bean
    public MessageProcessor messageProcessor() {
        MessageProcessor bean = new MessageProcessor();
        return bean;
    }

    @Bean
    public AuthorProcessor authorProcessor() {
        AuthorProcessor bean = new AuthorProcessor();
        bean.setAuthorStorage(authorStorage);
        bean.setMessageProcessor(messageProcessor());
        bean.setWebReader(webReader());
        return bean;
    }

    @Bean
    public AuthorWebReader webReader() {
        AuthorWebReader bean = new AuthorWebReader();
        bean.setParser(authorWebParser());
        return bean;
    }

    private AuthorWebParser authorWebParser() {
        AuthorWebParser bean = new AuthorWebParserImpl();
        return bean;
    }

    @Bean
    public SecurityRequestProcessor securityRequestProcessor(){
        SecurityRequestProcessor bean = new SecurityRequestProcessor();
        bean.setCredentialsChecker(credentialsChecker);
        bean.setCustomerFactory(entityFactory);
        bean.setCustomerStorage(customerStorage);
        return bean;
    }
}

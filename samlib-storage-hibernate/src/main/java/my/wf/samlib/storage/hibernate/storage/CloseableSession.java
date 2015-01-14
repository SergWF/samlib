package my.wf.samlib.storage.hibernate.storage;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class CloseableSession implements AutoCloseable {

    private final Session session;
    private Transaction transaction;

    public CloseableSession(Session session, boolean transactional) {
        this.session = session;
        if(transactional){
            transaction = session.beginTransaction();
        }
    }

    public Session delegate() {
        return session;
    }

    public void rollback(){
        if(null != transaction){
            transaction.rollback();
        }
    }

    @Override
    public void close() {
        if(null != session && session.isOpen()) {
            if(null != transaction){
                transaction.commit();
            }
            session.close();
        }
    }
}
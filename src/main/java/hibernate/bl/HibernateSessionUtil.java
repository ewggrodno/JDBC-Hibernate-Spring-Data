package hibernate.bl;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Getter
public class HibernateSessionUtil {

    protected Session session;
    private Transaction transaction;

    public Session openSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    public Session openTransactionSession() {
        session = openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeSession(){
        session.close();
    }

    public void closeTransactionSession(){
        transaction.commit();
        closeSession();
    }
}

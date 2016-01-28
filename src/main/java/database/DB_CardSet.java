package database;

import Model.CardSet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Jean on 28.01.2016.
 */
public class DB_CardSet
{
    public static CardSet getCardSet(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
    // CardSet = nicht SQL Tabelle, sondern Model.CardSet
        List cardList = session.createQuery( "from CardSet C where C.id is "+_id ).list();
        transaction.commit();
        CardSet cardSet = (CardSet) cardList.get(0);
        return cardSet;
    }
}

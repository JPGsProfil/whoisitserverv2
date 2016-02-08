package database;

import Model.Highscore;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Jean on 08.02.2016.
 */
public class DB_Highscore
{
    public static Highscore getScoresById(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List scoreList = session.createQuery( "from Highscore H where H. is "+_id ).list();
        transaction.commit();
        Highscore score = (Highscore) scoreList.get(0);
        return score;
    }
}

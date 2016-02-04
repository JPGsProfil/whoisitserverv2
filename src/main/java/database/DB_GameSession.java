package database;

import Model.GameSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Jean on 03.02.2016.
 */
public class DB_GameSession
{
    public static GameSession getSessionById(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List sessionList = session.createQuery( "from GameSession S where S.id is "+_id ).list();
        transaction.commit();
        GameSession gamesession = new GameSession();
        if(!sessionList.isEmpty())
        {
            gamesession= (GameSession) sessionList.get(0);
        }
        return gamesession;
    }



    public static Boolean isUserInSession(Integer _userId)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        Criteria cr = session.createCriteria(GameSession.class);
        Criterion player1 = Restrictions.eq("player1_ID", _userId );
        Criterion player2 = Restrictions.eq("player2_ID", _userId );
        LogicalExpression orExp = Restrictions.or(player1, player2);
        cr.add(orExp);
        List sessionList = cr.list();
        transaction.commit();
        if(sessionList.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public static List<GameSession> getSessionsWithOneUser()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        Criteria cr = session.createCriteria(GameSession.class);
        Criterion player1 = Restrictions.isNotNull("player1_ID");
        Criterion player2 = Restrictions.isNull("player2_ID" );
        LogicalExpression orExp = Restrictions.or(player1, player2);
        cr.add(orExp);
        List sessionList = cr.list();
        transaction.commit();
        return sessionList;
    }

}

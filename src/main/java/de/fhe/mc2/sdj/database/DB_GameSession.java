package de.fhe.mc2.sdj.database;

import de.fhe.mc2.sdj.marshalling.ReturnBool;
import de.fhe.mc2.sdj.model.GameSession;
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
    public static Response getSessionById(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List sessionList = session.createQuery( "from GameSession S where S.id is "+_id ).list();
        transaction.commit();
        GameSession gamesession = new GameSession();
        if(!sessionList.isEmpty())
        {
            gamesession= (GameSession) sessionList.get(0);
            return Response.ok().entity(gamesession).build();
        }
        else
        {
            return Response.noContent().entity(gamesession).build();
        }
    }


    public static Response addGameSession(GameSession _session)
    {
        // make sure id is null, otherwise would be update
        _session.setId(null);
        // save session
        return HibernateUtil.addToDB(_session);
    }


    public static Response updateGameSession(GameSession _session)
    {
        // save session
        return HibernateUtil.addToDB(_session);
    }




    public static Response isUserInSession(Integer _userId)
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
        ReturnBool returnbool = new ReturnBool(); // initalized with false
        if(!sessionList.isEmpty())
        {
            returnbool.bool = true;
            return Response.ok().entity(returnbool).build();
        }
        else
        {
            return Response.noContent().entity(returnbool).build();
        }
    }

    public static Response getSessionsWithOneUser()
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
        if(sessionList.isEmpty())
        {
            return Response.noContent().entity(sessionList).build();
        }
        else
        {
            return Response.ok().entity(sessionList).build();
        }
    }

}

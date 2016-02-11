package de.fhe.mc2.sdj.database;

import de.fhe.mc2.sdj.marshalling.ReturnBool;
import de.fhe.mc2.sdj.modelz.GameSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Connection between GameSession-Model and database
 */
public class DB_GameSession
{
    /**
     * internal db-function
     * get a GameSession by it's id
     * @param _id id of the gameSession as Integer
     * @return Response with GameSession if available
     */
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


    /**
     * add the given gameSession to the database
     * @param _session Gamesession object
     * @return Response with ok if successful
     */
    public static Response addGameSession(GameSession _session)
    {
        // make sure id is null, otherwise would be update
        _session.setId(null);
        // save session
        return HibernateUtil.addToDB(_session);
    }


    /**
     * internal db-function
     * Change values of a specific gameSession
     * can be used to add a user or change date or state
     * @param _session GameSession object wich should be changed
     * @return Response with 200 if successful
     */
    public static Response updateGameSession(GameSession _session)
    {
        // save session
        return HibernateUtil.addToDB(_session);
    }


    /**
     * internal db-function
     * find out whether user is already in a session
     * @param _userId id of the user wich will be checked
     * @return Response with returnbool true if true, else false
     */
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

    /**
     * internal db-function
     * get all sessions with one user
     * needed if looking for sessions with one user
     * @return Response with list of sessions
     */
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

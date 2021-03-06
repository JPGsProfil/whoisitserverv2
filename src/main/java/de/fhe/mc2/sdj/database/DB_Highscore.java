package de.fhe.mc2.sdj.database;

import de.fhe.mc2.sdj.model.Highscore;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.ws.rs.core.Response;

import java.util.List;

/**
 * Connection between Highscore-Model and database
 */
public class DB_Highscore
{
    /**
     * internal db-function
     * get a specific highscore by highscore id
     * not needed because user has a highscore
     * @param _id highscore id as Integer
     * @return Highscore instead of custom response
     */
    public static Highscore getScoresById(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List scoreList = session.createQuery( "from Highscore H where H.id is "+_id ).list();
        transaction.commit();
        Highscore score = (Highscore) scoreList.get(0);
        return score;
    }


    /**
     * get Score which belongs to given user-id
     * @param _id id of the user
     * @return Response with Highscore
     */
    public static Response getScoresByUserId(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List scoreList = session.createQuery( "from Highscore H where H.user.id is "+_id ).list();
        transaction.commit();
        if(scoreList.isEmpty())
        {
            return Response.noContent().entity(scoreList).build();
        }
        else
        {
            return Response.ok().entity(scoreList).build();
        }
    }

    public static Response updateScore(Highscore _highscore)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();

        String hqlUpdate = "update Highscore h set h.matchesWon = :matchesWon, h.matchesLost = :matchesLost where h.id = :id";
// or String hqlUpdate = "update Customer set name = :newName where name = :oldName";
        int updatedEntities = session.createQuery( hqlUpdate )
                .setInteger( "matchesWon",  _highscore.getMatchesWon())
                .setInteger( "matchesLost", _highscore.getMatchesLost() )
                .setInteger( "id", _highscore.getId() )
                .executeUpdate();
        transaction.commit();
        if(session.isOpen())
        {
            session.close();
        }
        if(updatedEntities > 0)
        {
            return Response.ok().build();
        }
        else
        {
            return Response.notModified().build();
        }
    }
}

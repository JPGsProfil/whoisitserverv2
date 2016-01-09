package database;

import Model.Highscore;
import Model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Iterator;
import java.util.List;


@Path( "/db" )
public class DB_Highscore
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "getAllScores" )
    public List GetHighscore()
    {
        Session session = null;
        Transaction transaction  = null;
        List userList = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction  = session.beginTransaction();
            System.out.println( "\nUser:" );
            userList = session.createQuery( "from User" ).list();

            for( int i=0; i<userList.size(); i++ )
            {
                User currentUser = (User) userList.get( i );
                System.out.print( "Nutzer: "+ currentUser.getName());
                System.out.println(   "        gewonnen: " + currentUser.getScore().getMatchesWon() );
            }
            System.out.println("Liste: "+userList);
            transaction.commit();
        } catch( HibernateException ex ) {
            if( transaction != null )
                try { transaction.rollback(); } catch( HibernateException exRb ) {}
            throw new RuntimeException( ex.getMessage() );
        } finally
        {
            try
            {
                if( session != null ) session.close();
            }
            catch( Exception exCl )
            {

            }
        }
        return userList;
    }




}

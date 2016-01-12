package database;

import Model.Highscore;
import Model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.ws.rs.core.Response;

import java.util.List;

/**
 * Created by Jean on 11.01.2016.
 */
public class DB_User
{
    public static User GetUser(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List userList = session.createQuery( "from User U where U.id is "+_id ).list();
        transaction.commit();
        User user = (User) userList.get(0);
        return user;
    }
    /*
    public static User GetUserV2(Integer _id)
    {
        String query = "from User U where U.id is "+_id;
        List list = HibernateUtil.DBTransaction(query);
        User user = (User) list.get(0);
        return user;
    }*/


    public Response testwrite2()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user1 = new User();
        Highscore score = new Highscore();
        user1.setName("a");
        user1.setPassword("Homer");
        user1.setScore(score);
        session.save(user1);
        String status = session.getStatistics().toString();
        session.getTransaction().commit();
        session.close();

        return Response.ok().build();
    }





}

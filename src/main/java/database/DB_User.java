package database;

import Marshalling.Login;
import Marshalling.LoginResult;
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
    public static User getUser(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List userList = session.createQuery( "from User U where U.id is "+_id ).list();
        transaction.commit();
        User user = (User) userList.get(0);
        return user;
    }

    public static LoginResult doesUserWithNameAndPasswordExist(Login _login)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List userList = session.createQuery( "from User U where U.name is "+_login.getName() + " and U.password is "+_login.getPassword() ).list();
        transaction.commit();
        LoginResult result = new LoginResult("accepted");
        if(userList.isEmpty())
        {
            result.setResult("denied");
        }
        return result;
    }
    /*
    public static User GetUserV2(Integer _id)
    {
        String query = "from User U where U.id is "+_id;
        List list = HibernateUtil.DBTransaction(query);
        User user = (User) list.get(0);
        return user;
    }*/


    public static Response addUser(User _user)
    {
        // save user
        HibernateUtil.addToDB(_user);
        // save highscore -> user is needed for highscore
        Highscore score = new Highscore(_user);
        return HibernateUtil.addToDB(score);


        /*
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction currentTransaction = null;
        try
        {
            currentTransaction = session.beginTransaction();
            session.save(_user);
            currentTransaction.commit();
        }
        catch (HibernateException e)
        {
            if (currentTransaction!=null) currentTransaction.rollback();
            e.printStackTrace();
            return Response.notModified().build();
        }
        finally
        {
            session.close();
        }

        return Response.ok().build();*/
    }


    public static Response testwrite2()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User user1 = new User("ja@ja.de","er","pwd");
        session.save(user1);

        Highscore score = new Highscore();
        score.setUser(user1);
        session.save(score);

        session.getTransaction().commit();
        session.close();

        return Response.ok().build();
    }





}

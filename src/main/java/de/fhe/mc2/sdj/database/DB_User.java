package de.fhe.mc2.sdj.database;

import de.fhe.mc2.sdj.marshalling.Login;
import de.fhe.mc2.sdj.model.Highscore;
import de.fhe.mc2.sdj.model.User;
import de.fhe.mc2.sdj.model.UserWithHighscore;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.ws.rs.core.Response;

import java.util.List;

/**
 * Created by Jean on 11.01.2016.
 */
public class DB_User
{


    public static Response getUserV2(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List userList = session.createQuery( "from User U where U.id is "+_id ).list();
        transaction.commit();
        User user = (User) userList.get(0);
        return Response.ok().entity(user).build();
    }

    public static Response deleteUser(UserWithHighscore _user)
    {
        return  HibernateUtil.deleteObj(_user);
    }

    public static Response doesUserWithNameAndPasswordExist(Login _login)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        Criteria cr = session.createCriteria(User.class);
        cr.add(Restrictions.eq("name", _login.getName() ));
        cr.add(Restrictions.eq("password", _login.getPassword() ));
        List userList = cr.list();
        User user = new User(new Integer(-1));
        transaction.commit();
        if(userList.isEmpty())
        {
            return Response.noContent().entity(user).build();
        }
        else
        {
            user = (User) userList.get(0);
            return Response.ok().entity(user).build();
        }
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
        _user.setID(null);

        // user with same name not allowed
        if(doesUserWithNameExist(_user.getName()))
        {
            return Response.status(Response.Status.CONFLICT).build();
        }
        else
        {
            // save user
            Response response = HibernateUtil.addToDB(_user);
            // save highscore -> user is needed for highscore
            if(
                    response.getStatus() == Response.Status.CREATED.getStatusCode()
                    || response.getStatus() == Response.Status.OK.getStatusCode()
              )
            {
                Highscore score = new Highscore(_user);
                response = HibernateUtil.addToDB(score);
            }
            return response;
        }





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

    /*
    private static User getUser(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List userList = session.createQuery( "from User U where U.id is "+_id ).list();
        transaction.commit();
        User user = (User) userList.get(0);
        return user;
    }*/


    private static boolean doesUserWithNameExist(String _name)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        Criteria cr = session.createCriteria(User.class);
        cr.add(Restrictions.eq("name",_name ));
        List userList = cr.list();
        transaction.commit();
        if(userList.isEmpty())
        {
            return false;
        }

        else
        {
            return true;
        }
    }





}

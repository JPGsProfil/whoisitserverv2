package de.fhe.mc2.sdj.database;

import de.fhe.mc2.sdj.marshallingz.Login;
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


    /**
     * internal db-function
     * get a user by userid
     * @param _id userid
     * @return Response with user if available
     */
    public static Response getUserV2(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List userList = session.createQuery( "from User U where U.id is "+_id ).list();
        transaction.commit();
        User user = (User) userList.get(0);
        return Response.ok().entity(user).build();
    }

    /**
     * delete user if possible
     * @param _user specific user object, contains highscore
     * @return Response message
     */
    public static Response deleteUser(UserWithHighscore _user)
    {
        return  HibernateUtil.deleteObj(_user);
    }


    /**
     * internal db-function
     * check of user password combination exist
     * @param _login object with String username and String password
     * @return Response with user if available
     */
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


    /**
     * internal db-function
     * add a single user to the database
     * add highscore for new user
     * @param _user user object with name, password, email ...
     * @return response message 200 if ok, else 304
     */
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
    }


    /**
     * internal function to test adding a user without android client
     * @return Response with 200 if seccessful
     */
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


    /**
     * internal function
     * check if there is still a user with given name, has to be unique
     * used for addUser
     * @param _name String name of the user
     * @return true if exist, else false
     */
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

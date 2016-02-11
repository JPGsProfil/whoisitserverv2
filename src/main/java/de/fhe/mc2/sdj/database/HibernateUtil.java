package de.fhe.mc2.sdj.database;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

/**
 * helper-class for Hibernate
 * creates a SessionFactory-object which in turn can open up new Session's
 */
public class HibernateUtil
{
    private static SessionFactory sessionFactory;


    /**
     * internal db-function
     * create a session factory
     * use the same the whole time
     * create a new if not exist
     * @return sessionFactory
     */
    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }








    /**
     * outsourced function to add an object to the database
     * @param _obj Object of database-entity (one of the model package)
     * @return Response with 200 if succesful, else 304 not modified
     */
    public static Response addToDB(Object _obj)
    {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction currentTransaction = null;
        try
        {
            currentTransaction = session.beginTransaction();
            session.save(_obj);
            currentTransaction.commit();
        }
        catch (HibernateException e)
        {
            //TransactionStatus transactionStatus = currentTransaction.getStatus();
            if (currentTransaction!=null) currentTransaction.rollback();
            e.printStackTrace();
            return Response.notModified().build();
        }
        finally
        {
            session.close();
        }

        return Response.ok().build();
    }


    /**
     * create a new session factory at the beginning
     * map the config from hibernate.cfg.xml
     * @return sessionFactory with correct config
     */
    private static SessionFactory buildSessionFactory()
    {
        try
        {
            // Create session factory from cfg.xml
            Configuration configuration = new Configuration();

            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = null;

            serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();


            Metadata Meta = new MetadataSources(serviceRegistry)
                    .getMetadataBuilder()
                    .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                    .build();

            SessionFactory sessionFactory = Meta.getSessionFactoryBuilder()
                    .build();

            return sessionFactory;
        }
        catch (Throwable ex)
        {
            System.err.println("Initial session factory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }





    /**
     * outsourced function to delete a database entry by given object
     * @param _object given object wich should be deleted (from model package)
     * @return Response with 200 if ok, else not modified
     */
    public static Response deleteObj(Object _object)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction currentTransaction = null;
        try
        {
            currentTransaction = session.beginTransaction();
            session.delete(_object);
            currentTransaction.commit();
        }
        catch (HibernateException e)
        {
            //TransactionStatus transactionStatus = currentTransaction.getStatus();
            if (currentTransaction!=null) currentTransaction.rollback();
            e.printStackTrace();
            return Response.notModified().build();
        }
        finally
        {
            session.close();
        }

        return Response.ok().build();
    }




    /**
     * old function to delete a database entry be id
     * @param type Entity Type (model)
     * @param id id of the Object as Integer
     * @return Response with ok if successful, else not modified
     */
    public static Response deleteById(Class<?> type, Serializable id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Object persistentInstance = session.load(type, id);
        if (persistentInstance != null)
        {
            session.delete(persistentInstance);
            return Response.ok().build();
        }
        return Response.notModified().build();
    }




    /**
     * make db transaction
     * contains exception
     * userful for multiple db changes (eg. write into 2 tables)
     * not used anymore
     * @param _query sql query statement
     * @return list of objects, have to be cast
     */
    public static List dbTransaction(String _query)
    {
        List list = null;
        Session currentSession = HibernateUtil.getSessionFactory().openSession();
        Transaction currentTransaction = null;
        try{
            currentTransaction = currentSession.beginTransaction();
            list = currentSession.createQuery(_query).list();
            currentTransaction.commit();
        }catch (HibernateException e) {
            if (currentTransaction!=null) currentTransaction.rollback();
            e.printStackTrace();
        }finally {
            currentSession.close();
        }
        return list;

    }

}

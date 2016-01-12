package database;



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
import java.util.List;

import java.util.Iterator;


public class HibernateUtil
{
    private static SessionFactory sessionFactory;



    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }


    /**
     * make db transaction
     * contains exception
     * userful for multiple db changes (eg. write into 2 tables)
     * @param _query sql query statement
     * @return list of objects, have to be cast
     */
    public static List DBTransaction(String _query)
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

}

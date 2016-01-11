package database;

/**
 * Created by Jean on 07.01.2016.
 */

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Iterator;
import java.util.List;


@Path( "/db" )
public class Test_Hibernate
{
    //private static final Logger LOGGER = Logger.getLogger("Hibernate-Tutorial");
    //public static void main(String[] args)

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path( "test1" )
    public Response run2()
    {
        Session testSessionFactory = HibernateUtil.getSessionFactory().openSession();
        testSessionFactory.beginTransaction();
        Test_DB_User user1 = new Test_DB_User();
        user1.setStrLastName("i");
        user1.setStrFirstName("Homer");
        testSessionFactory.save(user1);
        String status = testSessionFactory.getStatistics().toString();
        testSessionFactory.getTransaction().commit();
        testSessionFactory.close();

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "test2" )
    public List TestReadUserFromDB()
    {
        List users = null;
        Session currentSession = HibernateUtil.getSessionFactory().openSession();
        Transaction currentTransaction = null;
        try{
            currentTransaction = currentSession.beginTransaction();
            users = currentSession.createQuery("FROM Test_DB_User").list();
            for (Iterator iterator =
                 users.iterator(); iterator.hasNext();){
                Test_DB_User currentUser = (Test_DB_User) iterator.next();
                System.out.print("First Name: " + currentUser.getStrFirstName());
                System.out.println("  Last Name: " + currentUser.getStrLastName());
            }
            currentTransaction.commit();
        }catch (HibernateException e) {
            if (currentTransaction!=null) currentTransaction.rollback();
            e.printStackTrace();
        }finally {
            currentSession.close();
        }

        return users;
    }


    /////////////////////////////////
    ///////////////////////////////




        /*
         void FirstTransaction()
        {
            try {
                Transaction transaction = session.getTransaction();
                transaction.begin();

                // hier meine Aktion!!!

                transaction.commit();
            } catch (Exception e) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw e;
            }
        }






        Metadata Meta = new MetadataSource(serviceRegistry)
                .addAnnotadedClass(DBRole.class)
                .addAnnotadedClassName("de.fhe.database.DBRole")
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        Session session=new AnnotationConfiguration()
                .configure().buildSessionFactory().openSession();

        Transaction t=session.beginTransaction();

        Test_DB_User newUser1 = new Test_DB_User();
        newUser.setStrFirstName("Benny");
        newUser.setStrLastName("Neugebauer");

        Employee e2=new Employee();
        e2.setId(1002);
        e2.setFirstName("vimal");
        e2.setLastName("jaiswal");

        session.persist(e1);
        session.persist(e2);

        t.commit();
        session.close();
        System.out.println("successfully saved");

        */

        /*
        SessionFactory sf       = HibernateUtil.getSessionFactory();
        Session session         = null;
        Transaction transaction = null;

        // Neuen Benutzer in Datenbank speichern:
        try
        {
            session             = sf.getCurrentSession();
            transaction         = session.beginTransaction();
            session.save(newUser);
            transaction.commit();
        }
        catch (Exception e)
        {
            // rollback(transaction);
            System.out.println(e.getMessage());
        }*/
}
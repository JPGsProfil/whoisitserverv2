package database;

import Model.Attribute;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Jean on 03.02.2016.
 */
public class DB_Attribute
{
    public static Attribute getAttributeById(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List userList = session.createQuery( "from Attribute A where A.id is "+_id ).list();
        transaction.commit();
        Attribute attribute = (Attribute) userList.get(0);
        return attribute;
    }

    public static Response addAttribute(Attribute _attribute)
    {
        return HibernateUtil.addToDB(_attribute);
    }

    public static Response addAttributes(List<Attribute> _attributes)
    {
        return HibernateUtil.addToDB(_attributes);
    }
}

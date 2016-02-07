package database;

import Marshalling.ReturnString;
import Model.Attribute;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jean on 03.02.2016.
 */
public class DB_Attribute
{
    public static Response getAttributeById(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List attributeList = session.createQuery( "from Attribute A where A.id is "+_id ).list();
        transaction.commit();
        Attribute attribute = new Attribute();
        if(attributeList.isEmpty())
        {
            return Response.noContent().entity(attribute).build();
        }
        else
        {
            attribute = (Attribute) attributeList.get(0);
            return Response.ok().entity(attribute).build();
        }
    }

    public static Response getAttributesByCardSetId(Integer _cardSetId)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List attributes = session.createQuery( "SELECT name from Attribute A where A.card.cardSet.id is "+_cardSetId + " group by name" ).list();
        transaction.commit();
        List<ReturnString> returnStrings = new ArrayList<>();
        for(int index = 0; index < attributes.size(); index++)
        {
            ReturnString tempString = new ReturnString((String)attributes.get(index));
            returnStrings.add(tempString);
        }
        if(returnStrings.isEmpty())
        {
            return Response.noContent().entity(returnStrings).build();
        }
        else
        {
            return Response.ok().entity(returnStrings).build();
        }
    }

    public static Response getAttributesAndValuesByCardSetId(Integer _cardSetId)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List attributes = session.createQuery( "from Attribute A where A.card.cardSet.id is "+_cardSetId ).list();
        transaction.commit();
        if(attributes.isEmpty())
        {
            return Response.noContent().entity(attributes).build();
        }
        else
        {
            return Response.ok().entity(attributes).build();
        }
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

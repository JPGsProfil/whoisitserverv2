package de.fhe.mc2.sdj.database;

import de.fhe.mc2.sdj.marshalling.ReturnString;
import de.fhe.mc2.sdj.model.Attribute;
import de.fhe.mc2.sdj.model.Card;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jean on 03.02.2016.
 */

/**
 * database class for attributes which are part of the card
 */
public class DB_Attribute
{
    /**
     * get a single attribute by it's id
     * @param _id id of the given Attribute as Integer
     * @return Response with Attribute-obj
     */
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
        _attribute.setId(null);
        // join cardset from JSON id, needed for hibernate sql
        Card card = (Card) DB_Card.getCard(_attribute.getCardId()).getEntity();
        System.out.println("Card name ermittelt "+card.getName());
        _attribute.setCard(card);

        // prepare children if exist
        if(_attribute.getValue() != null)
        {
            // for cascade save: Value needs Attribute-obj for foreign key
            _attribute.getValue().setAttribute(_attribute);
            // set id value primary key null (if android makes a mistake, otherwise causes update instead of insert
            _attribute.getValue().setId(null);
        }
        return HibernateUtil.addToDB(_attribute);
    }

}

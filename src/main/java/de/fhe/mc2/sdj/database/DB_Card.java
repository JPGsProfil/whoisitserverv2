package de.fhe.mc2.sdj.database;

import de.fhe.mc2.sdj.modelz.Attribute;
import de.fhe.mc2.sdj.modelz.Card;
import de.fhe.mc2.sdj.modelz.CardSet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Connection between Card-Model and database
 */
public class DB_Card
{
    /**
     * internal db-function
     * get a single card by it's id
     * @param _id card id as Integer
     * @return Response with card-object
     */
    public static Response getCard(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List cardList = session.createQuery( "from Card C where C.id is "+_id ).list();
        transaction.commit();
        Card card = new Card();
        if(cardList.isEmpty())
        {
            return Response.noContent().entity(card).build();
        }
        else
        {
            card = (Card) cardList.get(0);
            return Response.ok().entity(card).build();
        }
    }


    /**
     * internal db-function
     * get List of cards by given cardSetId
     * @param _cardSetId id of the cardSet as Integer
     * @return Response with list of cards
     */
    public static Response getCards(Integer _cardSetId)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List cardList = session.createQuery( "from Card C where C.cardSet.id is "+_cardSetId ).list();
        transaction.commit();
        if(cardList.isEmpty())
        {
            return Response.noContent().entity(cardList).build();
        }
        return Response.ok().entity(cardList).build();
    }


    /**
     * internal db-function
     * add a single card to the database
     * @param _card Card-object wich should be added
     * @return Response with 200 if ok
     */
    public static Response addCard(Card _card)
    {
        _card.setId(null);
        CardSet cardSet = (CardSet) DB_CardSet.getCardSet(_card.getCardSetId()).getEntity();
        System.out.println("cardset name ermittelt "+cardSet.getName());
        _card.setCardSet(cardSet);
        // prepare attribute and value objects for adding to db
        // todo: auslagern, da auch in Cardset verwendet
        if(_card.getAttributeList() != null)
        {
            List<Attribute> attributes = _card.getAttributeList();
            for (int attributeIndex = 0; attributeIndex < attributes.size(); attributeIndex ++)
            {
                // for cascade save: each Attribute obj needs Card-obj for foreign key
                attributes.get(attributeIndex).setCard(_card);
                // set attribute id primary key null (if android makes a mistake, otherwise causes update instead of insert
                attributes.get(attributeIndex).setId(null);
                if(attributes.get(attributeIndex).getValue() != null)
                {
                    // for cascade save: Value needs Attribute-obj for foreign key
                    attributes.get(attributeIndex).getValue().setAttribute(attributes.get(attributeIndex));
                    // set id value primary key null (if android makes a mistake, otherwise causes update instead of insert
                    attributes.get(attributeIndex).getValue().setId(null);
                }
            }
        }
        return HibernateUtil.addToDB(_card);
    }

    /*
    public static Response addCards(List<Card> _cardSet)
    {
        //return HibernateUtil.addToDB(HibernateUtil.addToDB(_cardSet));
        Response currentResponse = null;
        for(int index = 0; index < _cardSet.size(); index++)
        {
            currentResponse = HibernateUtil.addToDB(_cardSet.get(index));
            if(currentResponse.equals(Response.notModified().build()))
            {
                System.out.println("failed at index: "+index);
                return Response.notModified().build();
            }
        }
        return currentResponse;
    }*/



}

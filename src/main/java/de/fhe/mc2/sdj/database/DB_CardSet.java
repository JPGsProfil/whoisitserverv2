package de.fhe.mc2.sdj.database;

import de.fhe.mc2.sdj.model.Attribute;
import de.fhe.mc2.sdj.model.Card;
import de.fhe.mc2.sdj.model.CardSet;
import de.fhe.mc2.sdj.model.Value;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jean on 28.01.2016.
 */
public class DB_CardSet
{
    /**
     * internal db-function
     * get a single card by cardid
     * @param _id card id
     * @return Response with Card-obj if available
     */
    public static Response getCardSet(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
    // CardSet = nicht SQL Tabelle, sondern CardSet
        List cardList = session.createQuery( "from CardSet C where C.id is "+_id ).list();
        transaction.commit();
        CardSet cardSet = new CardSet();
        if(cardList.isEmpty())
        {
            return Response.noContent().entity(cardSet).build();
        }
        else
        {
            cardSet = (CardSet) cardList.get(0);
            return Response.ok().entity(cardSet).build();
        }
    }


    /**
     * internal db-function
     * get all cards which has been added by specific user
     * @param _userId id of the user
     * @return Response with List of cards
     */
    public static Response getCardSetsByUserId(Integer _userId)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        // CardSet = nicht SQL Tabelle, sondern CardSet
        List cardList = session.createQuery( "from CardSet C where C.userId is " + _userId ).list();
        transaction.commit();
        if(cardList.isEmpty())
        {
            return Response.noContent().entity(cardList).build();
        }
        else
        {
            return Response.ok().entity(cardList).build();
        }
    }


    /**
     * internal db-function
     * add a cardset
     * called from api
     * contains cardset obj
     * may contain cards, attributes and value
     * @param _cardSet CardSet-object mapped from JSON
     * @return Response with 200, 409 or 304
     */
    public static Response addCardSet(CardSet _cardSet)
    {
        // set cardset id primary key null (if android makes a mistake, otherwise would cause update instead of insert
        _cardSet.setId(null);

        // begin of: Preparation necessary if cardset contain cards
        if(_cardSet.getCards() != null)
        {
            // list to check if 2 cards with same name
            List<String> cardNames = new ArrayList();
            // cascade all cards (only possible if one or more
            for(int cardIndex = 0; cardIndex < _cardSet.getCards().size(); cardIndex ++)
            {
                // check if cardset already contains card with this name
                if(cardNames.contains(_cardSet.getCards().get(cardIndex).getName()))
                {
                    return Response.status(Response.Status.CONFLICT).build();
                }
                else
                {
                    // add cardname to list to compare with others
                    cardNames.add(_cardSet.getCards().get(cardIndex).getName());
                }
                // for cascade save: each Card obj needs Cardset-obj for foreign key
                _cardSet.getCards().get(cardIndex).setCardSet(_cardSet);
                //  set card id primary key null
                _cardSet.getCards().get(cardIndex).setId(null);
                // begin of: check if attributes given
                if(_cardSet.getCards().get(cardIndex).getAttributeList() != null)
                {
                    List<String> AttributeNames = new ArrayList();
                    //cascading only possible if there are some
                    List<Attribute> attributes = _cardSet.getCards().get(cardIndex).getAttributeList();
                    for (int attributeIndex = 0; attributeIndex < attributes.size(); attributeIndex ++)
                    {
                        // check if card has already same Attribute
                        if(AttributeNames.contains(attributes.get(attributeIndex).getName()))
                        {
                            return Response.status(Response.Status.CONFLICT).build();
                        }
                        else
                        {
                            AttributeNames.add(attributes.get(attributeIndex).getName());
                        }
                        // for cascade save: each Attribute obj needs Card-obj for foreign key
                        attributes.get(attributeIndex).setCard(_cardSet.getCards().get(cardIndex));
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
                } // end of: check if attributes given
            }  // End ofcascade all cards (only possible if one or more
        }   // end of: Preparation necessary if cardset contain cards

        // add prepared object to the database
        return HibernateUtil.addToDB(_cardSet);
    }


}

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


    public static Response addCardSet(CardSet _cardSet)
    {
        // set cardset id primary key null (if android makes a mistake, otherwise would cause update instead of insert
        _cardSet.setId(null);
        if(_cardSet.getCards() != null)
        {
            // cascade all cards (only possible if one or more
            for(int cardIndex = 0; cardIndex < _cardSet.getCards().size(); cardIndex ++)
            {
                // for cascade save: each Card obj needs Cardset-obj for foreign key
                _cardSet.getCards().get(cardIndex).setCardSet(_cardSet);
                //  set card id primary key null
                _cardSet.getCards().get(cardIndex).setId(null);
                if(_cardSet.getCards().get(cardIndex).getAttributeList() != null)
                {
                    //cascading only possible if there are some
                    List<Attribute> attributes = _cardSet.getCards().get(cardIndex).getAttributeList();
                    for (int attributeIndex = 0; attributeIndex < attributes.size(); attributeIndex ++)
                    {
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
                }
            }
        }

        //_cardSet.setCards(null);
        return HibernateUtil.addToDB(_cardSet);

    }


    public static Response addCardSetTest()
    {
        /*
        CardSet cardSet = new CardSet(55, "test444", 1);
        Card card1 = new Card(5, 1, "Karte 11", "Super Bild" );
        Card card2 = new Card(6, 1, "Karte 12", "Super Bild 2" );
        //Card card3 = new Card(7, 5, "Karte 3", "Super Bild 3" );
        HibernateUtil.addToDB(cardSet);
        HibernateUtil.addToDB(card1);
        HibernateUtil.addToDB(HibernateUtil.addToDB(card2));*/


        CardSet cardSet = new CardSet(null, "Homer5", 1);
        Card card1 = new Card( null, "Karte Homer 1111", "Super Bild", cardSet );
        Card card2 = new Card( null, "Karte Homer 1112", "Super Bild 2", cardSet );
        Card card3 = new Card( null, "Karte Homer 1113", "Super Bild 3", cardSet );
        List<Card> cardList = new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);
        /*
        for(int index = 0; index < cardList.size(); index ++)
        {
            HibernateUtil.addToDB(cardList.get(index));
        }*/
        cardSet.setCards(cardList);
        //HibernateUtil.addToDB(card1);
        return HibernateUtil.addToDB(cardSet);

        /*
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction currentTransaction = null;

        currentTransaction = session.beginTransaction();
        session.save(cardSet);
        currentTransaction.commit();

        session.close();
        */

        //return Response.ok().build();

    /*
        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);
        cardSet.setCards(cardList);
        for (int index = 0; index < cardList.size(); index++ )
        {
            HibernateUtil.addToDB(HibernateUtil.addToDB(cardList.get(index)));
        }*/

        //return Response.ok().build();
    }
}

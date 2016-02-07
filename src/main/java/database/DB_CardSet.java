package database;

import Model.Card;
import Model.CardSet;
import Model.Highscore;
import Model.User;
import org.hibernate.HibernateException;
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
    // CardSet = nicht SQL Tabelle, sondern Model.CardSet
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

    public static Response getCardSetByUserId(Integer _userId)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        // CardSet = nicht SQL Tabelle, sondern Model.CardSet
        List cardList = session.createQuery( "from CardSet C where C.userId is " + _userId ).list();
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


    public static Response addCardSet(CardSet _cardSet)
    {
        _cardSet.setId(null);
        return HibernateUtil.addToDB(HibernateUtil.addToDB(_cardSet));

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

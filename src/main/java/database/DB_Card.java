package database;

import Model.Card;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Jean on 28.01.2016.
 */
public class DB_Card
{
    public static Card getCard(Integer _id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List cardList = session.createQuery( "from Card C where C.id is "+_id ).list();
        transaction.commit();
        Card card = (Card) cardList.get(0);
        return card;
    }


    public static List<Card> getCards(Integer _cardSetId)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List cardList = session.createQuery( "from Card C where C.cardSetId is "+_cardSetId ).list();
        transaction.commit();
        return cardList;
    }



    public static Response addCard(Card _cardSet)
    {
        return HibernateUtil.addToDB(HibernateUtil.addToDB(_cardSet));
    }

    public static Response addCards(List<Card> _cardSet)
    {
        //return HibernateUtil.addToDB(HibernateUtil.addToDB(_cardSet));
        Response currentResponse = null;
        for(int index = 0; index < _cardSet.size(); index++)
        {
            currentResponse = HibernateUtil.addToDB(HibernateUtil.addToDB(_cardSet.get(index)));
            if(currentResponse.equals(Response.notModified().build()))
            {
                System.out.println("failed at index: "+index);
                return Response.notModified().build();
            }
        }
        return currentResponse;
    }


}

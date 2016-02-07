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
     * List<Card>
     * @param _cardSetId
     * @return
     */
    public static Response getCards(Integer _cardSetId)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction  = session.beginTransaction();
        List cardList = session.createQuery( "from Card C where C.cardSetId is "+_cardSetId ).list();
        transaction.commit();
        if(cardList.isEmpty())
        {
            return Response.noContent().entity(cardList).build();
        }
        return Response.ok().entity(cardList).build();
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
            currentResponse = HibernateUtil.addToDB(_cardSet.get(index));
            if(currentResponse.equals(Response.notModified().build()))
            {
                System.out.println("failed at index: "+index);
                return Response.notModified().build();
            }
        }
        return currentResponse;
    }


    public static Response addCardsV2(List<Card> _cardSet)
    {
        return HibernateUtil.addToDB(_cardSet);
    }


}

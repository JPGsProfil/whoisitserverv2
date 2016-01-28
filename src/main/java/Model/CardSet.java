package Model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Jean on 11.12.2015.
 */
@Entity
//@SequenceGenerator(name="ID", initialValue = 1, allocationSize = 1)
@Table(name = "Cardset")
public class CardSet implements java.io.Serializable
{



    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public int getId()
    {
        return id;
    }

    @Column(name = "Name")
    public String getName()
    {
        return name;
    }

    @Column(name = "User_ID")
    public int getUserId()
    {
        return userId;
    }







    public void setId(int _id)
    {
        this.id = _id;
    }

    public void setName(String _name)
    {
        this.name = _name;
    }

    public void setUserId(int _userId)
    {
        this.userId = _userId;
    }

    public CardSet()
    {
    }

    public CardSet(int _id, String _name, int _userId, List<Card> _cards)
    {
        this.id = _id;
        this.name = _name;
        this.userId = _userId;
        cards = _cards;
    }


    // cardSet = Member von Card
    @OneToMany(mappedBy="cardSet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Card> getCards()
    {
        return cards;
    }

    public void setCards(List<Card> cards)
    {
        this.cards = cards;
    }



    private int id;
    private String name;
    private int userId;
    private List<Card> cards;


}

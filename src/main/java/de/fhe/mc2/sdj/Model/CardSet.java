package de.fhe.mc2.sdj.model;

import javax.persistence.*;
import java.util.List;

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
    public Integer getId()
    {
        return id;
    }

    @Column(name = "Name")
    public String getName()
    {
        return name;
    }

    @Column(name = "User_ID")
    public Integer getUserId()
    {
        return userId;
    }







    public void setId(Integer _id)
    {
        this.id = _id;
    }

    public void setName(String _name)
    {
        this.name = _name;
    }

    public void setUserId(Integer _userId)
    {
        this.userId = _userId;
    }

    public CardSet()
    {
        this.id = null;
        this.name = null;
        this.userId = null;
        this.cards = null;
    }

    public CardSet(Integer _id, String _name, Integer _userId)
    {
        this.id = _id;
        this.name = _name;
        this.userId = _userId;
    }

    public CardSet(Integer _id, String _name, Integer _userId, List<Card> _cards)
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



    private Integer id;
    private String name;
    private Integer userId;
    private List<Card> cards;


}

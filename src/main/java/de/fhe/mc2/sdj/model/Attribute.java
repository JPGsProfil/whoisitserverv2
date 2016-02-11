package de.fhe.mc2.sdj.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

/**
 * model of the attribute
 * attributes are part of a card
 */
@Entity
@Table(name = "Attribute")
public class Attribute
{



    @Id
    @GeneratedValue
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

    @Column(name = "UpdateDate")
    public Date getUpdateDate()
    {
        return updateDate;
    }

    @Transient
    public Integer getCardId()
    {
        return cardId;
    }


    @OneToOne(mappedBy="attribute", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Value getValue()
    {
        return value;
    }


    @ManyToOne
    @JsonBackReference
    @JoinColumn (name = "Card_ID",referencedColumnName = "ID")
    public Card getCard()
    {
        return card;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

    public void setCardId(Integer cardId)
    {
        this.cardId = cardId;
    }


    public void setCard(Card card)
    {
        this.card = card;
    }

    public void setValue(Value value)
    {
        this.value = value;
    }



    public Attribute()
    {
        this.id = null;
        this.name = null;
        this.cardId = null;
        this.card = null;
    }


    public Attribute( String _name, Date _updateDate)
    {
        this.id = null;
        this.name = _name;
        this.updateDate = _updateDate;
    }



    private Integer id;
    private String name;
    private Date updateDate;
    private Value value;
    private Integer cardId;
    private Card card;

}

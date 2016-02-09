package de.fhe.mc2.sdj.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jean on 11.12.2015.
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


    @OneToOne(mappedBy="attribute", cascade=CascadeType.PERSIST)
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


    public void setCard(Card card)
    {
        this.card = card;
    }



    public Attribute()
    {

    }


    public Attribute( String name, Date updateDate)
    {
        this.id = null;
        this.name = name;
        this.updateDate = updateDate;
    }

    public void setValue(Value value)
    {
        this.value = value;
    }

    private Integer id;
    private String name;
    private Date updateDate;
    private Value value;




    private Card card;

}

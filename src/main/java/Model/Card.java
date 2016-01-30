package Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by Jean on 11.12.2015.
 */

@Entity
@Table(name = "Card")
//@Entity(name = "Card")
//@Table(name="Card", uniqueConstraints={@UniqueConstraint(columnNames = {"ID"})})
public class Card
{

    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Integer getId()
    {
        return id;
    }


    @Column(name = "Image")
    public String getImage()
    {
        return image;
    }

    @Column(name = "Name")
    public String getName()
    {
        return name;
    }

    @ManyToOne
    @JoinColumn (name = "Cardset_ID",referencedColumnName = "ID")
    public CardSet getCardSet()
    {
        return cardSet;
    }

    public void setCardSet(CardSet cardSet)
    {
        this.cardSet = cardSet;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Card(Integer _id, String _name, String _image)
    {
        this.id = _id;
        this.name = _name;
        this.image = _image;
        this.cardSet = null;
    }

    public Card(Integer _id, String _name, String _image, CardSet _cardSet)
    {
        this.id = _id;
        this.name = _name;
        this.image = _image;
        this.cardSet = _cardSet;
    }

    public Card()
    {

    }



    Integer id;
    String name;
    String image;

    CardSet cardSet;


}

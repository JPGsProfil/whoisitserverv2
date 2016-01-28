package Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by Jean on 11.12.2015.
 */

@Entity
@Table(name = "Card")
public class Card
{

    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public int getId()
    {
        return id;
    }

    @Column(name = "Cardset_ID")
    public int getCardSetId()
    {
        return cardSetId;
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

    public void setCardSetId(int cardSetId)
    {
        this.cardSetId = cardSetId;
    }

    public void setId(int id)
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

    public Card(int _id, int _cardSetId, String _name, String _image)
    {
        this.id = _id;
        this.cardSetId = _cardSetId;
        this.name = _name;
        this.image = _image;
    }

    public Card()
    {

    }



    int id;
    int cardSetId;
    String name;
    String image;

    @ManyToOne
    @JsonBackReference
    @JoinColumn (name = "Cardset_ID", referencedColumnName = "ID", nullable = false, updatable = false, insertable = false)
    public CardSet getCardSet()
    {
        return cardSet;
    }

    public void setCardSet(CardSet cardSet)
    {
        this.cardSet = cardSet;
    }

    CardSet cardSet;


}

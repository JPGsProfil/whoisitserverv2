package Model;

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

    int id;
    int cardSetId;
    String name;
    String image;






    public Card(int _id, int _cardSetId, String _name, String _image)
    {
        this.id = _id;
        this.cardSetId = _cardSetId;
        this.name = _name;
        this.image = _image;
    }
}

package Model;

/**
 * Created by Jean on 11.12.2015.
 */


public class Card
{

    public int getCardSetId() {
        return cardSetId;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setCardSetId(int cardSetId) {
        this.cardSetId = cardSetId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
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

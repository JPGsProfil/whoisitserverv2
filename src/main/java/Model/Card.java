package Model;

/**
 * Created by Jean on 11.12.2015.
 */


public class Card
{
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    int ID;
    int CardSet_ID;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getCardSet_ID() {
        return CardSet_ID;
    }

    public void setCardSet_ID(int cardSet_ID) {
        CardSet_ID = cardSet_ID;
    }

    String Name;
    String Image;

    public Card(int _ID, int _CardSet_ID, String _Name, String _Image)
    {
        this.ID = _ID;
        this.CardSet_ID = _CardSet_ID;
        this.Name = _Name;
        this.Image = _Image;
    }

}

package Model;

/**
 * Created by Jean on 11.12.2015.
 */
public class Card
{
    public int GetID() {
        return ID;
    }

    public void SetID(int ID) {
        this.ID = ID;
    }

    int ID;
    int CardSet_ID;

    public String GetName() {
        return Name;
    }

    public void SetName(String name) {
        Name = name;
    }

    public String GetImage() {
        return Image;
    }

    public void SetImage(String image) {
        Image = image;
    }

    public int GetCardSet_ID() {
        return CardSet_ID;
    }

    public void SetCardSet_ID(int cardSet_ID) {
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

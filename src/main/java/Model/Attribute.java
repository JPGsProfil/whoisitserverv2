package Model;

/**
 * Created by Jean on 11.12.2015.
 */
public class Attribute
{
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCardSet_ID() {
        return CardSet_ID;
    }

    public void setCardSet_ID(int cardSet_ID) {
        CardSet_ID = cardSet_ID;
    }

    int ID;
    String Name;
    int CardSet_ID;
}

package Model;

/**
 * Created by Jean on 11.12.2015.
 */
public class Attribute
{
    public int GetID() {
        return ID;
    }

    public void SetID(int ID) {
        this.ID = ID;
    }

    public String GetName() {
        return Name;
    }

    public void SetName(String name) {
        Name = name;
    }

    public int GetCardSet_ID() {
        return CardSet_ID;
    }

    public void SetCardSet_ID(int cardSet_ID) {
        CardSet_ID = cardSet_ID;
    }

    int ID;
    String Name;
    int CardSet_ID;
}

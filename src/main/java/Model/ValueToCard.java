package Model;

/**
 * Created by Jean on 11.12.2015.
 */
public class ValueToCard
{
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCard_ID() {
        return Card_ID;
    }

    public void setCard_ID(int card_ID) {
        Card_ID = card_ID;
    }

    public int getValue_ID() {
        return Value_ID;
    }

    public void setValue_ID(int value_ID) {
        Value_ID = value_ID;
    }

    int ID;
    int Card_ID;
    int Value_ID;
}

package Model;

/**
 * Created by Jean on 11.12.2015.
 */
public class ValueToCard
{
    public int getID() {
        return ID;
    }

    public void SetID(int ID) {
        this.ID = ID;
    }

    public int GetCard_ID() {
        return Card_ID;
    }

    public void SetCard_ID(int card_ID) {
        Card_ID = card_ID;
    }

    public int GetValue_ID() {
        return Value_ID;
    }

    public void SetValue_ID(int value_ID) {
        Value_ID = value_ID;
    }

    int ID;
    int Card_ID;
    int Value_ID;
}

package Model;

/**
 * Created by Jean on 11.12.2015.
 */
public class Value
{
    public int getID() {
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

    public int GetAttribut_ID() {
        return Attribut_ID;
    }

    public void SetAttribut_ID(int attribut_ID) {
        Attribut_ID = attribut_ID;
    }

    int ID;
    String Name;
    int Attribut_ID;
}

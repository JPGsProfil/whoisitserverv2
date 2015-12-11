package Model;

/**
 * Created by Jean on 11.12.2015.
 */
public class Value
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

    public int getAttribut_ID() {
        return Attribut_ID;
    }

    public void setAttribut_ID(int attribut_ID) {
        Attribut_ID = attribut_ID;
    }

    int ID;
    String Name;
    int Attribut_ID;
}

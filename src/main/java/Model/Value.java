package Model;

/**
 * Created by Jean on 11.12.2015.
 */
public class Value
{
    public int getID() {
        return ID;
    }

    public void setID(int _ID) {
        this.ID = _ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String _name) {
        Name = _name;
    }

    public int getAttribut_ID() {
        return Attribut_ID;
    }

    public void setAttribut_ID(int _attribut_ID) {
        Attribut_ID = _attribut_ID;
    }

    int ID;
    String Name;
    int Attribut_ID;
}

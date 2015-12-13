package Model;

/**
 * Created by Jean on 11.12.2015.
 */
public class CardSet
{

    int ID;
    String Name;
    int User_ID;

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

    public int GetUser_ID() {
        return User_ID;
    }

    public void SetUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public CardSet(int _ID, String _Name, int _User_ID)
    {
        this.ID = _ID;
        this.Name = _Name;
        this.User_ID = _User_ID;
    }


}

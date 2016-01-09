package Model;

/**
 * Created by Jean on 11.12.2015.
 */
public class CardSet
{

    int ID;
    String name;
    int user_ID;

    public int GetID()
    {
        return ID;
    }

    public void SetID(int _ID)
    {
        this.ID = _ID;
    }

    public String GetName()
    {
        return name;
    }

    public void SetName(String _name)
    {
        name = _name;
    }

    public int GetUser_ID()
    {
        return user_ID;
    }

    public void SetUser_ID(int _user_ID)
    {
        user_ID = _user_ID;
    }

    public CardSet(int _ID, String _name, int _user_ID)
    {
        this.ID = _ID;
        this.name = _name;
        this.user_ID = _user_ID;
    }


}

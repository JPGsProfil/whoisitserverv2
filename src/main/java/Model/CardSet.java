package Model;

/**
 * Created by Jean on 11.12.2015.
 */
public class CardSet
{

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    int id;
    String name;
    int userId;


}

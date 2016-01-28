package Model;

/**
 * Created by Jean on 11.12.2015.
 */
public class Attribute
{

    public int getCardSetId() {
        return cardSetId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCardSetId(int cardSetId) {
        this.cardSetId = cardSetId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attribute(int cardSetId, int id, String name) {
        this.cardSetId = cardSetId;
        this.id = id;
        this.name = name;
    }

    int id;
    String name;
    int cardSetId;
}

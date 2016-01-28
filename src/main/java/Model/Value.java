package Model;

/**
 * Created by Jean on 11.12.2015.
 */
public class Value
{

    public int getAttributeId() {
        return attributeId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Value(int attributeId, int id, String name) {
        this.attributeId = attributeId;
        this.id = id;
        this.name = name;
    }

    int id;
    String name;
    int attributeId;
}

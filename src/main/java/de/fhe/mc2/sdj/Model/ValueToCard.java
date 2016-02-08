package de.fhe.mc2.sdj.model;

/**
 * Created by Jean on 11.12.2015.
 */
public class ValueToCard
{


    public int getCardId() {
        return cardId;
    }

    public int getId() {
        return id;
    }

    public int getValueId() {
        return valueId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValueId(int valueId) {
        this.valueId = valueId;
    }

    public ValueToCard(int cardId, int id, int valueId) {
        this.cardId = cardId;
        this.id = id;
        this.valueId = valueId;
    }

    int id;
    int cardId;
    int valueId;
}

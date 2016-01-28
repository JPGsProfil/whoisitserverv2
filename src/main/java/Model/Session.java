package Model;

/**
 * Created by Jean on 11.12.2015.
 */
public class Session
{
    public int getCardset_ID() {
        return cardsetId;
    }

    public boolean isHasEnded() {
        return hasEnded;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public int getId() {
        return id;
    }

    public int getPlayer1_ID() {
        return player1_ID;
    }

    public int getPlayer2_ID() {
        return player2_ID;
    }

    public void setCardset_ID(int cardsetId) {
        this.cardsetId = cardsetId;
    }

    public void setHasEnded(boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlayer1_ID(int player1_ID) {
        this.player1_ID = player1_ID;
    }

    public void setPlayer2_ID(int player2_ID) {
        this.player2_ID = player2_ID;
    }

    int id;
    int player1_ID;
    int player2_ID;
    int cardsetId;
    boolean hasStarted;
    boolean hasEnded;
}

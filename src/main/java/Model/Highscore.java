package Model;

/**
 * Created by Jean on 11.12.2015.
 */
public class Highscore
{
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMatchesWon() {
        return MatchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        MatchesWon = matchesWon;
    }

    public int getMatchesLost() {
        return MatchesLost;
    }

    public void setMatchesLost(int matchesLost) {
        MatchesLost = matchesLost;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    int ID;
    int MatchesWon;
    int MatchesLost;
    int User_ID;
}

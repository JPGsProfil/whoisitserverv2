package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * Created by Jean on 08.01.2016.
 */
//@Embeddable
//@Table(name="Highscore")
@Entity
@Table(name="Highscore")
public class Highscore
{
    //@Column(name="ID", nullable=false)
    int ID;



    @Id
    @Column(name="User_ID", unique=true, nullable=false)
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters = @Parameter(name="property", value="user"))
    Integer user_ID;

    @Column(name="MatchesWon", nullable=false)
    Integer matchesWon;

    @Column(name="MatchesLost", nullable=false)
    Integer matchesLost;



    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonBackReference
    private User user;




    public Integer getUser_ID()
    {
        return user_ID;
    }

    public void setUser_ID(Integer user_ID) {
        this.user_ID = user_ID;
    }



    public Highscore()
    {
        matchesWon = 0;
        matchesLost = 0;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public int getID() {
        return ID;
    }


    public Integer getMatchesWon()
    {
        return matchesWon;
    }


    public Integer getMatchesLost()
    {
        return matchesLost;
    }

    public void setID(int _ID)
    {
        this.ID = _ID;
    }



    public void setMatchesWon(Integer _matchesWon)
    {
        this.matchesWon = _matchesWon;
    }



    public void setMatchesLost(Integer _matchesLost)
    {
        this.matchesLost = _matchesLost;
    }





}

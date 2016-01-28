package Model;

import javax.persistence.*;

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
public class Highscore implements java.io.Serializable
{



    @Id
    @GeneratedValue
    @Column(name="ID", nullable=false)
    public Integer getId()
    {
        return id;
    }

    @ManyToOne
    //@PrimaryKeyJoinColumn
    @JsonBackReference
    @JoinColumn(name="User_ID", insertable=false, updatable=false, nullable=false)
    public User getUser() {
        return user;
    }


    @Column(name="MatchesWon", nullable=false)
    public Integer getMatchesWon()
    {
        return matchesWon;
    }


    @Column(name="MatchesLost", nullable=false)
    public Integer getMatchesLost()
    {
        return matchesLost;
    }



    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setUser(User _user)
    {
        this.user = _user;
    }



    public void setMatchesWon(Integer _matchesWon)
    {
        this.matchesWon = _matchesWon;
    }



    public void setMatchesLost(Integer _matchesLost)
    {
        this.matchesLost = _matchesLost;
    }

    public Highscore()
    {

        //id = null;
        //user_ID = null;
        matchesWon = 0;
        matchesLost = 0;
        //user = new User();
    }


    public Highscore(User _user )
    {
        //id = null;
        //user_ID = null;
        matchesWon = 0;
        matchesLost = 0;
        user = _user;
    }



    private Integer id;
    private User user;
    private Integer matchesWon;
    private Integer matchesLost;



    /*
    public Integer getUser_ID()
    {
        return user_ID;
    }

    public void setUser_ID(Integer _user_ID) {
        this.user_ID = _user_ID;
    }
*/

    /*
    @Id
    @Column(name="User_ID", unique=true, nullable=false)
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters = @Parameter(name="property", value="user"))
    Integer user_ID;
    */



}

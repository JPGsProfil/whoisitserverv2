package de.fhe.mc2.sdj.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * Highscore of a specific user
 * shows statistic about player
 * later highscore per user and set
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

    /*
    @Column(name = "User_ID")
    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer _id)
    {
        this.id = _id;
    }
    */


    @ManyToOne
    //@PrimaryKeyJoinColumn
    @JsonBackReference
    @JoinColumn(name="User_ID", referencedColumnName = "ID")
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
    //private Integer userId;



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

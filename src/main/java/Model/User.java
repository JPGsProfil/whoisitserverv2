package Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Jean on 11.12.2015.
 */



@Entity
//@SequenceGenerator(name="ID", initialValue = 1, allocationSize = 1)
@Table(name = "User")
public class User
{

    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Email")
    private String email;

    @Column(name = "Name")
    private String name;

    @Column(name = "Password")
    private String password;

    public List<Highscore> getHighscore()
    {
        return highscore;
    }

    @OneToMany(mappedBy="user",fetch = FetchType.EAGER, cascade={CascadeType.ALL})
    @JsonManagedReference
    private List<Highscore> highscore;


    //@Column(name = "id", table = "Highscore")



    public void setHighscore(Highscore _score)
    {
        highscore.add(_score);
    }


    public User()
    {

    }

    public User(String _email, String _name, String _password)
    {
        //this.id     = _id;
        email       = _email;
        name        = _name;
        password    = _password;
        //highscore   = _score;
    }



    public Integer getID()
    {
        return id;
    }


    public String getEmail() {
        return email;
    }


    public String getName() {
        return name;
    }



    public String getPassword()
    {
        return password;
    }



    public void setID(Integer _id)
    {
        this.id = _id;
    }


    public void setEmail(String _email)
    {
        email = _email;
    }


    public void setName(String _name)
    {
        name = _name;
    }


    public void setPassword(String _password)
    {
        password = _password;
    }

    /*
    @Override
    public String toString() {
        Integer printId = id;
        if (printId == null)
        {
            printId = 0;
        }

        /*Integer highscoreUser_id = highscore.getUser_ID();
        if (highscoreUser_id == null)
        {
            printId = 0;
        }

        return "User{" +
                "ID=" + printId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", highscore id=" + highscore.getID() +
                ", highscore won=" + highscore.getMatchesWon() +
                ", highscore userid=" + highscoreUser_id +
                '}';
    }*/
}

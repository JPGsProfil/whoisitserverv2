package Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @GeneratedValue
    @Column(name = "id")
    private int ID;

    @Column(name = "Email")
    private String email;

    @Column(name = "Name")
    private String name;

    @Column(name = "Password")
    private String password;

    @OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    @JsonManagedReference
    private Highscore score;


    //@Column(name = "id", table = "Highscore")

    public Highscore getScore()
    {
        return score;
    }

    public void setScore(Highscore _score)
    {
        score = _score;
    }


    public User()
    {

    }

    public User(int _ID, String _email, String _name, String _password, Highscore _score)
    {
        this.ID     = _ID;
        email       = _email;
        name        = _name;
        password    = _password;
        score       = _score;
    }



    public int getID()
    {
        return ID;
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



    public void setID(int _ID)
    {
        this.ID = ID;
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



}

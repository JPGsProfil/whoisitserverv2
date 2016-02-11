package de.fhe.mc2.sdj.model;

import javax.persistence.*;
import java.util.List;

/**
 * contains the user and the highscore
 */

@Entity(name = "UserWithHighscore")
@Table(name = "User")
// needed for delete ...
public class UserWithHighscore
{

    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Integer getID()
    {
        return id;
    }


    @Column(name = "Email")
    public String getEmail()
    {
        return email;
    }


    @Column(name = "Name")
    public String getName()
    {
        return name;
    }



    @Column(name = "Password")
    public String getPassword()
    {
        return password;
    }


    @OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@PrimaryKeyJoinColumn
    public List<Highscore> getHighscore()
    {
        return highscore;
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

    public void setHighscore(List<Highscore> highscore)
    {
        this.highscore = highscore;
    }


    /*
    @OneToMany(mappedBy="user",fetch = FetchType.EAGER, cascade={CascadeType.ALL})
    @JsonManagedReference
    private List<Highscore> highscore;


    public List<Highscore> getHighscore()
    {
        return highscore;
    }



    public void setHighscore(List<Highscore> _score)
    {
        highscore = _score;
    }*/


    public UserWithHighscore()
    {
        id = null;
        name = null;
        password = null;
        email = null;
    }

    public UserWithHighscore(Integer _userId)
    {
        id = _userId;
        name = null;
        password = null;
        email = null;
    }

    public UserWithHighscore(String _email, String _name, String _password)
    {
        //this.id     = _id;
        email       = _email;
        name        = _name;
        password    = _password;
        //highscore   = _score;
    }


    private Integer id;
    private String email;
    private String name;
    private String password;
    private List<Highscore> highscore;

}

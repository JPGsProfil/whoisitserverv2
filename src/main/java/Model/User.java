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
}

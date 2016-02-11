package de.fhe.mc2.sdj.modelz;


import javax.persistence.*;

/**
 * user profile for isit-game containing name, password, email ...
 */



@Entity
@Table(name = "User")
public class User
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
        id = null;
        name = null;
        password = null;
        email = null;
    }

    public User(Integer _userId)
    {
        id = _userId;
        name = null;
        password = null;
        email = null;
    }

    public User(String _email, String _name, String _password)
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

}

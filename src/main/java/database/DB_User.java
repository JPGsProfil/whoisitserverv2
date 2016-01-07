package database;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by Jean on 07.01.2016.
 */

@Entity
@SequenceGenerator(name="user_id", initialValue=1, allocationSize=1)
@Table(name="users")
public class DB_User implements Serializable
{
    int     intUserID;
    String  strFirstName;
    String  strLastName;

    // Default-Konstruktor:
    public DB_User()
    {

    }

    /* Getter */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id", nullable=false)
    public int getIntUserID()
    {
        return intUserID;
    }

    @Column(name="first_name", nullable=false)
    public String getStrFirstName()
    {
        return strFirstName;
    }

    @Column(name="last_name", nullable=false)
    public String getStrLastName()
    {
        return strLastName;
    }

    /* Setter */
    public void setIntUserID(int intUserID)
    {
        this.intUserID = intUserID;
    }

    public void setStrFirstName(String strFirstName)
    {
        this.strFirstName = strFirstName;
    }

    public void setStrLastName(String strLastName)
    {
        this.strLastName = strLastName;
    }



}

package Model;

import javax.persistence.*;

/**
 * Created by Jean on 11.12.2015.
 */
@Entity
//@SequenceGenerator(name="ID", initialValue = 1, allocationSize = 1)
@Table(name = "Cardset")
public class CardSet
{

    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    @Column(name = "Name")
    public String getName() {
        return name;
    }

    @Column(name = "User_ID")
    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    int id;
    String name;
    int userId;


}

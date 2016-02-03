package Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Jean on 11.12.2015.
 */
@Entity
@Table(name = "Value")
public class Value
{



    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Integer getId()
    {
        return id;
    }

    @Column(name = "Name")
    public String getName()
    {
        return name;
    }

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonBackReference
    public Attribute getAttribute()
    {
        return attribute;
    }




    public Value()
    {
    }


    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAttribute(Attribute attribute)
    {
        this.attribute = attribute;
    }

    public Value(Attribute attribute, Integer id, String name)
    {
        this.attribute = attribute;
        this.id = id;
        this.name = name;
    }




    Integer id;
    String name;
    Attribute attribute;
}

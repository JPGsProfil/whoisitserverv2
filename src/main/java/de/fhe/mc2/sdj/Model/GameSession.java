package de.fhe.mc2.sdj.model;

import javax.persistence.*;

/**
 * Created by Jean on 11.12.2015.
 */
@Entity
@Table(name = "Session")
public class GameSession
{

    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Integer getId()
    {
        return id;
    }

    public Integer getCardset_ID()
    {
        return cardsetId;
    }

    @Column(name = "HasEnded")
    public Boolean isHasEnded()
    {
        return hasEnded;
    }

    @Column(name = "HasStarted")
    public Boolean isHasStarted()
    {
        return hasStarted;
    }


    @Column(name = "Player1_ID")
    public Integer getPlayer1_ID()
    {
        return player1_ID;
    }

    @Column(name = "Player2_ID")
    public Integer getPlayer2_ID()
    {
        return player2_ID;
    }



    public void setCardset_ID(Integer cardsetId)
    {
        this.cardsetId = cardsetId;
    }

    public void setHasEnded(Boolean hasEnded)
    {
        this.hasEnded = hasEnded;
    }

    public void setHasStarted(Boolean hasStarted)
    {
        this.hasStarted = hasStarted;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setPlayer1_ID(Integer player1_ID)
    {
        this.player1_ID = player1_ID;
    }

    public void setPlayer2_ID(Integer player2_ID)
    {
        this.player2_ID = player2_ID;
    }

    Integer id;
    Integer player1_ID;
    Integer player2_ID;
    Integer cardsetId;
    Boolean hasStarted;
    Boolean hasEnded;
}

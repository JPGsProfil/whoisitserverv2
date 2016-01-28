package Marshalling;

import Model.CardSet;
import Model.Highscore;
import Model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Jean on 11.01.2016.
 */
@Path("/db")
public class dbAPI
{

    //@_POST
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/{id}" )
    public User User(@PathParam("id") Integer _id)
    {
        return database.DB_User.getUser(_id);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/add" )
    public Response UserAdd( User _user)
    {
        System.out.println("bin in AddUser: ");
        System.out.println("Name: " + _user.getName());
        database.DB_User.addUser(_user);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/addGet" )
    public Response UserAddPerGet()
    {
        Highscore score = new Highscore();
        User testUser = new User("test@test.de", "Er", "321");
        return database.DB_User.addUser(testUser);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/addtest" )
    public Response UserAddTest()
    {

        return database.DB_User.testwrite2();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "cardset/{id}" )
    public CardSet getCardSet(@PathParam("id") Integer _id)
    {
        return database.DB_CardSet.getCardSet(_id);
    }

















}

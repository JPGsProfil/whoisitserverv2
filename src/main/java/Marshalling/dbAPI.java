package Marshalling;

import Model.Card;
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
    ///////////////////////////
    /////// User

    //@_POST
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/{id}" )
    public User getUserById(@PathParam("id") Integer _id)
    {
        return database.DB_User.getUser(_id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/namepassword" )
    public LoginResult doesUserWithNameAndPasswordExist( Login _user)
    {
        System.out.println("Bin in doesUserWithNameAndPasswordExist");
        System.out.println("user name "+_user.getName());
        return database.DB_User.doesUserWithNameAndPasswordExist(_user);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/loginexample" )
    public Login getLoginExample()
    {
        Login testlogin = new Login();
        testlogin.setName("ja");
        testlogin.setPassword("ja");
        return testlogin;

    }


    /*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/namepw" )
    public User GetUserByNameAndPwd( String _username, String _password)
    {
        return database.DB_User.getUserByIdAndPwd(_username, _password);
    }
    */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/new" )
    public Response addUser( User _user)
    {
        System.out.println("bin in AddUser: ");
        System.out.println("Name: " + _user.getName());
        database.DB_User.addUser(_user);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/addgettest" )
    public Response addUserPerGetTest()
    {
        Highscore score = new Highscore();
        User testUser = new User("test@test.de", "Er", "321");
        return database.DB_User.addUser(testUser);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/addposttest" )
    public Response addUserByPostTest()
    {
        return database.DB_User.testwrite2();
    }



    ///////////////////////////
    /////// Cardset

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "cardset/{id}" )
    public CardSet getCardSet(@PathParam("id") Integer _id)
    {
        return database.DB_CardSet.getCardSet(_id);
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "cardset/new" )
    public Response addCardSet( CardSet _cardSet)
    {
        return database.DB_CardSet.addCardSet(_cardSet);
    }


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "cardset/new/test" )
    public Response addCardSetTest()
    {
        return database.DB_CardSet.addCardSetTest();
    }



    ///////////////////////////
    /////// Card

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "card/single/{id}" )
    public Card getCard(@PathParam("id") Integer _id)
    {
        return database.DB_Card.getCard(_id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "card/multiple/{cardSetId}" )
    public List<Card> getCards(@PathParam("cardSetId") Integer _cardSetId)
    {
        return database.DB_Card.getCards(_cardSetId);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "card/new/single" )
    public Response addCard( Card _card)
    {
        return database.DB_Card.addCard(_card);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "card/new/multiple" )
    public Response addCards( List<Card> _cardList)
    {
        return database.DB_Card.addCards(_cardList);
    }














}

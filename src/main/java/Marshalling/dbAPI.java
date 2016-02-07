package Marshalling;

import Model.*;
import database.DB_Attribute;
import database.DB_GameSession;
import org.hibernate.Session;

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

    /**
     * old get user by id, now replaced by version 2 with return Response
     * @param _id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user2/{id}" )
    public User getUserById(@PathParam("id") Integer _id)
    {
        return database.DB_User.getUser(_id);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/{id}" )
    public Response getUserByIdV2(@PathParam("id") Integer _id)
    {
        return database.DB_User.getUserV2(_id);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/{id}" )
    public Response deleteUserById(@PathParam("id") Integer _id)
    {
        return database.DB_User.deleteUser(_id);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/namepassword" )
    public Response doesUserWithNameAndPasswordExist( Login _user)
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
    @Path( "user" )
    public Response addUser( User _user)
    {
        System.out.println("bin in AddUser: ");
        System.out.println("Name: " + _user.getName());
        return database.DB_User.addUser(_user);
        //return Response.created().build();
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
    public Response getCardSet(@PathParam("id") Integer _id)
    {
        return database.DB_CardSet.getCardSet(_id);
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "cardset/new" )
    public Response addCardSet(CardSet _cardSet)
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
    public Response getCard(@PathParam("id") Integer _id)
    {
        return database.DB_Card.getCard(_id);
    }

    /**
     * List<Card>
     * @param _cardSetId
     * @return
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "card/multiple/{cardSetId}" )
    public Response getCards(@PathParam("cardSetId") Integer _cardSetId)
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "card/new/multiplev2" )
    public Response addCardsV2( List<Card> _cardList)
    {
        return database.DB_Card.addCardsV2(_cardList);
    }


    /////////////////////////////////////////7
    ////////// Attribute


    /**
     *
     * @param _id
     * @return content is Attribute
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "attribute/{id}" )
    public Response getAttributeById(@PathParam("id") Integer _id)
    {
        return DB_Attribute.getAttributeById(_id);
    }


    /**
     * content: List<Attribute>
     * @param _cardsetId
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "attribute/bycardset/{id}" )
    public Response getAttributesAndValuesByCardSetId(@PathParam("id") Integer _cardsetId)
    {
        return DB_Attribute.getAttributesAndValuesByCardSetId(_cardsetId);
    }


    /**
     * content: List<ReturnString>
     * @param _cardsetId
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "attribute/bycardset/onlyname/{id}" )
    public Response getAttributesByCardSetId(@PathParam("id") Integer _cardsetId)
    {
        return DB_Attribute.getAttributesByCardSetId(_cardsetId);
    }




    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "attribute/single" )
    public Response AddAttribute(Attribute _attribute)
    {
        return DB_Attribute.addAttribute(_attribute);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "attribute/multiple" )
    public Response AddAttributes(List<Attribute> _attributes)
    {
        return DB_Attribute.addAttributes(_attributes);
    }

    // Session

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "session/{id}" )
    public GameSession getSession(@PathParam("id") Integer _id)
    {
        return DB_GameSession.getSessionById(_id);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "session/byuser/{id}" )
    public ReturnBool isInSession(@PathParam("id") Integer _id)
    {
        return DB_GameSession.isUserInSession(_id);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "session/joinable" )
    public List<GameSession> getSessionsWithOneUser()
    {
        return DB_GameSession.getSessionsWithOneUser();
    }












}

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
     * getestet
     * @param _id user id
     * @return 200 or 400
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/{id}" )
    public Response getUserByIdV2(@PathParam("id") Integer _id)
    {
        return database.DB_User.getUserV2(_id);
    }

    /**
     * tested
     * return http 409 (conflict) if user already exist
     * @param _user
     * @return
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

    /**
     * not working with user. if needed user on andrdoid should be changed like UserWithHighscore
     * OR adding userId to highscore (already in highscore comments)
     * High
     * @param _user
     * @return
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user" )
    public Response deleteUser(UserWithHighscore _user)
    {
        return database.DB_User.deleteUser(_user);
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


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "cardset/user/{id}" )
    public Response getCardSetByUserId(@PathParam("id") Integer _userId)
    {
        return database.DB_CardSet.getCardSetByUserId(_userId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "cardset" )
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
     * List of Cards
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
    public Response addCardsV2( List<Card> _cards)
    {
        return database.DB_Card.addCardsV2(_cards);
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
     * content: List of Attributes
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
     * content: List of ReturnString
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
    public Response addAttribute(Attribute _attribute)
    {
        return DB_Attribute.addAttribute(_attribute);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "attribute/multiple" )
    public Response addAttributes(List<Attribute> _attributes)
    {
        return DB_Attribute.addAttributes(_attributes);
    }

    // Session

    /**
     * content: Gamesession
     * @param _id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "session/{id}" )
    public Response getSession(@PathParam("id") Integer _id)
    {
        return DB_GameSession.getSessionById(_id);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "session" )
    public Response addSession(GameSession _session)
    {
        return DB_GameSession.addGameSession(_session);
    }



    /**
     * content: ReturnBool
     * @param _id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "session/byuser/{id}" )
    public Response isInSession(@PathParam("id") Integer _id)
    {
        return DB_GameSession.isUserInSession(_id);
    }


    /**
     * content List of GameSession
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "session/joinable" )
    public Response getSessionsWithOneUser()
    {
        return DB_GameSession.getSessionsWithOneUser();
    }








}

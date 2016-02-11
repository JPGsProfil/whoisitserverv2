package de.fhe.mc2.sdj.marshalling2;

import de.fhe.mc2.sdj.database.*;
import de.fhe.mc2.sdj.model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Jean on 11.01.2016.
 */
@Path("/db")
public class dbAPI
{
    ///////////////////////////
    /////// User


    /**
     * get user by id
     * last tested 10.02.16
     * @param _id user id
     * @return Response with 200 or 400
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/{id}" )
    public Response getUserByIdV2(@PathParam("id") Integer _id)
    {
        return DB_User.getUserV2(_id);
    }

    /**
     * add a user to the database
     * prove if name already exist
     * also add a score for the new user
     * last tested 08.02.16
     * @statuscode 200 user added
     * @statuscode 204 user not added, database not modified
     * @statuscode 409 user already exist
     * @param _user user which should be added to database
     * @return Response with 200 if successful, else 204 or 409
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user" )
    public Response addUser( User _user)
    {
        System.out.println("bin in AddUser: ");
        System.out.println("Name: " + _user.getName());
        return DB_User.addUser(_user);
        //return Response.created().build();
    }

    /**
     * not working with user. if needed user on andrdoid should be changed like UserWithHighscore
     * OR adding userId to highscore (already in highscore comments)
     * @statuscode 200 user deleted
     * @statuscode 304 user not deleted, database not modified
     * @param _user user which has to be deleted
     * @return 200 or 304
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user" )
    public Response deleteUser(UserWithHighscore _user)
    {
        return DB_User.deleteUser(_user);
    }


    /**
     * check if user password combination exist
     * @statuscode 200 user exist and password is correct
     * @statuscode 204 no user with given password
     * @param _user contains String username  and String password
     * @return response with 200 and user or 204
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/namepassword" )
    public Response doesUserWithNameAndPasswordExist( Login _user)
    {
        System.out.println("Bin in doesUserWithNameAndPasswordExist");
        System.out.println("user name "+_user.getName());
        return DB_User.doesUserWithNameAndPasswordExist(_user);
    }


    /**
     * return bool if user is in a session
     * last tested: 10.02.16
     * @param _id id of the user
     * @return Response with ReturnBool, true if user is in session, else false
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/insession/{userId}" )
    public Response isInSession(@PathParam("userId") Integer _id)
    {
        return DB_GameSession.isUserInSession(_id);
    }

    /*
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
        return DB_User.addUser(testUser);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/addposttest" )
    public Response addUserByPostTest()
    {
        return DB_User.testwrite2();
    }
    */


    ///////////////////////////
    /////// Cardset


    /**
     * get the cardset which belongs to given cardset id
     * includes all cards with multiple attributes and values
     * last tested 08.02.16
     * @param _id id of the cardset
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "cardset/{id}" )
    public Response getCardSet(@PathParam("id") Integer _id)
    {
        return DB_CardSet.getCardSet(_id);
    }


    /**
     * get all cardsets belonging to a specific userid
     * includes all cards with multiple attributes and values
     * last tested 08.02.16
     * @param _userId id of the user (foreign key)
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "cardsets/{id}" )
    public Response getCardSetsByUserId(@PathParam("id") Integer _userId)
    {
        return DB_CardSet.getCardSetsByUserId(_userId);
    }


    /**
     * add a cardset to the database
     * json may contain cards
     * cards may contain attributes
     * attrubutes may contain a value
     * tested
     * @statuscode 200 cardset added successfully
     * @statuscode 304 cardset adding failed, database not modified
     * @statuscode 409 cardset not valid, maybe 2 cards with same name
     * @param _cardSet object with cardset-information, read from json
     * @return Statuscode 200 if success, else 304
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "cardset" )
    public Response addCardSet(CardSet _cardSet)
    {
        return DB_CardSet.addCardSet(_cardSet);
    }



    ///////////////////////////
    /////// Card

    /**
     * get a card by id
     * includes attributes and values of specific card
     * last tested 09.02.16
     * @statuscode 200 card available
     * @statuscode 204 no card with this id
     * @param _id card id (primary key)
     * @return Response 200 containing card object if exist, else 204 no content
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "card/{id}" )
    public Response getCard(@PathParam("id") Integer _id)
    {
        return DB_Card.getCard(_id);
    }



    /**
     * get all cards which belong to specific cardSetId
     * last tested: 09.02.16
     * @statuscode 200 cards for given cardSetId available
     * @statuscode 204 no cards for given cardSetId
     * @param _cardSetId id of cardset
     * @return Response 200 with List of cards if available, else 204 with no content
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "cards/{cardSetId}" )
    public Response getCards(@PathParam("cardSetId") Integer _cardSetId)
    {
        return DB_Card.getCards(_cardSetId);
    }


    /**
     * add a single card to the database
     * may contain attributes and they may contain a value, but not needed
     * JSON has to contain cardSetId (eg. ...,"cardSetId"=1, ...), because every card needs a cardSetId
     * @statuscode 200 card added successfully
     * @statuscode 304 card adding failed, database not modified
     * last tested 09.02.16
     * @param _card card which should be added to database as JSON
     * @return Response with 200 if successful, else 304 not modified
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "card" )
    public Response addCard( Card _card)
    {
        return DB_Card.addCard(_card);
    }

    /*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "card/multiple" )
    public Response addCards( List<Card> _cardList)
    {
        return DB_Card.addCards(_cardList);
    }*/

    /*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "card/multiplev2" )
    public Response addCardsV2( List<Card> _cards)
    {
        return DB_Card.addCardsV2(_cards);
    }
    */


    /////////////////////////////////////////7
    ////////// Attribute


    /**
     * return attribute with belonging value
     * @statuscode 200 attribute for given id available
     * @statuscode 204 no attribute for given id
     * last tested 09.02.16
     * @param _id id (primary key) of the attribute
     * @return Response with Attribute as JSON
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "attribute/{id}" )
    public Response getAttributeById(@PathParam("id") Integer _id)
    {
        return DB_Attribute.getAttributeById(_id);
    }


    /**
     * get all Attributes for given cardSetId
     * @statuscode 200 attribute(s) for given cardSetId available
     * @statuscode 204 no attribute for given cardSetId
     * last tested 09.02.16
     * @param _cardsetId id (foreign key) of the cardset
     * @return Response with List of Attributes
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "attributes/{id}" )
    public Response getAttributesAndValuesByCardSetId(@PathParam("id") Integer _cardsetId)
    {
        return DB_Attribute.getAttributesAndValuesByCardSetId(_cardsetId);
    }


    /**
     * get all attributes (only name) and without duplicates
     * can be used to generate question
     * @statuscode 200 attribute(s) for given cardSetId available
     * @statuscode 204 no attribute for given cardSetId
     * last tested 09.02.
     * @param _cardsetId id (foreign key) of the cardset
     * @return Response with List of ReturnString (name)
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "attributes/onlyname/{id}" )
    public Response getAttributesByCardSetId(@PathParam("id") Integer _cardsetId)
    {
        return DB_Attribute.getAttributesByCardSetId(_cardsetId);
    }


    /**
     * add a single attribute to the database, may contain a value
     * JSON has to contain cardId (eg. ...,"cardId"=1, ...), because every attribute needs a cardId
     * last tested 09.02.16
     * @statuscode 200 attribute added successfully
     * @statuscode 304 attribute adding failed, database not modified
     * @param _attribute JSON which contains the attribute
     * @return Response with 200 if successful, else 304
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "attribute" )
    public Response addAttribute(Attribute _attribute)
    {
        return DB_Attribute.addAttribute(_attribute);
    }

    /*
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "attributes" )
    public Response addAttributes(List<Attribute> _attributes)
    {
        return DB_Attribute.addAttributes(_attributes);
    }*/

    // Session

    /**
     * get gamesession for given id
     * last tested 09.02.16
     * @statuscode 200 gamesession for given id available
     * @statuscode 204 no gamesession for given id
     * @param _id id (primary key) of the gamesession
     * @return Response with Gamesession
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "session/{id}" )
    public Response getSession(@PathParam("id") Integer _id)
    {
        return DB_GameSession.getSessionById(_id);
    }


    /**
     * add a gamesession to the database
     * last tested 10.02.16
     * @statuscode 200 gamesession added successfully
     * @statuscode 304 gamesession adding failed, database not modified
     * @param _session given gameSession as JSON
     * @return Response 200 if successful, else 304
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "session" )
    public Response addSession(GameSession _session)
    {
        return DB_GameSession.addGameSession(_session);
    }






    /**
     * get list of gamesessions which aren't full
     * last tested 10.02.16
     * @statuscode 200 joinable gameSession(s) available
     * @statuscode 204 no joinable gameSession(s)
     * @return List of GameSessions
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "sessions/joinable" )
    public Response getSessionsWithOneUser()
    {
        return DB_GameSession.getSessionsWithOneUser();
    }


    /**
     * Update a gamesession
     * can be used to add a second user or change state
     * @statuscode 200 gamesession successfully changed
     * @statuscode 304 gamesession changing failed, database not modified
     * @param _session session which should be changed
     * @return Response with 200 if successfully, else 304
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "session" )
    public Response updateSession(GameSession _session)
    {
        return DB_GameSession.updateGameSession(_session);
    }








}

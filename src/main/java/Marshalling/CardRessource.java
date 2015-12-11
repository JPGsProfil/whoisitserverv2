package Marshalling;

/**
 * Created by Jean on 11.12.2015.
 */


import Model.Card;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path( "/card" )
public class CardRessource
{
    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public Card getAsJson()
    {
        return new Card();
    }

    @Path( "{Card_ID}" )
    public CardRessource getCard( @PathParam( "Card_ID" ) int Card_ID)
    {
        return new CardRessource( Card_ID );
    }



    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    public Response createStation(Card s ) throws URISyntaxException
    {
        System.out.println( s );
        return Response.created( new URI( "http://server/Card/134" ) ).build();
    }

}

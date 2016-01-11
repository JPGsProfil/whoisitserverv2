package Marshalling;

import Model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
        return database.DB_User.GetUser(_id);
    }



}

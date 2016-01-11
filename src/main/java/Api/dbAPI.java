package Api;

import Model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Jean on 11.01.2016.
 */

public class dbAPI
{
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "user/getuser" )
    public User User(@QueryParam("id") Integer _id)
    {
        return database.DB_User.GetUser(_id);
    }

}

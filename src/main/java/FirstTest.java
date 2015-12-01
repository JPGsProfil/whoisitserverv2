import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Jean on 01.12.2015.
 */

@Path( "/firstTest" )
public class FirstTest
{
    @GET
    @Produces( MediaType.TEXT_PLAIN )
    public String getGreeting()
    {
        return "Server Example";
    }
}

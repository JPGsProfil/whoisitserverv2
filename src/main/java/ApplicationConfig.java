import database.Test_FirstTest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jean on 01.12.2015.
 */

@ApplicationPath("Api")
public class ApplicationConfig extends Application
{
    @Override
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add( Test_FirstTest.class );
        classes.add(database.Test_Hibernate.class);
        return super.getClasses();
    }
}

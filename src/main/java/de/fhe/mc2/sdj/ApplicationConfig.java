package de.fhe.mc2.sdj;

import de.fhe.mc2.sdj.marshalling.dbAPI;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jean on 01.12.2015.
 */

/**
 * Defines the components of a JAX-RS application and supplies additional metadata.
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application
{
    /**
     * Jax-RS component
     * @return api-class
     */
    @Override
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add( dbAPI.class );
        return super.getClasses();
    }
}

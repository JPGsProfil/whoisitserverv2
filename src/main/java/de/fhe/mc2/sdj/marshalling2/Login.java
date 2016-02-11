package de.fhe.mc2.sdj.marshalling2;

/**
 * Created by Jean on 02.02.2016.
 */

/**
 * object to check name and password for login without giving the whole userobject
 */
public class Login
{
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    String name;
    String password;
}

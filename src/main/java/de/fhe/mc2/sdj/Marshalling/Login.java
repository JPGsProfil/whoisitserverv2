package de.fhe.mc2.sdj.marshalling;

/**
 * Created by Jean on 02.02.2016.
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

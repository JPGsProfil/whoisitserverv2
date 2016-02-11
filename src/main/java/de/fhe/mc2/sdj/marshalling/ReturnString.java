package de.fhe.mc2.sdj.marshalling;

/**
 * Return object containing a string instead of a simple string to search through JSON on Android-client
 */
public class ReturnString
{
    public String string;
    public ReturnString()
    {
        string = null;
    }
    public ReturnString(String _string)
    {
        string = _string;
    }
}

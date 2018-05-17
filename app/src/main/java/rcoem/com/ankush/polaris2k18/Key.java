package com.example.ankush.polaris2k18;

/**
 * Created by Ankush on 12/22/2017.
 */

public class Key {
    public static String key;
    public static boolean status=false;
    Key(String key,boolean status)
    {
        this.key=key;this.status=status;
    }
    public static boolean getStatus()
    {
        return status;
    }
    public static String getKey()
    {
        return key;
    }
}

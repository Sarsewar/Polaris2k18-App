package com.example.ankush.polaris2k18;

/**
 * Created by Ankush on 12/21/2017.
 */

public class IOS {
    public String email;
    public String phone;
    IOS()
    {}
    IOS(String n,String p)
    {
        this.email=n;
        this.phone=p;
    }
    public String getEmail()
    {
        return this.email;
    }
    public String getPhone()
    {
        return this.phone;
    }
}

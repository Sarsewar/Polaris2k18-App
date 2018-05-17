package com.example.ankush.polaris2k18;

import java.util.HashMap;

/**
 * Created by Ankush on 12/16/2017.
 */
public class User {

    public String name;
    public String email;
    public String key;
    public String phone;
    public String receipt;
    public String paid;
    public String date;
    public String balance;
    public String college;
    public String rname;
   public  HashMap<String, Participated> participatedMap;
    public HashMap<String, Group> groupMap;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)

    User() {

    }

    public User(String name,String email,String key,String ph,String rname,String balance,String paid,String college,String receipt,String date
    ) {
        this.name = name;
        this.email = email;
        this.key=key;
        this.receipt=receipt;
        this.phone=ph;
        this.college=college;
        this.date=date;
        this.rname=rname;
        this.balance=balance;
        this.paid=paid;


    }
    public String getCollege()
    {
        return this.college;
    }

    public String getPaid()
    {
        return paid;
    }
public String getReceipt()
{
    return receipt;
}
    public String getBalance()
    {
        return balance;
    }
    public String getName()
    {
        return name;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getRname(){
        return rname;
    }
    public String getKey()
    {return this.key;}

    public String getDate()
    {
        return this.date;
    }
}
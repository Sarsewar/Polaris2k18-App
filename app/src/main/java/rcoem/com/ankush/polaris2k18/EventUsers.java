package com.example.ankush.polaris2k18;

/**
 * Created by Ankush on 12/18/2017.
 */

public class EventUsers {
    public String receipt;
    public String email;
    public String phone;
    public String userId;
    public String date;

    EventUsers(String receipt,String email,String phone,String userId,String date)
    {
        this.receipt=receipt;
        this.email=email;
        this.userId=userId;
        this.date=date;
        this.phone=phone;
    }
    public String getUserId()
    {
        return userId;
    }
    public String getReceipt()
    {
        return receipt;
    }
    public String getEmail()
    {
        return email;
    }
    public String getDate()
    {
        return date;
    }
    public String getPhone()
    {
        return phone;
    }
}

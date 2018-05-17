package com.example.ankush.polaris2k18;

/**
 * Created by Ankush on 12/16/2017.
 */

public class Participated {
    public String eventname;
    public String date;
    public Participated()
    {

    }
    public Participated(String nm,String date)
    {
        this.eventname=nm;
        this.date=date;
    }
    public String getEventname()
    {
        return this.eventname;
    }
    public String getDate()
    {
        return this.date;
    }
}

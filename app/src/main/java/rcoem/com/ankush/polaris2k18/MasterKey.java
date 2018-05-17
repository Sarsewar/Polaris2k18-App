package com.example.ankush.polaris2k18;

/**
 * Created by Ankush on 1/12/2018.
 */

class MasterKey {

    public String reader;
    public String pubteam;
    public String admin;
     MasterKey()
    {}
     MasterKey(String Reader,String PT,String admin)
    {
        this.reader=Reader;
        this.pubteam=PT;
        this.admin=admin;

    }
    public String getReader()
    {
        return reader;
    }
    public String getPT()
    {
        return pubteam;
    }
    public String getAdmin()
    {
        return admin;
    }
}

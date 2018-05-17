package com.example.ankush.polaris2k18;

/**
 * Created by Ankush on 12/16/2017.
 */

public class Group {
  public String name;
    public String phone;
    public String status;
    public Group()
    {

    }
    Group(String name,String phone,String status)
    {
        this.name=name;
        this.phone=phone;
        this.status=status;

    }
    public void setStatus(String status)
    {
      this.status=status;
    }
public  String getStatus()
{
  return status;
}
    public String getName()
    {
        return name;
    }
    public String getPhone()
    {return phone;}
}

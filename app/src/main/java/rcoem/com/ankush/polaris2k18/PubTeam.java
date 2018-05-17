package com.example.ankush.polaris2k18;

/**
 * Created by Ankush on 12/16/2017.
 */
public class PubTeam {
    public String name, phone,key,total_amount,today_amount;

   public PubTeam() {

    }

   public PubTeam(String name, String ph,String key) {
        this.name = name;
        this.phone = ph;
       this.total_amount="0";
       this.key=key;
       this.today_amount="0";
    }
public String getKey()
{
    return this.key;
}
public void setTotal_amount(String total_amount){
    this.total_amount=total_amount;

}
    public String getName() {
        return this.name;
    }
    public String getTotal_amount() {
        return this.total_amount;
    }
    public String getToday_amount() {
        return this.today_amount;
    }
    public String getPhone()
    {
        return this.phone;
    }

}
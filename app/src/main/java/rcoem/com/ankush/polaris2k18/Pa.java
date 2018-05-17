package com.example.ankush.polaris2k18;

import java.util.ArrayList;

/**
 * Created by Ankush on 12/21/2017.
 */

public class Pa {

    public static String  s="";
    public static ArrayList<String> l=new ArrayList<>();
    Pa(String s){
        this.s=s;
        l.add(s);
    }
    public static ArrayList<String> getL()
    {
        return l;
    }
    public static String getS()
    {
        return s;
    }
}

package com.example.ankush.polaris2k18;

import java.util.ArrayList;

/**
 * Created by Ankush on 1/14/2018.
 */

public class StorePar {
    public static ArrayList<String> par;
    static
    {
        par=new ArrayList<>();
    }
     StorePar(String j)
    {
        par.add(j);
    }

    public StorePar() {

    }

    public static ArrayList<String> getA()
    {
        return par;
    }
}

package com.example.ankush.polaris2k18;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ankush on 12/27/2017.
 */

public class Polaris2k18 extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}

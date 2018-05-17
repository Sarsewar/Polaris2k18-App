package com.example.ankush.polaris2k18;

import android.app.Application;
import android.content.Context;


/**
 * Created by Ankush on 12/19/2017.
 */

public class MyApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //MyNotificationOpenedHandler : This will be called when a notification is tapped on.
        //MyNotificationReceivedHandler : This will be called when a notification is received while your app is running.
       /* OneSignal.startInit(context)
                .setNotificationOpenedHandler(new MyNotificationOpenedHandler(context))
                .setNotificationReceivedHandler( new MyNotificationReceivedHandler() )
                .init();
*/
    }


}
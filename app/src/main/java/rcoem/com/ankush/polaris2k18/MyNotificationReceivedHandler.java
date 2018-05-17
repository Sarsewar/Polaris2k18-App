package com.example.ankush.polaris2k18;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.json.JSONObject;

/**
 * Created by Ankush on 12/19/2017.
 */
//This will be called when a notification is received while your app is running.
public class MyNotificationReceivedHandler  implements OneSignal.NotificationReceivedHandler {

    Context mContext;
    MyNotificationReceivedHandler(Context mContext)
    {
        this.mContext=mContext;
    }
    @Override
    public void notificationReceived(OSNotification notification)
    {
        JSONObject data =notification.payload.additionalData;

        Log.i("ReceivedHandler:",data.toString());
        Toast.makeText(mContext,"Receiver Handler",Toast.LENGTH_LONG).show();
        Intent j=new Intent(mContext,Notice.class);
        j.putExtra("json",data.toString());
        mContext.startActivity(j);
        if (data != null)
        {
            //While sending a Push notification from OneSignal dashboard
            // you can send an addtional data named "customkey" and retrieve the value of it and do necessary operation

        }
    }
}
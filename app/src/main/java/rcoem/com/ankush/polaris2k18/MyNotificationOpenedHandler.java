package com.example.ankush.polaris2k18;

/**
 * Created by Ankush on 12/19/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class MyNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    public Context mContext;
    public MyNotificationOpenedHandler(Context mContext) {
        this.mContext=mContext;
    }

    // This fires when a notification is opened by tapping on it.
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        String dat=result.notification.payload.body;
        JSONObject data = result.notification.payload.additionalData;
        String activityToBeOpened;
        JSONObject j=result.notification.toJSONObject();
        //While sending a Push notification from OneSignal dashboard
        // you can send an addtional data named "activityToBeOpened" and retrieve the value of it and do necessary operation
        //If key is "activityToBeOpened" and value is "AnotherActivity", then when a user clicks
        //on the notification, AnotherActivity will be opened.
        //Else, if we have not set any additional data MainActivity is opened.
        Log.i("Notification:",data.toString());
        Intent intent = new Intent(mContext, Notice.class);
        intent.putExtra("json",data.toString());
        Toast.makeText(mContext,"Notification Arrive",Toast.LENGTH_LONG).show();
        mContext.startActivity(intent);
        if (j != null)
        {

            /*activityToBeOpened = data.optString("polaris", null);
            if (activityToBeOpened != null && activityToBeOpened.equals("Notice"))
            {
              */  Log.i("OneSignalExample", "customkey set with value: " );



       // }

        //If we send notification with action buttons we need to specidy the button id's and retrieve it to
        //do the necessary operation.

    }
    else
            Log.i("OneSignalExample", "data null: ");

    }
}
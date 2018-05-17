package com.example.ankush.polaris2k18;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends AppCompatActivity {
EditText t,m;
    Button b;
    private DatabaseReference mDatabase,mDatabase12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }

        t=(EditText)findViewById(R.id.admintitle);
        m=(EditText)findViewById(R.id.adminMsg);
        b=(Button)findViewById(R.id.adminsend);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase12 = FirebaseDatabase.getInstance().getReference("Notices");

                NoticeClass not=new NoticeClass(t.getText().toString(),m.getText().toString());
                mDatabase12.push().setValue(not);

                mDatabase = FirebaseDatabase.getInstance().getReference("Users");
                mDatabase.keepSynced(true);

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                        {
                            final ArrayList<Participated> par=new ArrayList<>();
                            par.clear();

                            if (postSnapshot != null)
                            {
                                User u = postSnapshot.getValue(User.class);

                                String n=null;
                                n=u.getEmail();
                                if(n!=null)
                                    sendNotification(n);

                            } else
                            {
                                Toast.makeText(getApplicationContext(), "email not found", Toast.LENGTH_LONG).show();
                            }
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("sfdg", "Failed to read value.", error.toException());
                    }
                });
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void sendNotification(final String mail)
    {
        final String send_email=mail;
        AsyncTask.execute(new Runnable()
        {
            @Override
            public void run() {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);


                    //This is a Simple Logic to Send Notification different Device Programmatically....
                   /* if (MainActivity.ontTag.equals("ankush@gmail.com")) {
                        send_email = "pk@gmail.com";
                    } else {
                        send_email = "ankush@gmail.com";
                    }
*/
                  // send_email="vaishu@gmail.com"+"\"dj@gmail.com";
                    String title=t.getText().toString();
                    String msg=m.getText().toString();
                    try {
                        String jsonResponse=null;

                        URL url = new URL("https://onesignal.com/api/v1/notifications");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setUseCaches(false);
                        con.setDoOutput(true);
                        con.setDoInput(true);

                        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        con.setRequestProperty("Authorization", "Basic ZDc2YmU4OGMtYzE3ZC00MmVmLTg0NTYtZWFkN2Y5YzhmZjcx");//Change Restful api
                        con.setRequestMethod("POST");

                        String strJsonBody = "{"
                                + "\"app_id\": \"90111f73-46e3-4c29-a624-158643fd821f\","

                                + "\"filters\": [{\"field\": \"tag\", \"key\": \"User_ID\", \"relation\": \"=\", \"value\": \"" + send_email + "\"}],"

                                + "\"data\": {\""+title+"\": \""+msg+"\"},"
                                + "\"contents\": {\"en\": \"Please check-out Notice Section.\"}"
                                + "}";


                        System.out.println("strJsonBody:\n" + strJsonBody);

                        byte[] sendBytes = strJsonBody.getBytes("UTF-8");
                        con.setFixedLengthStreamingMode(sendBytes.length);

                        OutputStream outputStream = con.getOutputStream();
                        outputStream.write(sendBytes);

                        int httpResponse = con.getResponseCode();
                        System.out.println("httpResponse: " + httpResponse);

                        if (httpResponse >= HttpURLConnection.HTTP_OK
                                && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                            Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        } else {
                            Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        }
                        System.out.println("jsonResponse:\n" + jsonResponse);
                        if(jsonResponse!=null)
                        {
                        Admin.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Admin.this,"sent",Toast.LENGTH_LONG).show();

                            }
                        });

                        }
                        else {

                            Admin.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Admin.this,"Network Problem\nPlease Try Again.",Toast.LENGTH_LONG).show();

                                }
                            });
                        }

                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        });
    }
}

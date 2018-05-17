package com.example.ankush.polaris2k18;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Show12 extends AppCompatActivity {
    Context c;
    private ListView lv;
    File file;
    private static final String TAG = "MEDIA";
    FileOutputStream outputStream;
    ProgressDialog pDialog;
    HashMap<String,ArrayList<Group> > hgp=new HashMap<>();
    HashMap<String,ArrayList<Participated> > hpar=new HashMap<>();
    HashMap<String,User > huser=new HashMap<>();
    SharedPreferences sh;
    SharedPreferences.Editor editor;
    public static final String PP="PP";
    public  static int tolpay=0;
    public static int tolbal=0;
    public static int pcount=0;
    String date;
    private DatabaseReference mDatabase;
    ArrayList<User> my_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show12);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }
        final String check=getIntent().getStringExtra("show");
        if(check.equalsIgnoreCase("specific"))
        date=getIntent().getStringExtra("date");
        lv=(ListView)findViewById(R.id.list);

        sh=getSharedPreferences(PP,Context.MODE_PRIVATE);
        editor=sh.edit();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);

        try {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Polaris");

        }catch (Exception e)
        {

        }
        final String finalMail = "";
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                final ArrayList<User> my_list=new ArrayList<User>();
                final ArrayList<Pa> parti=new ArrayList<Pa>();
                tolbal=tolpay=pcount=0;
                Gson gson = new Gson();
               // String reqJson=gson.toJson();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    if (postSnapshot != null)
                    {
                        User u = postSnapshot.getValue(User.class);

                       // Toast.makeText(getApplicationContext(),reqJson, Toast.LENGTH_LONG).show();

                        String n=null;
                        n=u.getDate();
                        if(check.equalsIgnoreCase("specific"))
                        {
                            if(n!=null)
                            if(n.equalsIgnoreCase(date)) {
                                pcount++;

                                mDatabase.child(u.getKey()).child("participated").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot1) {
                                        String j = "";


                                        for (DataSnapshot da : dataSnapshot1.getChildren()) {
                                            Participated ps = da.getValue(Participated.class);
                                            // parti.add(ps);
                                            j += ps.getEventname() + "/";


                                        }
                                        Pa k = new Pa(j);
                                        //StorePar pi=new StorePar(j);

                                    }


                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                                my_list.add(u);

                                tolbal = tolbal + Integer.parseInt(u.getBalance());
                                tolpay = tolpay + Integer.parseInt(u.getPaid());
                                TextView pta = (TextView) findViewById(R.id.showtotal);
                                //int b=sh.getInt();
                                pta.setText(String.valueOf(tolpay));
                                TextView pta1 = (TextView) findViewById(R.id.showbalance);
                                pta1.setText(String.valueOf(tolbal));
                                // Toast.makeText(getApplicationContext(),String.valueOf(tolbal),Toast.LENGTH_LONG).show();

                            }
                        }else if(check.equalsIgnoreCase("all"))
                        {
                            pcount++;

                            mDatabase.child(u.getKey()).child("participated").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot1) {
                                    String j = "";


                                    for (DataSnapshot da : dataSnapshot1.getChildren()) {
                                        Participated ps = da.getValue(Participated.class);
                                        // parti.add(ps);
                                        j += ps.getEventname() + "/";


                                    }
                                    Pa k = new Pa(j);
                                    //StorePar pi=new StorePar(j);

                                }


                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                            my_list.add(u);

                            tolbal = tolbal + Integer.parseInt(u.getBalance());
                            tolpay = tolpay + Integer.parseInt(u.getPaid());
                            TextView pta = (TextView) findViewById(R.id.showtotal);
                            //int b=sh.getInt();
                            pta.setText(String.valueOf(tolpay));
                            TextView pta1 = (TextView) findViewById(R.id.showbalance);
                            pta1.setText(String.valueOf(tolbal));
                            // Toast.makeText(getApplicationContext(),String.valueOf(tolbal),Toast.LENGTH_LONG).show();
                            lv.setAdapter( new CustomAdapter1(Show12.this,my_list,StorePar.getA()));


                        }

                    } else
                    {
                        Toast.makeText(getApplicationContext(), "email not found", Toast.LENGTH_LONG).show();
                    }
                }
                lv.setAdapter( new CustomAdapter1(Show12.this,my_list,StorePar.getA()));
                TextView pc=(TextView)findViewById(R.id.showp);
                pc.setText(String.valueOf(pcount));



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("sfdg", "Failed to read value.", error.toException());
            }
        });
       // Toast.makeText(getApplicationContext(),)
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

}

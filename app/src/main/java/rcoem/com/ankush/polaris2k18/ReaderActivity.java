package com.example.ankush.polaris2k18;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.HashMap;

public class ReaderActivity extends AppCompatActivity {
    private Button scan_btn;
    TextView name,ph,email,pay,college,bal,p,key;
    private FirebaseAuth mAuth=null;
    SharedPreferences pr;
    SharedPreferences.Editor editor;
    public final static String QRREADER="QRREADER";
    private DatabaseReference mDatabase;
    public static  String text2Qr,QR="";
    HashMap<String,ArrayList<Group> > hgp=new HashMap<>();
    HashMap<String,ArrayList<Participated> > hpar=new HashMap<>();
    HashMap<String,User > huser=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }



        scan_btn = (Button) findViewById(R.id.scan_btn);
        final Activity activity = this;
        name = (TextView) findViewById(R.id.reader_name);
        ph = (TextView) findViewById(R.id.reader_ph);
        email = (TextView) findViewById(R.id.reader_email);
        college = (TextView) findViewById(R.id.reader_college);
        pay = (TextView) findViewById(R.id.reader_paid);
        bal = (TextView) findViewById(R.id.reader_bal);
        p = (TextView) findViewById(R.id.reader_parti);
        key = (TextView) findViewById(R.id.reader_key);
        mAuth = FirebaseAuth.getInstance();
        pr=getSharedPreferences(QRREADER,MODE_PRIVATE);
        editor=pr.edit();
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });
    }
void Read(String mail)
{

    mDatabase = FirebaseDatabase.getInstance().getReference("Users");
    mDatabase.keepSynced(true);

    final String finalMail =mail;

   // Toast.makeText(ReaderActivity.this,"Reader:"+finalMail, Toast.LENGTH_LONG).show();

    mDatabase.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
            {

                final ArrayList<Participated> par=new ArrayList<>();
                par.clear();
                Log.d("inside Reader","adsf");

                if (postSnapshot != null)
                {
                    User u = postSnapshot.getValue(User.class);

                    String n=null;
                    n=u.getKey();
                    if(n!=null)
                        if(n.equalsIgnoreCase(finalMail))
                        {


                            editor.putString("key",u.getReceipt());
                            editor.putString("name",u.getName());
                            editor.putString("phone",u.getPhone());
                            editor.putString("college",u.getCollege());
                            editor.putString("paid",u.getPaid());
                            editor.putString("email",u.getEmail());
                            editor.putString("balance",u.getBalance());
                            mDatabase.child(u.getKey()).child("participated").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot1)
                                {                                    String j="";


                                    for(DataSnapshot da:dataSnapshot1.getChildren())
                                    {
                                        Participated ps =da.getValue(Participated.class);
                                        par.add(ps);
                                        j += ps.getEventname() + "/";


                                    }
                                    editor.putString("parti",j);
                                    editor.commit();
                                    editor.apply();
                                    //p.setText(j);

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }

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

    bal.setText(pr.getString("balance",null));
    name.setText(pr.getString("name",null));
    college.setText(pr.getString("college",null));
    ph.setText(pr.getString("phone",null));
    key.setText(pr.getString("key",null));
    p.setText(pr.getString("parti",null));
    pay.setText(pr.getString("paid",null));
    email.setText(pr.getString("email",null));

}
void setT()
{
    bal.setText(pr.getString("balance",null));
    name.setText(pr.getString("name",null));
    college.setText(pr.getString("college",null));
    ph.setText(pr.getString("phone",null));
    key.setText(pr.getString("key",null));
    p.setText(pr.getString("parti",null));
    pay.setText(pr.getString("paid",null));
    email.setText(pr.getString("email",null));

}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
        {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            } else
                {
                    ReaderActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            key.setText(result.getContents().toString());

                            Read(result.getContents().toString());

                        }
                    });

                Toast.makeText(this,"Result:"+result.getContents(), Toast.LENGTH_LONG).show();

                }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        setT();
        super.onSaveInstanceState(outState);

    }
    @Override
    protected void onRestoreInstanceState(Bundle s)
    {
        super.onRestoreInstanceState(s);
        setT();
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

package com.example.ankush.polaris2k18;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    TextView name,ph,email,pay,college,bal,p,key,receipt;
    final static String MYPROFILE="PROFILE";
    SharedPreferences.Editor editor;
    SharedPreferences s;
    Button ref;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolprofile);
//        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        ref=(Button)findViewById(R.id.ref);
        s=getSharedPreferences(MYPROFILE,MODE_PRIVATE);
        name=(TextView)findViewById(R.id.u_name);
        ph=(TextView)findViewById(R.id.u_ph);
        receipt=(TextView)findViewById(R.id.u_receipt);
        email=(TextView)findViewById(R.id.u_email);
        college=(TextView)findViewById(R.id.u_college);
        pay=(TextView)findViewById(R.id.u_paid);
        bal=(TextView)findViewById(R.id.u_balance);
        p=(TextView)findViewById(R.id.u_parti);
        key=(TextView)findViewById(R.id.u_key);
        Bundle b=getIntent().getExtras();
        if(b!=null)
        {
            editor=s.edit();

            editor.putString("name",b.getString("name"));
            name.setText(b.getString("name"));
            ph.setText(b.getString("phone"));
            receipt.setText(b.getString("receipt"));

            editor.putString("receipt",b.getString("receipt"));
            editor.putString("phone",b.getString("phone"));
            college.setText(b.getString("college"));
            editor.putString("college",b.getString("college"));
            pay.setText(b.getString("paid"));
            editor.putString("paid",b.getString("paid"));
            email.setText(b.getString("email"));
            editor.putString("email",b.getString("email"));
            key.setText(b.getString("key"));
            editor.putString("key",b.getString("key"));
            bal.setText(b.getString("balance"));
            editor.putString("balance",bal.getText().toString());
            String g = Pa.getS();
            p.setText(g);
            editor.putString("participated",p.getText().toString());
            editor.putString("check","true");
            editor.commit();
            editor.apply();
        }
        else
        {
            name.setText(s.getString("name",null));
            ph.setText(s.getString("phone",null));
            college.setText(s.getString("college",null));
            pay.setText(s.getString("paid",null));
            email.setText(s.getString("email",null));
            receipt.setText(s.getString("receipt",null));
             key.setText(s.getString("key",null));
            bal.setText(s.getString("balance",null));
             p.setText(s.getString("participated",null));
        }
        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference("Users");
                mDatabase.keepSynced(true);

                final String finalMail = email.getText().toString();
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
                                    if(n.equalsIgnoreCase(finalMail))
                                    {

                                        editor=s.edit();


                                        name.setText(u.getName());
                                        ph.setText(u.getPhone());
                                        college.setText(u.getCollege());
                                        pay.setText(u.getPaid());
                                        email.setText(u.getEmail());
                                        key.setText(u.getKey());
                                        bal.setText(u.getBalance());
                                        receipt.setText(u.getReceipt());
                                        editor.putString("name",name.getText().toString());
                                        editor.putString("phone",ph.getText().toString());
                                        editor.putString("college",college.getText().toString());
                                        editor.putString("paid",pay.getText().toString());
                                        editor.putString("email",email.getText().toString());
                                        editor.putString("key",key.getText().toString());
                                        editor.putString("balance",bal.getText().toString());
                                        editor.putString("receipt",receipt.getText().toString());

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
                                                p.setText(j);
                                                editor.putString("participated",p.getText().toString());
                                                editor.commit();
                                                editor.apply();


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

            }
        });
        }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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

package com.example.ankush.polaris2k18;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.onesignal.OneSignal;

import java.util.ArrayList;

public class UserLogin extends AppCompatActivity {
private String check=null;//=false;
    Button b1,b2,b3;
    EditText e1,name,ph;
    Button btGo1;
    SharedPreferences.Editor editor;
    SharedPreferences s;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        s=getSharedPreferences(Profile.MYPROFILE,MODE_PRIVATE);
        name=(EditText)findViewById(R.id.user_name);
        e1=(EditText)findViewById(R.id.user_email);
        ph=(EditText)findViewById(R.id.user_phone);
        btGo1=(Button)findViewById(R.id.bt_go1);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }

        check=null;
        s=getSharedPreferences(Profile.MYPROFILE,MODE_PRIVATE);
        check=s.getString("check",null);
        if(check!=null)
        if(check.equalsIgnoreCase("true"))
        {

            Intent i=new Intent(UserLogin.this,Profile.class);


             startActivity(i);
            finish();
        }
        btGo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplication(),"Please Wait...",Toast.LENGTH_LONG).show();

                startSignIn();
            }
        });

    }


    void startSignIn()
    {
        String email=e1.getText().toString();

        if(TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(ph.getText().toString()) || TextUtils.isEmpty(name.getText().toString()))
        {
            Toast.makeText(UserLogin.this,"Field is empty",Toast.LENGTH_LONG).show();

        }
        else {
            mDatabase = FirebaseDatabase.getInstance().getReference("Users");
            final String finalMail = email;
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

                                    final Intent i=new Intent(UserLogin.this,Profile.class);
                                    final Bundle b=new Bundle();
                                    b.putString("name",u.getName());
                                    b.putString("college",u.getCollege());
                                    b.putString("email",u.getEmail());
                                    b.putString("key",u.getKey());
                                    b.putString("phone",u.getPhone());
                                    b.putString("paid",u.getPaid());
                                    b.putString("balance",u.getBalance());
                                    b.putString("ranme",u.getRname());
                                    b.putString("receipt",u.getReceipt());
                                    OneSignal.sendTag("User_ID", u.getEmail());
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
                                            Toast.makeText(getApplicationContext(), j, Toast.LENGTH_SHORT).show();
                                            Pa k=new Pa(j);
                                            b.putString("participated",j);
                                            i.putExtras(b);
                                            editor=s.edit();
                                            editor.putString("check","true");
                                            editor.commit();
                                            editor.apply();
                                            startActivity(i);

                                            finish();
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

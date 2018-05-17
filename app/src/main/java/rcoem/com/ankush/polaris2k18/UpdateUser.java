package com.example.ankush.polaris2k18;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateUser extends AppCompatActivity {
Button ser;
    EditText e;
    HashMap<String,ArrayList<Participated> > hpar=new HashMap<>();

    private DatabaseReference mDatabase;
    public static  String text2Qr,QR="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        e=(EditText)findViewById(R.id.editText5);
       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }
        ser=(Button)findViewById(R.id.search);
        ser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail="";
                mail=e.getText().toString();
                if(mail.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter Email id",Toast.LENGTH_LONG).show();

                }
                else
                {
                    mDatabase = FirebaseDatabase.getInstance().getReference("Users");
                    final String finalMail = mail;
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
                                    final Intent i=new Intent(UpdateUser.this,Registration.class);
                                    final Bundle b=new Bundle();
                                    b.putString("name",u.getName());
                                    b.putString("college",u.getCollege());
                                    b.putString("email",u.getEmail());
                                    b.putString("key",u.getKey());
                                    b.putString("date",u.getDate());
                                    b.putString("phone",u.getPhone());
                                    b.putString("paid",u.getPaid());
                                    b.putString("balance",u.getBalance());
                                    b.putString("ranme",u.getRname());
                                    b.putString("receipt",u.getReceipt());
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
                                            Pa k=new Pa(j);
                                            b.putString("participated",j);
                                            i.putExtras(b);

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
}

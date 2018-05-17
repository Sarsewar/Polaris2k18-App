package com.example.ankush.polaris2k18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Validate extends AppCompatActivity {
EditText key;
    Button b;
    DatabaseReference mDatabase=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }
        key=(EditText)findViewById(R.id.masterkey);
        b=(Button)findViewById(R.id.masterButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String k=key.getText().toString();

                mDatabase = FirebaseDatabase.getInstance().getReference("MasterKey");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {

                        MasterKey ps =dataSnapshot.getValue(MasterKey.class);
                        String reader1=ps.getReader();
                        String pt12=ps.getPT();
                        String admin12=ps.getAdmin();
                        if(k.equals(pt12))
                        {
                            startActivity(new Intent(Validate.this,MainActivity.class));
                        }
                        else if(k.equalsIgnoreCase(admin12))
                        {
                            startActivity(new Intent(Validate.this,Teacher.class));
                        }
                        else if(k.equalsIgnoreCase(reader1))
                        {
                            startActivity(new Intent(Validate.this,ReaderActivity.class));

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Wrong key...",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

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
}

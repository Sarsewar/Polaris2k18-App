package com.example.ankush.polaris2k18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Notice extends AppCompatActivity {
    TextView t;
    private DatabaseReference mDatabase;
     HashMap<String, ArrayList<NoticeClass>> hgp=new HashMap<>();
    ListView lv;
    static int  counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        lv=(ListView)findViewById(R.id.listview);
        refresh();
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        String j=null;
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }
       // TextView t=(TextView)findViewById(R.id.textView2);
        //Toast.makeText(Notice.this,"hey",Toast.LENGTH_LONG).show();

        /*j=getIntent().getStringExtra("json");
        if(j!=null)
        {
            String s=j.toString().replaceAll("\\{","");
            s=s.replaceAll("\\}","");
            t.setText(s);

        }*/
        ArrayList<NoticeClass> mylist=hgp.get("0");
//        Toast.makeText(getApplicationContext(), mylist.size()+"a", Toast.LENGTH_LONG).show();

        //


    }
    void refresh()
    {
        try{

            mDatabase = FirebaseDatabase.getInstance().getReference("Notices");
            mDatabase.keepSynced(true);
            mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                final ArrayList<NoticeClass> par = new ArrayList<>();
            if(dataSnapshot!=null) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if (postSnapshot != null) {
                        NoticeClass not = postSnapshot.getValue(NoticeClass.class);
                        par.add(not);

                    }
                }
            }
                hgp.put("0",par);
                Collections.reverse(par);

                lv.setAdapter(new CustomAdapter(Notice.this,par));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        }
        catch (Exception e)
        {
            e.printStackTrace();
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

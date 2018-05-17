package com.example.ankush.polaris2k18;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Ankush on 1/14/2018.
 */

public class CustomAdapter1 extends BaseAdapter {
Context c;
    LayoutInflater mLayoutInflater;
    ArrayList<User> entries;
    ArrayList<String> par;
   public  int tolpay=0;
    public int tolbal=0;
    public static final String PP="PP";
    private DatabaseReference mDatabase;
SharedPreferences sh;
    SharedPreferences.Editor editor;
    CustomAdapter1(Context mContext, ArrayList<User> entries, ArrayList<String> par)
    {
        this.c=mContext;
        this.entries=entries;
        this.par=par;
        mLayoutInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sh=c.getSharedPreferences(PP,c.MODE_PRIVATE);
        editor=sh.edit();

        mDatabase = FirebaseDatabase.getInstance().getReference("Users");

    }
    void data(User obj, final TextView t)
    {

    }
    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public User getItem(int position) {
        return entries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final LinearLayout listview;
        listview = (LinearLayout) mLayoutInflater.inflate(
                R.layout.show_list, parent, false);

         User obj=getItem(position);
        TextView rno=(TextView)listview.findViewById(R.id.rno);
        rno.setText(obj.getReceipt());
        TextView name=(TextView)listview.findViewById(R.id.tname);
        name.setText(obj.getName());

        TextView email=(TextView)listview.findViewById(R.id.shemail);
        email.setText(obj.getEmail());
        TextView phone=(TextView)listview.findViewById(R.id.shphone);
        phone.setText(obj.getPhone());
        TextView paid=(TextView)listview.findViewById(R.id.shpaid);
        paid.setText(obj.getPaid());
        TextView bal=(TextView)listview.findViewById(R.id.shbal);
        bal.setText(obj.getBalance());
         //Pa p=par.get(position);

        tolbal=tolbal+Integer.parseInt(obj.getBalance());
        tolpay=tolpay+Integer.parseInt(obj.getPaid());


        mDatabase.child(obj.getKey()).child("participated").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1)
            {
                String j="";


                for(DataSnapshot da:dataSnapshot1.getChildren())
                {
                    Participated ps =da.getValue(Participated.class);
                    // parti.add(ps);
                    j += ps.getEventname() + "/";


                }
                final TextView part=(TextView)listview.findViewById(R.id.shpar);

                part.setText(j);
                Pa k=new Pa(j);
                editor.putString("par",j);
                editor.apply();
                editor.commit();
                //StorePar pi=new StorePar(j);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        TextView col=(TextView)listview.findViewById(R.id.clg);
        col.setText(obj.getCollege());
        editor.putString("pay", String.valueOf(tolpay));
        editor.putString("bal", String.valueOf(tolbal));
        editor.apply();
        editor.commit();

        return listview;
    }
}

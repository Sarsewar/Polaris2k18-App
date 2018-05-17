package com.example.ankush.polaris2k18;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Add Participants Details to firebase and Calculate the total fare.
 */

public class Registration extends AppCompatActivity implements View.OnClickListener {
   // public static ArrayList<String> arr;
    int totals=0;
    CheckBox c1,c2,c3,c4,c5,w1,w2,w3,ios,c6,c7,c8;
    private final static String PREF="MYPREF";
    String total="";
    int eventno=10;
    String userId="";
    int pcount=0;
    String parti;
    boolean up=false;
    String s="";
    String key="";
    String[] spl;
    Button button,qr;
    private int mYear, mMonth, mDay;

    public static ArrayList<String> arr=new ArrayList<>();
    EditText name,ph,email,pay,college,bal,receipt,date;
    Intent i;
    static String ontTag;
    Dialog dialog;
    String[] sp;
    String rname;
    DatabaseReference mDatabase=null,mDatabase12=null,mDatabase1=null,io =null,pubteam,pubteam1;
    SharedPreferences pr;
    final static String MYPROFILE="PROFILE";
ImageView cal;
    SharedPreferences.Editor editor;
    Participated part;
    EventUsers eu;
    String status="false";
    public FirebaseAuth mAuth=null;
    FirebaseUser fuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        up=false;
        name=(EditText)findViewById(R.id.pname);
        ph=(EditText)findViewById(R.id.mno);
        email=(EditText)findViewById(R.id.email);
        college=(EditText)findViewById(R.id.college);
        pay=(EditText)findViewById(R.id.pay_Money);
        bal=(EditText)findViewById(R.id.bal);
        bal.setText("0");
        date=(EditText)findViewById(R.id.pdate);
        cal=(ImageView)findViewById(R.id.cal1);
        receipt=(EditText)findViewById(R.id.preceipt);
cal.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        // Toast.makeText(getApplicationContext(),"ankush ",Toast.LENGTH_LONG).show();
        DatePickerDialog datePickerDialog = new DatePickerDialog(Registration.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }
});
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }
        pr=getSharedPreferences(MYPROFILE,MODE_PRIVATE);
        editor=pr.edit();
         mAuth = FirebaseAuth.getInstance();
        pr = getSharedPreferences(PREF, MODE_PRIVATE);
        fuser=mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        pubteam1= pubteam= FirebaseDatabase.getInstance().getReference("Publicity_Team");
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        status="false";

        rname = pr.getString("Rname", null);

        ios= (CheckBox) findViewById(R.id.iOS);
         c1 = (CheckBox) findViewById(R.id.ch1);
        c2 = (CheckBox) findViewById(R.id.ch2);
        c3 = (CheckBox) findViewById(R.id.ch3);
        c4 = (CheckBox) findViewById(R.id.ch4);
        c5 = (CheckBox) findViewById(R.id.ch5);
        c6 = (CheckBox) findViewById(R.id.ch6);
        c7 = (CheckBox) findViewById(R.id.ch7);
        c8 = (CheckBox) findViewById(R.id.ch8);

        button=(Button)findViewById(R.id.button);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c3.setOnClickListener(this);c5.setOnClickListener(this);c6.setOnClickListener(this);
        c7.setOnClickListener(this);
        c8.setOnClickListener(this);
        w1 = (CheckBox) findViewById(R.id.w1);
       w2 = (CheckBox) findViewById(R.id.w2);
       // w3 = (CheckBox) findViewById(R.id.w3);
        w1.setOnClickListener(this);
       w2.setOnClickListener(this);
       // w3.setOnClickListener(this);
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Please Select Home Button",Toast.LENGTH_LONG).show();
                Intent j=new Intent(Registration.this,PT_Home.class);
               // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(j);
            }
        });
        fab.setVisibility(View.INVISIBLE);
        Bundle b=getIntent().getExtras();
        if(b!=null)
        {
            fab.setVisibility(View.VISIBLE);
            up=true;
            date.setText(b.getString("date"));
            name.setText(b.getString("name"));
            ph.setText(b.getString("phone"));
            college.setText(b.getString("college"));
            pay.setText(b.getString("paid"));
            email.setText(b.getString("email"));
            userId=key=b.getString("key");
            receipt.setText(b.getString("receipt"));
            Key ka=new Key(key,true);
            status="true";
            bal.setText(b.getString("balance"));
               String g=Pa.getS();
               sp=g.split("/");

            for(int j=0;j<sp.length;j++)
            {

                if(sp[j].equalsIgnoreCase(c1.getText().toString()))
                {
                    c1.setChecked(true);
                }
                else if(sp[j].equalsIgnoreCase(c2.getText().toString()))
                {
                    c2.setChecked(true);
                }
                else if(sp[j].equalsIgnoreCase(c3.getText().toString()))
                {
                    c3.setChecked(true);
                }
                else if(sp[j].equalsIgnoreCase(c4.getText().toString()))
                {
                    c4.setChecked(true);
                }
                else if(sp[j].equalsIgnoreCase(w1.getText().toString()))
                {
                    w1.setChecked(true);
                }
                else if(sp[j].equalsIgnoreCase(w2.getText().toString()))
                {
                    w2.setChecked(true);
                }
                else if(sp[j].equalsIgnoreCase(c5.getText().toString()))
                    {
                        c5.setChecked(true);
                    }
                    else if(sp[j].equalsIgnoreCase(c6.getText().toString()))
                    {
                        c6.setChecked(true);
                    }
                else if(sp[j].equalsIgnoreCase(c7.getText().toString()))
                {
                    c7.setChecked(true);
                }
                else if(sp[j].equalsIgnoreCase(c8.getText().toString()))
                {
                    c8.setChecked(true);
                }


            }


            b.clear();

        }

        dialog= new Dialog(Registration.this);
        dialog.setContentView(R.layout.custom);
        dialog.setTitle("Result");
        final TextView text = (TextView) dialog.findViewById(R.id.text);


        final Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        Button ca = (Button) dialog.findViewById(R.id.dialogButtonCancel);
        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(up)
                {new Doit().execute();
                   }

                    sendData();
                     i= new Intent(Registration.this, PT_Home.class);
                        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         Log.d("userid",userId);
                        Log.d("total", String.valueOf(totals));
                        Key ka=new Key(userId,false);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),"Successfully Register",Toast.LENGTH_LONG).show();


                dialog.dismiss();





            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String n="";n=name.getText().toString();
                String c="";c=college.getText().toString();
                String e="";e=email.getText().toString();
                String p="";p=ph.getText().toString();
                String r=receipt.getText().toString();
                if(n.equals("") || p.equals("") || e.equals("") || c.equals("") || receipt.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(Registration.this,"Field is empty",Toast.LENGTH_LONG).show();

                }
                else
                    {
                        s="Total:";
                        s+=totals+"\n";
                        pcount=0;
                        parti= getCheckedList();
                       spl = parti.split(":");
                        s+="Participated in:";
                        for (int i = 0; i < pcount; i++)
                        {
                            s+=spl[i]+",";
                        }
                        text.setText(s);
                        dialog.show();

                }

            }
        });
    }

public class Doit extends AsyncTask<Void,Void,Void>
{

    @Override
    protected Void doInBackground(Void... params) {


        // userId=key;
        //mDatabase.child(userId).removeValue();
        for(int k=0;k<sp.length;k++)
        {
            mDatabase12 = FirebaseDatabase.getInstance().getReference(sp[k]);
            mDatabase12.child(key).removeValue();

        }
          return null;
    }


    @Override
    protected void onPostExecute(Void result)
    {
    }
}
    void sendData() {
        pcount = 0;
        parti = getCheckedList();
        final String rname = pr.getString("Rname", null);
        if (up)
            userId = key;
        else
            userId = mDatabase.push().getKey();


        User user = new User(name.getText().toString(), email.getText().toString(), userId, ph.getText().toString(), rname, bal.getText().toString(), pay.getText().toString(), college.getText().toString(),receipt.getText().toString(),date.getText().toString());
        mDatabase.child(userId).setValue(user);
         String[] spl = parti.split(":");


        for (int i = 0; i < pcount; i++)
        {
             part= new Participated(spl[i], date.getText().toString());
             mDatabase.child(userId).child("participated").push().setValue(part);
             mDatabase1 = FirebaseDatabase.getInstance().getReference(spl[i]);
             mDatabase1.child(userId);
             eu = new EventUsers(receipt.getText().toString(), email.getText().toString(), ph.getText().toString(), userId,date.getText().toString());
             mDatabase1.child(userId).setValue(eu);

        }
        if(ios.isChecked())
        {
             io = FirebaseDatabase.getInstance().getReference("iOS");
            IOS s=new IOS(email.getText().toString(), ph.getText().toString());
            io.child(userId).setValue(s);


        }


        /**
         * For Group addition property.
         * */
                /*for(int i=0;i<5;i++)
                {
                    Group group=new Group(i+"","987456123","false");
                    mDatabase.child(userId).child("group").push().setValue(group);


                }*/


        //finish();
    }


    private String getCheckedList(){
        String cl="";
        if(c1.isChecked())
        {cl+=c1.getText().toString()+":";
            pcount++;
        }
        if(c2.isChecked()) {
            cl += c2.getText().toString() + ":";
            pcount++;
        }
        if(c3.isChecked())
        {  cl+=c3.getText().toString()+":";
            pcount++;
        }
        if(c4.isChecked()) {
            cl += c4.getText().toString() + ":";
            pcount++;
        }


        if(w1.isChecked())
        {  cl+=w1.getText().toString()+":";pcount++;
        }
        if(w2.isChecked())
        {  cl+=w2.getText().toString()+":";pcount++;
        }
        if(c5.isChecked())
        {  cl+=c5.getText().toString()+":";pcount++;
        }
        if(c6.isChecked())
        {  cl+=c6.getText().toString()+":";pcount++;
        }

        if(c7.isChecked())
        {  cl+=c7.getText().toString()+":";pcount++;
        }
        if(c8.isChecked())
        {  cl+=c8.getText().toString()+":";pcount++;
        }
        return cl;
    }

    private String getEightBits(){
        String eight="";
        if(c1.isChecked())
        {eight+="1";
            arr.add(c1.getText().toString());

        }else eight+="0";
        if(c2.isChecked())
        {eight+="1";

            arr.add(c2.getText().toString());

        }else eight+="0";
        if(c3.isChecked()){eight+="1";
            arr.add(c3.getText().toString());}else eight+="0";
        if(c4.isChecked()){eight+="1";
            arr.add(c4.getText().toString());}else eight+="0";
        if(w1.isChecked()){eight+="1";
            }else eight+="0";
        if(c5.isChecked()){eight+="1";
           }else eight+="0";
        if(c6.isChecked()){eight+="1";
            }else eight+="0";
        if(c7.isChecked()){eight+="1";
        }else eight+="0";
       //eight+="0";
        if(c8.isChecked()){eight+="1";
        }else eight+="0";
        if(w2.isChecked()){eight+="1";
        }else eight+="0";
        return eight;
    }

    @Override
    public void onClick(View view) {
        //Seq:code-realy,krack-jack,quiz,python,hotheads,nemesis,los,project,Angluar;
        int ar[]={150,200,100,250,400,250,50,500,250,400};
        String tmp=getEightBits();
        totals=0;
        for(int i=0;i<eventno;i++)
        {
            totals+=ar[i]*Integer.parseInt(String.valueOf(tmp.charAt(i)));
        }
            total=String.valueOf(totals);
        pay.setText(total);
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

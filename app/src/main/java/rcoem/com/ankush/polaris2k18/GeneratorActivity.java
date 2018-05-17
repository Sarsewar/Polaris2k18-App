package com.example.ankush.polaris2k18;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.HashMap;

public class GeneratorActivity extends AppCompatActivity {
    TextView text;
    Button gen_btn,r;
    ImageView image;
    private final static String PREF="MYPREF";

    // private DatabaseReference mDatabase;
    public static  String text2Qr,QR="";
    HashMap<String,ArrayList<Group> > hgp=new HashMap<>();
    HashMap<String,ArrayList<Participated> > hpar=new HashMap<>();
    HashMap<String,User > huser=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);
        text = (TextView) findViewById(R.id.text);
        gen_btn = (Button) findViewById(R.id.gen_btn);
          r=(Button)findViewById(R.id.button4);
       // Log.d("Generator Activity",userId);

        r.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //sendNotification();

                startActivity(new Intent(GeneratorActivity.this,PT_Home.class));
                finish();
               /* mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                        {

                            final ArrayList<Participated> par=new ArrayList<>();
                            final ArrayList<Group> gar=new ArrayList<>();
                            par.clear();
                            gar.clear();
                            //The PUSH ID OP WANTED

                            if (postSnapshot != null)
                            {
                                User u = postSnapshot.getValue(User.class);

                                huser.put(u.getKey(),u);
                                mDatabase.child(u.getKey()).child("group").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot1)
                                    {
                                        for(DataSnapshot da:dataSnapshot1.getChildren())
                                        {
                                            Group ps =da.getValue(Group.class);
                                            gar.add(ps);

                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                hgp.put(u.getKey(),gar);
                                // gar.clear();

                                mDatabase.child(u.getKey()).child("participated").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot1)
                                    {
                                        for(DataSnapshot da:dataSnapshot1.getChildren())
                                        {
                                            Participated ps =da.getValue(Participated.class);
                                            par.add(ps);

                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                hpar.put(u.getKey(),par);

                                //par.clear();


                            } else {
                                Toast.makeText(getApplicationContext(), "Null", Toast.LENGTH_LONG).show();
                            }
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("sfdg", "Failed to read value.", error.toException());
                    }
                });

                for(User auser : huser.values())
                {
                    text2Qr="";
                    if(text.getText().toString().equals(auser.getKey())) {
                        text2Qr = auser.getName() + "\n" + auser.getEmail() + "\n" + auser.getPhone() + "\n";
                        String g = "Group:";
                        ArrayList<Group> arg = hgp.get(auser.getKey());
                        for (Group agrp : arg) {
                            g += agrp.getName() + ":" + agrp.getPhone() + ":" + agrp.getStatus() + "\n";
                            //   Toast.makeText(getApplicationContext(),g, Toast.LENGTH_LONG).show();

                        }
                        text2Qr += g;
                        String p = "Participated:";
                        ArrayList<Participated> apa = hpar.get(auser.getKey());
                        for (Participated apart : apa) {
                            p += apart.getEventname() + ":" + apart.getStatus() + "\n";
                            //Toast.makeText(getApplicationContext(),p, Toast.LENGTH_LONG).show();

                        }
                        text2Qr += p;
                        Toast.makeText(getApplicationContext(), text2Qr, Toast.LENGTH_LONG).show();
                    }
                }
*/



            }});

        image = (ImageView) findViewById(R.id.image);
        gen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text2Qr = getIntent().getStringExtra("userId");
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    image.setImageBitmap(bitmap);
                }
                catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);

    }
}

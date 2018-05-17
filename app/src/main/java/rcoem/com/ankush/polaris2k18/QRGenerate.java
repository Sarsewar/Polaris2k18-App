package com.example.ankush.polaris2k18;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class QRGenerate extends AppCompatActivity {
EditText e;
    Button s;    private DatabaseReference mDatabase;
    ImageView image;
    String em="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerate);
        e=(EditText)findViewById(R.id.qremail);
        s=(Button)findViewById(R.id.qrsearch);
        image = (ImageView) findViewById(R.id.image12);
       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setVisibility(View.INVISIBLE);
                em=e.getText().toString();
                if(em.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter Email id",Toast.LENGTH_LONG).show();

                }
                else
                {
                    mDatabase = FirebaseDatabase.getInstance().getReference("Users");
                    mDatabase.keepSynced(true);

                    final String finalMail = em;
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            boolean flag=true;
                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                            {
                                final ArrayList<Participated> par=new ArrayList<>();
                                par.clear();

                                if (postSnapshot != null)
                                {
                                    final User u = postSnapshot.getValue(User.class);
                                    String n=null;
                                    n=u.getEmail();
                                    if(n!=null)
                                    {
                                        if (n.equalsIgnoreCase(finalMail)) {

                                            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                                            try {
                                                BitMatrix bitMatrix = multiFormatWriter.encode(u.getKey(), BarcodeFormat.QR_CODE, 200, 200);
                                                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                                                image.setVisibility(View.VISIBLE);
                                                image.setImageBitmap(bitmap);
                                                //e.setText(n);
                                                Toast.makeText(getApplicationContext(),u.getKey(), Toast.LENGTH_SHORT).show();

                                                flag = false;
                                            } catch (WriterException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                } else
                                {
                                    Toast.makeText(getApplicationContext(), "email not found", Toast.LENGTH_SHORT).show();
                                }
                            }
                            if(flag)
                                Toast.makeText(getApplicationContext(), "email not found", Toast.LENGTH_LONG).show();



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

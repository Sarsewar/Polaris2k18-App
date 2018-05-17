package com.example.ankush.polaris2k18;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ParticipantQR extends AppCompatActivity {
ImageView img;
    final static String MYPROFILE="PROFILE";
    SharedPreferences.Editor editor;
    SharedPreferences s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_qr);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }
        img=(ImageView)findViewById(R.id.pqr);
        s=getSharedPreferences(MYPROFILE,MODE_PRIVATE);
        String f=null;
        f=s.getString("key",null);
        if(f!=null)
        {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(f, BarcodeFormat.QR_CODE, 200, 200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                img.setVisibility(View.VISIBLE);
                img.setImageBitmap(bitmap);
                //e.setText(n);
                //Toast.makeText(getApplicationContext(),u.getKey(), Toast.LENGTH_SHORT).show();

            } catch (WriterException e) {
                e.printStackTrace();
            }
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

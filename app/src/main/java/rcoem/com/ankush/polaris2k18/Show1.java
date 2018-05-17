package com.example.ankush.polaris2k18;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Calendar;

public class Show1 extends AppCompatActivity {
Button sh,ans;
    EditText d;
    RadioGroup grp;
    ImageView cal1;
    private int mYear, mMonth, mDay;


    RadioButton all,sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show1);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }
        ans=(Button)findViewById(R.id.anssec);
        ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Show1.this,Analysis.class);
                startActivity(i);
            }
        });
        sh=(Button)findViewById(R.id.show);
        d=(EditText)findViewById(R.id.date1);
        cal1=(ImageView)findViewById(R.id.cal1);
        final LinearLayout lay=(LinearLayout)findViewById(R.id.linearshow);
        lay.setVisibility(View.INVISIBLE);
        cal1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final Calendar c = Calendar.getInstance();
                                mYear = c.get(Calendar.YEAR);
                                mMonth = c.get(Calendar.MONTH);
                                mDay = c.get(Calendar.DAY_OF_MONTH);
                                // Toast.makeText(getApplicationContext(),"ankush ",Toast.LENGTH_LONG).show();
                                DatePickerDialog datePickerDialog = new DatePickerDialog(Show1.this,
                                        new DatePickerDialog.OnDateSetListener() {

                                            @Override
                                            public void onDateSet(DatePicker view, int year,
                                                                  int monthOfYear, int dayOfMonth) {

                                                d.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                            }
                                        }, mYear, mMonth, mDay);

                                datePickerDialog.show();
                            }
                        }
);
        grp=(RadioGroup)findViewById(R.id.rgrp);
        all=(RadioButton)findViewById(R.id.rall);
        sp=(RadioButton)findViewById(R.id.spdate);
all.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        lay.setVisibility(View.INVISIBLE);
    }
});
        sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sp.isChecked()) {
                    lay.setVisibility(View.VISIBLE);
                  //  Toast.makeText(getApplicationContext(),"sdfjasd",Toast.LENGTH_LONG).show();
                }
            }
        });
sh.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(sp.isChecked())
        {
            //lay.setVisibility(View.VISIBLE);

            String date=d.getText().toString();
            Intent i=new Intent(Show1.this,Show12.class);
            i.putExtra("show","specific");
            i.putExtra("date",date);
            startActivity(i);

        }
        if(all.isChecked())
        {
            Intent i=new Intent(Show1.this,Show12.class);
            i.putExtra("show","all");
            startActivity(i);
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

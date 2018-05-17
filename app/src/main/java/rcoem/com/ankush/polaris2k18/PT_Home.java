package com.example.ankush.polaris2k18;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.onesignal.OneSignal;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PT_Home extends AppCompatActivity {

    FirebaseAuth fauth;
    FirebaseUser fuser;

    static String ontTag;
    private FirebaseAuth mAuth=null;
    @InjectView(R.id.add_par)
    Button add_par;
    @InjectView(R.id.logout)
    Button logout;
    Button up,gen,show;
    Bundle b=null;
    FloatingActionButton fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pt__home);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }

        ButterKnife.inject(this);
        mAuth = FirebaseAuth.getInstance();
        up=(Button)findViewById(R.id.upd_par);
        gen=(Button)findViewById(R.id.generate);
        fa=(FloatingActionButton)findViewById(R.id.hf);
        show=(Button)findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i=new Intent(PT_Home.this,Show1.class);
                startActivity(i);
            }
        });
        fa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ij=new Intent(PT_Home.this,Home.class);
                ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(ij);
                finish();
            }
        });

        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o=new Intent(PT_Home.this,QRGenerate.class);
                startActivity(o);

            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i3 = new Intent(PT_Home.this,UpdateUser.class);
                startActivity(i3);
            }
        });
        fuser=mAuth.getCurrentUser();
        if(fuser!=null) {
            ontTag = fuser.getEmail();
            OneSignal.sendTag("User_ID", ontTag);
        }
    }
    @OnClick({ R.id.add_par,R.id.logout})
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.add_par:
                Explode explode = new Explode();
                explode.setDuration(100);
                getWindow().setExitTransition(explode);
                getWindow().setEnterTransition(explode);
                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                Intent i2 = new Intent(this,Registration.class);
                startActivity(i2, oc2.toBundle());
                // startSignIn();
                break;

            case R.id.logout:
                Explode explode2 = new Explode();
                explode2.setDuration(100);
                getWindow().setExitTransition(explode2);
                getWindow().setEnterTransition(explode2);
                ActivityOptionsCompat oc4 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent i4 = new Intent(this,MainActivity.class);
                startActivity(i4, oc4.toBundle());
                finish();
                break;
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

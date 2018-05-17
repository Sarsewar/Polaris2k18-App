package com.example.ankush.polaris2k18;


import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.transition.Explode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    /***
     * This is Login Page of Publicity Team
     * #Polaris2k18
     */
    Button b1,b2,b3;
    @InjectView(R.id.name)
    EditText etUsername;
    @InjectView(R.id.editText2)
    EditText etPassword;
    @InjectView(R.id.bt_go)
    Button btGo;
    @InjectView(R.id.cv)
    CardView cv;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    private FirebaseAuth mAuth=null;
    FirebaseUser fuser;
    static String ontTag;
    FirebaseDatabase database;
    FirebaseAuth fauth;
    private final static String PREF="MYPREF";
    DatabaseReference reference;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText e1,pass,e2,name,ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.name);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        fauth = FirebaseAuth.getInstance();
        reference = database.getReference("Publicity_Team");;
        reference.keepSynced(true);

        fuser=mAuth.getCurrentUser();

        if(fuser!=null) {
            Toast.makeText(getApplicationContext(), "Welcome:" + fuser.getEmail(), Toast.LENGTH_LONG).show();
            ontTag = fuser.getEmail();
            OneSignal.sendTag("User_ID", ontTag);
           // Toast.makeText(getApplicationContext(),"Welcome:"+ontTag,Toast.LENGTH_LONG).show();
        }

        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        //checking if user id already login or not.If yes then Jump to AccountActivity.classs file
        mAuthListener =new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged( FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null)
                {
                    startActivity(new Intent(MainActivity.this,PT_Home.class));

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login first!",Toast.LENGTH_LONG).show();
                }
            }
        };
        ButterKnife.inject(this);


    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    private void startSignIn()
    {
        final String email=e1.getText().toString();
        String pass=e2.getText().toString();
        final String na=name.getText().toString();


        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(na))
        {
            Toast.makeText(MainActivity.this,"Field is empty",Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(MainActivity.this,"Verifying...",Toast.LENGTH_LONG).show();
            mAuth = FirebaseAuth.getInstance();

            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this, "Check Email and Password!!", Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        SharedPreferences pr=getSharedPreferences(PREF,MODE_PRIVATE);
                        final SharedPreferences.Editor e=pr.edit();

                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot!=null)
                                {
                                    PubTeam g=dataSnapshot.getValue(PubTeam.class);
                                    if(email.equalsIgnoreCase(g.getName()))
                                    {

                                        e.putString("Rname",g.getKey());
                                        e.commit();
                                        e.apply();

                                    }
                                }
                            }


                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                        startActivity(new Intent(MainActivity.this,PT_Home.class));
                        finish();

                    }
                }
            });
        }
    }


    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;
            case R.id.bt_go:
                Explode explode = new Explode();
                explode.setDuration(10);
                getWindow().setExitTransition(explode);
                getWindow().setEnterTransition(explode);
                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                Toast.makeText(getApplication(),"Please Wait...",Toast.LENGTH_LONG).show();
                startSignIn();
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

package com.example.ankush.polaris2k18;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RegisterActivity extends AppCompatActivity {

    private Button mAddBtn2;
    private EditText mNameField;
    private EditText mEmailField2;
    private EditText mPasswordView,pho;

    private final static String PREF="MYPREF";
    private FirebaseAuth firebaseAuth;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.cv_add)
    CardView cvAdd;
    @InjectView(R.id.p_reg)
    Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }
        ButterKnife.inject(this);
        mNameField=(EditText)findViewById(R.id.NameField);
        mEmailField2=(EditText)findViewById(R.id.EmailField2);
        mPasswordView=(EditText)findViewById(R.id.PasswordField2);
        pho=(EditText)findViewById(R.id.phone);

        firebaseAuth=FirebaseAuth.getInstance();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ShowEnterAnimation();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /**
                * Button
                * */
               //  btnRegistration();
                Toast.makeText(getApplicationContext(),"Sorry,Cannot able to Signup..",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void btnRegistration()
    {
        Toast.makeText(getApplicationContext(),"Please wait...",Toast.LENGTH_SHORT).show();
        try {
            if(TextUtils.isEmpty(mNameField.getText().toString()) || TextUtils.isEmpty(mEmailField2.getText().toString()) || TextUtils.isEmpty(mPasswordView.toString()) || TextUtils.isEmpty(pho.getText().toString()))
            {
                Toast.makeText(RegisterActivity.this,"Field is empty",Toast.LENGTH_LONG).show();

            }
            else {

                // final ProgressDialog processDialog =ProgressDialog.show(RegistrationActivity.this,"Please Wait...","Processing...",true);
                firebaseAuth.createUserWithEmailAndPassword(mEmailField2.getText().toString(), mPasswordView.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplication(), "Registration Successful", Toast.LENGTH_SHORT).show();
                                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Publicity_Team");
                                    SharedPreferences pr = getSharedPreferences(PREF, MODE_PRIVATE);
                                    SharedPreferences.Editor e = pr.edit();


                                    String userId = mDatabase.push().getKey();
                                    e.putString("Rname",userId);
                                    e.commit();
                                    e.apply();
                                    String nam = mNameField.getText().toString();
                                    String p = pho.getText().toString();
                                    PubTeam pt = new PubTeam(mEmailField2.getText().toString(), p, userId);
                                    mDatabase.child(userId).setValue(pt);
                                    Intent i = new Intent(RegisterActivity.this, PT_Home.class);
                                    startActivity(i);
                                } else {

                                    Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        }
        catch(Exception e)
        {

        }

    }
    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(10);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose()
    {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
    @Override
    public void onBackPressed() {
        animateRevealClose();
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

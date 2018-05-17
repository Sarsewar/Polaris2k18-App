package com.example.ankush.polaris2k18;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Analysis extends AppCompatActivity {
TextView cr,kj,project,hh,nem,los,quiz,csgo,python,ang,tot;
    private DatabaseReference mDatabase;
    SharedPreferences pr;
    SharedPreferences.Editor editor;
    public final static String ANALYSIS1="Analysis";
Button ref;
    int crcount,kjcount,projectcount,hhcount,nemcount,loscount,quizcount,csgocount,pythoncount,angcount,total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.too));

        }
        ref=(Button)findViewById(R.id.a_ref);
        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crcount=kjcount=projectcount=hhcount=nemcount=loscount=quizcount=csgocount=pythoncount=angcount=total=0;

                func();
            }
        });
        pr=getSharedPreferences(ANALYSIS1,MODE_PRIVATE);
        editor=pr.edit();
         crcount=kjcount=projectcount=hhcount=nemcount=loscount=quizcount=csgocount=pythoncount=angcount=total=0;
tot=(TextView)findViewById(R.id.a_total);
        cr=(TextView)findViewById(R.id.a_cr);
        kj=(TextView)findViewById(R.id.a_kj);
        project=(TextView)findViewById(R.id.a_project);
        hh=(TextView)findViewById(R.id.a_hh);
        nem=(TextView)findViewById(R.id.a_nemesis);
        los=(TextView)findViewById(R.id.a_los);
        quiz=(TextView)findViewById(R.id.a_quiz);
        csgo=(TextView)findViewById(R.id.a_csgo);
        python=(TextView)findViewById(R.id.a_python);
        ang=(TextView)findViewById(R.id.a_ang);
        func();
    }
    void func()
    {
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        mDatabase.keepSynced(true);


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    if (postSnapshot != null)
                    {
                        User u = postSnapshot.getValue(User.class);

                        String n=null;
                        n=u.getDate();


                            mDatabase.child(u.getKey()).child("participated").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot1) {
                                    String j = "";


                                    for (DataSnapshot da : dataSnapshot1.getChildren())
                                    {
                                        Participated ps = da.getValue(Participated.class);

                                        function( ps.getEventname());

                                    }

                                    csgo.setText(String.valueOf(csgocount));
                                    editor.putString("cs",String.valueOf(csgocount));
                                    project.setText(String.valueOf(projectcount));
                                    editor.putString("project",String.valueOf(projectcount));
                                    cr.setText(String.valueOf(crcount));
                                    editor.putString("cr",String.valueOf(crcount));
                                    nem.setText(String.valueOf(nemcount));
                                    editor.putString("nem",String.valueOf(nem));
                                    hh.setText(String.valueOf(hhcount));
                                    editor.putString("hh",String.valueOf(hhcount));
                                    kj.setText(String.valueOf(kjcount));
                                    editor.putString("kj",String.valueOf(kjcount));
                                    python.setText(String.valueOf(pythoncount));
                                    editor.putString("python",String.valueOf(pythoncount));
                                    ang.setText(String.valueOf(angcount));
                                    editor.putString("ang",String.valueOf(angcount));

                                    quiz.setText(String.valueOf(quizcount));
                                    editor.putString("quiz",String.valueOf(quizcount));

                                    los.setText(String.valueOf(loscount));
                                    editor.putString("los",String.valueOf(loscount));
                                    tot.setText(String.valueOf(total));
                                    editor.apply();

                                    editor.commit();

                                }


                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });





                    } else
                    {
                        Toast.makeText(getApplicationContext(), "Please Try Again...", Toast.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("sfdg", "Failed to read value.", error.toException());
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
       // setT();
        super.onSaveInstanceState(outState);

    }
    @Override
    protected void onRestoreInstanceState(Bundle s)
    {
        super.onRestoreInstanceState(s);
        //setT();
    }
    void function(String s)
    {

        if(s.equalsIgnoreCase("Code Relay"))
        {
            crcount++;
           total++;
        }
        else if(s.equalsIgnoreCase("Krack-jack"))
        {
            kjcount++;
            total++;

        }
    else if(s.equalsIgnoreCase("Quiz"))
    {
        quizcount++;
        total++;

    }
    else if(s.equalsIgnoreCase("HotHeads"))
    {
        hhcount++;
        total++;

    }
    else if(s.equalsIgnoreCase("Nemesis"))
    {
        nemcount++;
       total++;

    } else if(s.equalsIgnoreCase("Lord of Stage"))
    {
        loscount++;
        total++;

    } else if(s.equalsIgnoreCase("Project"))
    {
        projectcount++;
        total++;

    }
    else if(s.equalsIgnoreCase("CS GO"))
    {
        csgocount++;
        total++;

    }
    else if(s.equalsIgnoreCase("Python"))
    {
        pythoncount++;
        total++;

    }
    else if(s.equalsIgnoreCase("Angular js"))
    {

        angcount++;
        total++;

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

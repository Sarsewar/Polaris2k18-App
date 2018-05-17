package com.example.ankush.polaris2k18;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.onesignal.OneSignal;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private EditText ed;
    private ImageButton bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10,bt11;
    private ImageView face,insta;
    private TextView website;
    SharedPreferences.Editor editor;
    SharedPreferences s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new MyNotificationOpenedHandler(Home.this))
                .setNotificationReceivedHandler( new MyNotificationReceivedHandler(Home.this) )
                .init();
        s=getSharedPreferences(Profile.MYPROFILE,MODE_PRIVATE);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,Notice.class));
            }
        });
        fab.setBackgroundColor(Color.parseColor("#05abf2"));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        bt1=(ImageButton)findViewById(R.id.button1);
        bt2=(ImageButton)findViewById(R.id.button2);
        bt3=(ImageButton)findViewById(R.id.button3);
        bt4=(ImageButton)findViewById(R.id.button4);
        bt5=(ImageButton)findViewById(R.id.button5);
        bt6=(ImageButton)findViewById(R.id.button6);
        //bt7=(ImageButton)findViewById(R.id.button7);
        bt8=(ImageButton)findViewById(R.id.button8);
        bt9=(ImageButton)findViewById(R.id.button9);
        bt10=(ImageButton)findViewById(R.id.button10);
        bt11=(ImageButton)findViewById(R.id.button11);

        face=(ImageView)findViewById(R.id.face);
        insta=(ImageView)findViewById(R.id.insta);
        website=(TextView)findViewById(R.id.website);

        Intent intent = getIntent();
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.facebook.com/polariscse/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://www.rcoempolaris.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.instagram.com/cse_polaris/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,events.class);
                i.putExtra("slot","1");
                i.putExtra("title","Code Relay");
                startActivity(i);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,events.class);
                i.putExtra("slot","2");
                i.putExtra("title","KrackJack");
                startActivity(i);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,events.class);
                i.putExtra("slot","3");
                i.putExtra("title","Nemesis");
                startActivity(i);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,events.class);
                i.putExtra("slot","4");
                i.putExtra("title","Quiz");
                startActivity(i);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,events.class);
                i.putExtra("slot","5");
                i.putExtra("title","HotHeads");
                startActivity(i);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,events.class);
                i.putExtra("slot","6");
                i.putExtra("title","Lord of Stage");
                startActivity(i);
            }
        });
      bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,events.class);
                i.putExtra("slot","7");
                i.putExtra("title","Angular Js");
                startActivity(i);
            }
        });
        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,events.class);
                i.putExtra("slot","8");
                i.putExtra("title","Counter Strike");
                startActivity(i);
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,events.class);
                i.putExtra("slot","9");
                i.putExtra("title","Python");
                startActivity(i);
            }
        });
      /*  bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,events.class);
                i.putExtra("slot","10");
                i.putExtra("title","Quiz");
                startActivity(i);
            }
        });*/
       bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,events.class);
                i.putExtra("slot","11");
                i.putExtra("title","Project");
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.badge) {
            String url = "http://www.rcoempolaris.com/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            startActivity(new Intent(Home.this,Home.class));
        } else if (id == R.id.nav_profile)
        {
            startActivity(new Intent(Home.this,UserLogin.class));

        } else if (id == R.id.nav_qr) {
            startActivity(new Intent(Home.this,ParticipantQR.class));

        } else if (id == R.id.nav_pt) {
            startActivity(new Intent(Home.this,Validate.class));

        } else if (id == R.id.nav_notice) {
            startActivity(new Intent(Home.this,Notice.class));

        } else if (id == R.id.nav_about) {
            startActivity(new Intent(Home.this,About.class));

        }/*else if(id==R.id.nav_sponsor)
        {
            startActivity(new Intent(Home.this,Sponsor.class));


        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

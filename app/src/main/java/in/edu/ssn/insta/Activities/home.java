package in.edu.ssn.insta.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import in.edu.ssn.insta.R;

public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView shop;
    ImageView insta;
    ImageView plants;
    ImageView gps;
    ImageView display;
    TextView events;
    int flag=0;

    Intent Insta_intent;
    Intent shop_intent;
    Intent gps_intent;
    Intent plant_intent;
    Intent event_intent;

    public void change_image(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if(flag==0){
                    display.setBackgroundResource(R.mipmap.dis1);
                    flag=2;
                }
                else if(flag==2){
                    display.setBackgroundResource(R.mipmap.dis2);
                    flag=3;
                }
                else if(flag==3){
                    display.setBackgroundResource(R.mipmap.dis3);
                    flag=4;
                }
                else if(flag==4){
                    display.setBackgroundResource(R.mipmap.dis4);
                    flag=0;
                }
            }
        }, 500);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        shop = (ImageView) findViewById(R.id.shop_home_btn);
        insta = (ImageView) findViewById(R.id.insta_home_btn);
        plants = (ImageView) findViewById(R.id.plantg_home_btn);
        gps = (ImageView) findViewById(R.id.GPS_home_btn);
        display=(ImageView)findViewById(R.id.display_img);
        events = (TextView)findViewById(R.id.Event_img_btn) ;

        shop.setOnClickListener(shop_redirect);
        insta.setOnClickListener(insta_redirect);
        gps.setOnClickListener(gps_redirect);
        plants.setOnClickListener(plant_redirect);
        events.setOnClickListener(event_redirect);


        gps_intent = new Intent(getApplicationContext(), map.class);
        Insta_intent = new Intent(getApplicationContext(), insta_home.class);
        shop_intent = new Intent(getApplicationContext(), shop.class);
        plant_intent = new Intent(getApplicationContext(), Plants.class);
        event_intent = new Intent(getApplicationContext(), Events.class);

        change_image();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            startActivity(startMain);
            finishAffinity();
            finish();

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
        if (id == R.id.action_settings) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("DEVELOPERS");
            builder1.setMessage(R.string.Developers);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
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
            startActivity(gps_intent);
            change_image();
        } else if (id == R.id.nav_gallery) {
            change_image();
            startActivity(plant_intent);
        } else if (id == R.id.nav_shop) {
            startActivity(shop_intent);
            change_image();
        } else if (id == R.id.nav_insta) {
            startActivity(Insta_intent);
            change_image();
        }
        else if (id == R.id.nav_logout) {
            SharedPref.putString(getApplicationContext(), "sp_Username", null);
            SharedPref.putString(getApplicationContext(), "sp_image_url", null);
            SharedPref.putString(getApplicationContext(), "sp_email", null);
            SharedPref.putBoolean(getApplicationContext(), "sp_loggedin", false);

            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            startActivity(startMain);
            finishAffinity();
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    View.OnClickListener insta_redirect = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(Insta_intent);
            change_image();

        }
    };
    View.OnClickListener shop_redirect = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(shop_intent);
            change_image();

        }
    };
    View.OnClickListener gps_redirect = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(gps_intent);
            change_image();

        }
    };
    View.OnClickListener plant_redirect = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(plant_intent);
            change_image();

        }
    };
    View.OnClickListener event_redirect = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(event_intent);
            change_image();

        }
    };


}

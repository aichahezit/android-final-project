package a00906732.bcit.ca.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeekViewNav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button button3;
    Button button4;
    Button button5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*from original WeekView class*/
        Intent intent = getIntent();
        TextView welcome = (TextView)findViewById(R.id.textView2);
        welcome.setText("Welcome " + MainActivity.username + "!"); //welcome message

        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

        Date temp = new Date();
        button3.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date(temp.getTime() + 1 * (1000 * 60 * 60 * 24))));
        button4.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date(temp.getTime() + 2 * (1000 * 60 * 60 * 24))));
        button5.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date(temp.getTime() + 3 * (1000 * 60 * 60 * 24))));

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
        getMenuInflater().inflate(R.menu.week_view_nav, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void dayView(View v){
        String day = "0";
        switch (v.getId()) {
            //today
            case R.id.button2:
                day = "0";
                break;
            case R.id.button3:
                day = "1";
                break;
            case R.id.button4:
                day = "2";
                break;
            case R.id.button5:
                day = "3";
                break;
        }
        Intent intent = new Intent(this, DayViewNav.class);
        intent.putExtra("day", day);
        startActivity(intent);
    }

    public void addView(View v){
        Intent intent = new Intent(this, AddView.class);
        startActivity(intent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_today) {
            Intent intent = new Intent(this, DayViewNav.class);
            startActivity(intent);
        } else if (id == R.id.nav_dayview) {
            Intent intent = new Intent(this, DayViewNav.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

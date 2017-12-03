package com.stats.vicky.cabtesting;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import static com.stats.vicky.cabtesting.R.id.drawerLayoutSt;

public class StatusActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mtoggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
         Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawerLayoutSt);
        mtoggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View nav_header = LayoutInflater.from(this).inflate(R.layout.header_nav, null);
        Bundle bundle = getIntent().getExtras();
        String stuff = bundle.getString("username");
        String time = bundle.getString("time");
        /*String source = bundle.getString("source");
        String dest = bundle.getString("dest");
        String time = bundle.getString("time");*/
       ((TextView) nav_header.findViewById(R.id.textView)).setText("Welcome : "+stuff);
        ((TextView) this.findViewById(R.id.textView2)).setText(time);
        ((TextView) this.findViewById(R.id.textView3)).setText("Cab Request Submitted");

        navigationView.addHeaderView(nav_header);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void track(View view) {
        Intent i =new Intent(StatusActivity.this,FinalActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        /*int id = item.getItemId();
        if (id == R.id.track_status) {
            Intent i=new Intent(EmpDetails.this,StatusActivity.class);
            startActivity(i);
            return  true;
        }
        DrawerLayout drawer =  findViewById(R.id.drawerlayout);

        drawer.closeDrawer(GravityCompat.START);*/

        return true;

    }
}

package com.stats.vicky.cabtesting;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import static com.stats.vicky.cabtesting.R.id.drawerLayoutSt;

public class StatusActivity extends AppCompatActivity {

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
        Intent i =new Intent();
        Bundle bundle = getIntent().getExtras();
        //String stuff = bundle.getString("username");
        String source = bundle.getString("source");
        String dest = bundle.getString("dest");
        String time = bundle.getString("time");
       // ((TextView) nav_header.findViewById(R.id.textView)).setText("Welcome : "+stuff);


        navigationView.addHeaderView(nav_header);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }

    public void track(View view) {
        Intent i =new Intent(StatusActivity.this,FinalActivity.class);
        startActivity(i);
    }
}

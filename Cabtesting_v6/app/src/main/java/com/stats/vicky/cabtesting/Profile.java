package com.stats.vicky.cabtesting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mtoggle;
    static String stuff;
    static Context Cont;
    static String time_dtl;
    static Bundle bundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.profileDrawerLayout);
        mtoggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
         bundle = getIntent().getExtras();
        stuff = bundle.getString("username");
        View nav_header = LayoutInflater.from(this).inflate(R.layout.header_nav, null);
        ((TextView) nav_header.findViewById(R.id.header)).setText(stuff);
        navigationView.addHeaderView(nav_header);
        navigationView.setNavigationItemSelectedListener(this);
        this.Cont=this;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Navigationmove nmv=new Navigationmove();
        nmv.move(bundle,id,this);
        return true;
    }
}
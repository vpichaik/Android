package com.stats.vicky.cabtesting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;


public class EmpDetails extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mtoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_details);
            Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        mDrawerLayout = findViewById(R.id.drawerlayout);
        mtoggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View nav_header = LayoutInflater.from(this).inflate(R.layout.header_nav, null);
        Bundle bundle =i.getExtras();
        String stuff = bundle.getString("username");
        ((TextView) nav_header.findViewById(R.id.textView)).setText("Welcome : "+stuff);
        navigationView.addHeaderView(nav_header);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mtoggle.onOptionsItemSelected(item))
        {
        return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void submit(View view)  {
        Intent i1=new Intent(EmpDetails.this,StatusActivity.class);

        EditText source=findViewById(R.id.source);
        EditText dest=findViewById(R.id.destination);
        TimePicker time=findViewById(R.id.time);

        Bundle bundle = new Bundle();
        Bundle bundle1= getIntent().getExtras();
        String stuff = bundle1.getString("username");
        bundle.putString("dest",dest.getText().toString());
        bundle.putString("source",source.getText().toString());
        bundle.putString("time",time.toString());
        bundle.putString("username",stuff);
        i1.putExtras(bundle);
        startActivity(i1);


    }
}


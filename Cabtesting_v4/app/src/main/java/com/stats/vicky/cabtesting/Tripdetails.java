package com.stats.vicky.cabtesting;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.widget.DrawerLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tripdetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mtoggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripdetails);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawerLayoutFinal);
        mtoggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        Bundle bundle=getIntent().getExtras();
        Log.i("val1",bundle.getString("username"));
        if(!bundle.getString("jsonvalue").isEmpty()) {
            Log.i("val2",bundle.getString("jsonvalue"));
            String jsonvalue = bundle.getString("jsonvalue");
          /*adding Header to navigation bar*/
            NavigationView navigationView = findViewById(R.id.nav_view);
            View nav_header = LayoutInflater.from(this).inflate(R.layout.header_nav, null);
            ((TextView) nav_header.findViewById(R.id.header)).setText("Welcome : " + bundle.getString("username"));
            navigationView.addHeaderView(nav_header);
            navigationView.setNavigationItemSelectedListener(this);
        /*End of  Header to navigation bar*/

            try {
                JSONArray array = new JSONArray(jsonvalue);
                JSONObject object = new JSONObject(array.get(0).toString());
                String From = object.getString("From");
                String DRIVER = object.getString("chaufferName");
                String To = object.getString("To");
                TextView from = new TextView(this);
                TextView to = new TextView(this);
                TextView driver = new TextView(this);
                from = findViewById(R.id.textView3);
                to = findViewById(R.id.textView4);
                driver = findViewById(R.id.textView5);
                from.setText("Starting Place: " + From);
                to.setText("Destination: " + To);
                driver.setText("Driver Name: " + DRIVER);
                JSONArray array1 = new JSONArray(object.getString("co_passenger"));
                Log.i("val3", array1.toString());
                ArrayList<String> al = new ArrayList<String>();
                for (int i = 0; i < array1.length(); i++) {
                    JSONObject Jo = new JSONObject(array1.get(i).toString());
                    al.add(Jo.getString("Grp_userName"));
                }

                int len = al.size();
                int i = 0;
                int j = 0;
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout = findViewById(R.id.list_value);
                while (i < len) {
                    j = i + 1;
                    TextView textView = new TextView(this);
                    textView.setText("Passenger " + j + " " + al.get(i).toString());
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    textView.setId(i);

                    textView.setTextSize(20);
                    textView.setTextColor(getResources().getColor(R.color.colorBlue));
                    linearLayout.addView(textView);
                    i++;

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Intent i =new Intent(this,FinalActivity.class);
            this.startActivity(i);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
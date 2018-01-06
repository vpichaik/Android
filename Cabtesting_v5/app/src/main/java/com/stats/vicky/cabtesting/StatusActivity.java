package com.stats.vicky.cabtesting;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;


public class StatusActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mtoggle;
    static String stuff;
    static String time;
    static String Api_dtl;
    static SwipeRefreshLayout mSwipeRefreshLayout;
    static LinearLayout tripbook;
    static LinearLayout tripconfirm;
    static TextView statusheader;
    static TextView time1;
    static TextView time2;
    static TextView statusbook;
    static TextView cabconfirm;
    static Context Cont;
    static TextView status;
    static LinearLayout triprequest;
    static TextView time3;
    static TextView cabavail;
    static Button tripdetails;
    static Button reached;
    static Bundle bundle;
    static Button cancel;
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
        mSwipeRefreshLayout=findViewById(R.id.refreshlayout);
        tripconfirm=findViewById(R.id.tripconfirm);
        tripbook=findViewById(R.id.tripbook);
        time2=findViewById(R.id.time2);
        time3=findViewById(R.id.time3);
        tripdetails=findViewById(R.id.tripdetails);
        reached=findViewById(R.id.reached);
        triprequest=findViewById(R.id.triprequest);
        cancel =findViewById(R.id.cancel);
        status=findViewById(R.id.status);
        cabavail=findViewById(R.id.cabavail);
        statusheader=findViewById(R.id.statusheader);
        cabconfirm=findViewById(R.id.cabconfirm);
        time1=findViewById(R.id.time1);
        statusbook=findViewById(R.id.statusbook);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View nav_header = LayoutInflater.from(this).inflate(R.layout.header_nav, null);
        bundle = getIntent().getExtras();
         stuff = bundle.getString("username");
         time = bundle.getString("time");
        StatusActivity sts=new StatusActivity();
        sts.fetchdata("trip");
        ((TextView) nav_header.findViewById(R.id.header)).setText(stuff);
        navigationView.addHeaderView(nav_header);
        navigationView.setNavigationItemSelectedListener(this);
        this.Cont=this;




    }

    public void track(View view) {
        Intent i =new Intent(StatusActivity.this,FinalActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Navigationmove nmv=new Navigationmove();
        nmv.move(bundle,id,Cont);

        return true;

    }

    public void afterRefresh(String res) {
        String[] result_data = res.split("\\^");
        String Jsonvalue = result_data[1];
        String APi = result_data[0];
        if (APi.equals("trip")) {
            if (Jsonvalue.equals("1")) {
                this.mSwipeRefreshLayout.setRefreshing(false);
                this.statusheader.setText("No Cab Booked For Today");
            } else if (Jsonvalue.equals("1")) {
                this.mSwipeRefreshLayout.setRefreshing(false);
                this.statusheader.setText("Issue in Connecting to Database");
            } else {

                try {

                    this.mSwipeRefreshLayout.setRefreshing(false);
                    JSONArray arr = new JSONArray(Jsonvalue);
                    JSONObject obj = new JSONObject(arr.get(0).toString());
                    String[] result1 = obj.get("UserBookedOn").toString().split("\\s");
                    String TripAcceptedFlag = obj.get("TripAcceptedFlag").toString();
                    Log.i("val", result1[1]);
                    this.statusheader.setText("Status of Your Cab Request");
                    this.time1.setText(result1[1]);
                    this.statusbook.setText("Cab Request Submitted");
                    this.status.setVisibility(View.VISIBLE);
                    this.tripbook.setVisibility(View.VISIBLE);
                    if (obj.get("TripConfirmTime").toString() != "null") {
                        String[] result = obj.get("TripConfirmTime").toString().split("\\s");
                        this.time2.setText(result[1]);
                        this.cabconfirm.setText("Cab Request Approved");
                        Log.i("val", result[1]);
                        this.tripconfirm.setVisibility(View.VISIBLE);
                        if (obj.get("TripReqDateTime").toString() != "null") {
                            String[] result2 = obj.get("TripReqDateTime").toString().split("\\s");
                            this.time3.setText(result2[1]);
                            this.cabavail.setText("Cab Availing Time");
                            this.triprequest.setVisibility(View.VISIBLE);
                            this.tripdetails.setVisibility(View.VISIBLE);
                            this.reached.setVisibility(View.VISIBLE);
                            this.cancel.setVisibility(View.VISIBLE);

                        }
                    }

                } catch (Exception e) {
                    Log.e("val", e.getMessage());
                }
            }
        }
        else  if (APi.equals("sts_upd"))
        {
            Log.e("val", stuff);
            Bundle bundle = new Bundle();
            bundle.putString("username",stuff);
            Intent i =new Intent(Cont,FinalActivity.class);
            i.putExtras(bundle);
            Cont.startActivity(i);

        }
        else if (APi.equals("cncl_dtls"))
        {
            Log.e("val", Jsonvalue);
            Bundle bundle = new Bundle();
            bundle.putString("username",stuff);
            bundle.putString("jsonvalue",Jsonvalue);
            Intent i =new Intent(Cont,CancelActivity.class);
            i.putExtras(bundle);
            Cont.startActivity(i);
        }

        else
        {
            Log.e("val", Jsonvalue);
            Bundle bundle = new Bundle();
            bundle.putString("username",stuff);
            bundle.putString("jsonvalue",Jsonvalue);
            Intent i =new Intent(Cont,Tripdetails.class);
            i.putExtras(bundle);
            Cont.startActivity(i);
        }
    }
    @Override
    public void onRefresh() {
        StatusActivity sts=new StatusActivity();
        sts.fetchdata("trip");

    }
    public void fetchdata(String Api_dtl)
    {
        APIConnecting AP=new APIConnecting();
        AP.execute(Api_dtl,stuff);
    }

    public void reached(View view) {
        StatusActivity sts=new StatusActivity();
        sts.fetchdata("sts_upd");

    }

    public void tripstatus(View view) {
        StatusActivity sts=new StatusActivity();
        sts.fetchdata("trip_dtls");
    }

    public void CancelCab(View view) {
        StatusActivity sts=new StatusActivity();
        sts.fetchdata("cncl_dtls");
    }
}

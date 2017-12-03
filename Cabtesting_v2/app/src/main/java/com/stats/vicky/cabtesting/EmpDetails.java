package com.stats.vicky.cabtesting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class EmpDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mtoggle;
    static String stuff;
    static Context Cont;
static String time_dtl;

private static final String[]
        places=new String[]{"Adambakkam","Adyar","Alandur ","Alapakkam","Alwarpet","Alwarthirunagar",
        "Ambattur","Aminjikarai","Anna Nagar","Annanur","Arumbakkam","Ashok Nagar","Avadi","Ayanavaram","Besant Nagar",
        "Basin Bridge","Chepauk","Chetput","Chintadripet","Chitlapakkam","Choolai","Choolaimedu","Chrompet","Egmore",
        "Ekkaduthangal","Ennore","Foreshore Estate","Fort St. George","George Town","Gopalapuram","Government Estate","Guindy",
        "IIT Madras","Injambakkam","ICF","Iyyapanthangal","Jafferkhanpet","Karapakkam","Kattivakkam","Kazhipattur","K.K. Nagar",
        "Keelkattalai","Kelambakkam","Kilpauk","Kodambakkam","Kodungaiyur","Kolathur","Korattur","Korukkupet","Kottivakkam",
        "Kotturpuram","Kottur","Kovalam","Kovilambakkam","Koyambedu","Kundrathur","Madhavaram","Madhavaram Milk Colony","Madipakkam",
        "Madambakkam","Maduravoyal","Manali","Manali New Town","Manapakkam","Mandaveli","Mangadu","Mannady","Mathur","Medavakkam",
        "Meenambakkam","MGR Nagar","Minjur","Mogappair","MKB Nagar","Mount Road","Moolakadai","Moulivakkam","Mugalivakkam","Mudichur",
        "Mylapore","Nandanam","Nanganallur","Navalur","Neelankarai","Nemilichery","Nesapakkam","Nolambur","Noombal","Nungambakkam","Otteri",
        "Padi","Pakkam","Palavakkam","Pallavaram","Pallikaranai","Pammal","Park Town","Parry's Corner","Pattabiram","Pattaravakkam",
        "Pazhavanthangal","Peerkankaranai","Perambur","Peravallur","Perumbakkam","Perungalathur","Perungudi","Pozhichalur","Poonamallee",
        "Porur","Pudupet","Purasaiwalkam","Puthagaram","Puzhal","Puzhuthivakkam","Raj Bhavan","Ramavaram","Red Hills","Royapettah","Royapuram",
        "Saidapet","Saligramam","Santhome","Sembakkam","Selaiyur","Shenoy Nagar","Sholavaram","Sholinganallur","Sithalapakkam","Sowcarpet",
        "St.Thomas Mount","Tambaram","Teynampet","Tharamani","T. Nagar","Thirumangalam","Thirumullaivoyal","Thiruneermalai","Thiruninravur",
        "Thiruvanmiyur","Tiruverkadu","Thiruvotriyur","Tirusulam","Tiruvallikeni","Tiruvallur","Tondiarpet","United India Colony","Vandalur","Vadapalani","Valasaravakkam","Vallalar Nagar","Vanagaram","Velachery","Villivakkam","Virugambakkam","Vyasarpadi","Washermanpet","West Mambalam"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawerlayout);
        mtoggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        final AutoCompleteTextView actv1=findViewById(R.id.source);
        final AutoCompleteTextView actv2=findViewById(R.id.destination);
        ImageView imgv1=findViewById(R.id.img1);
        ImageView imgv2=findViewById(R.id.img2);
        actv1.setThreshold(2);
        actv2.setThreshold(2);
        ArrayAdapter<String>adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,places);
        actv1.setAdapter(adapter1);
        ArrayAdapter<String>adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,places);
        actv2.setAdapter(adapter2);
        imgv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actv1.showDropDown();
            }
        });
        imgv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actv2.showDropDown();
            }
        });


        /*adding Header to navigation bar*/
        NavigationView navigationView = findViewById(R.id.nav_view);
        Bundle bundle = getIntent().getExtras();
        stuff = bundle.getString("username");
        View nav_header = LayoutInflater.from(this).inflate(R.layout.header_nav, null);
        ((TextView) nav_header.findViewById(R.id.textView)).setText("Welcome : "+stuff);
        navigationView.addHeaderView(nav_header);
        navigationView.setNavigationItemSelectedListener(this);
        /*End of  Header to navigation bar*/

            }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void submit(View view)  {
        EditText source=findViewById(R.id.source);
        EditText dest=findViewById(R.id.destination);
        TimePicker time=findViewById(R.id.time);
        String source_add=source.getText().toString();
        String dest_add=dest.getText().toString();
        String min;
        if(time.getCurrentMinute()>10)
        {min= time.getCurrentMinute().toString();
        }
        else
        {min= "0"+time.getCurrentMinute();
        }

        time_dtl=time.getCurrentHour()+":"+min+":00";
        String Api_dtl="insert";
        Bundle bundle = new Bundle();
        bundle.putString("dest",dest_add);
        bundle.putString("source",source_add);
        bundle.putString("time",time_dtl);
        bundle.putString("username",stuff);
        this.Cont=this;
        APIConnecting AP=new APIConnecting();
        AP.execute(Api_dtl,source_add,dest_add,time_dtl,stuff);
        /*Intent i1=new Intent(EmpDetails.this,StatusActivity.class);
        i1.putExtras(bundle);
        EmpDetails.this.startActivity(i1);*/
    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        bundle.putString("username",stuff);
        if (id == R.id.track_status) {
            Log.i("val",stuff);
            Intent i=new Intent(Cont,StatusActivity.class);
            i.putExtras(bundle);
            startActivity(i);
        }
        DrawerLayout drawer =  findViewById(R.id.drawerlayout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
    public void redirect(String result)
    {

        // Toast.makeText(getApplicationContext(), "success",Toast.LENGTH_LONG).show();

        Bundle bundle = new Bundle();
        bundle.putString("username",stuff);
        bundle.putString("time",time_dtl);
        Intent i=new Intent(Cont,StatusActivity.class);
        i.putExtras(bundle);
        Cont.startActivity(i);

    }
}


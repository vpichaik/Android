package com.stats.vicky.cabtesting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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


public class EmpDetails extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mtoggle;

private static final String[] places=new String[]{"Adambakkam","Adyar","Alandur ","Alapakkam","Alwarpet","Alwarthirunagar","Ambattur","Aminjikarai","Anna Nagar","Annanur","Arumbakkam","Ashok Nagar","Avadi","Ayanavaram","Besant Nagar","Basin Bridge","Chepauk","Chetput","Chintadripet","Chitlapakkam","Choolai","Choolaimedu","Chrompet","Egmore","Ekkaduthangal","Ennore","Foreshore Estate","Fort St. George","George Town","Gopalapuram","Government Estate","Guindy","IIT Madras","Injambakkam","ICF","Iyyapanthangal","Jafferkhanpet","Karapakkam","Kattivakkam","Kazhipattur","K.K. Nagar","Keelkattalai","Kelambakkam","Kilpauk","Kodambakkam","Kodungaiyur","Kolathur","Korattur","Korukkupet","Kottivakkam","Kotturpuram","Kottur","Kovalam","Kovilambakkam","Koyambedu","Kundrathur","Madhavaram","Madhavaram Milk Colony","Madipakkam","Madambakkam","Maduravoyal","Manali","Manali New Town","Manapakkam","Mandaveli","Mangadu","Mannady","Mathur","Medavakkam","Meenambakkam","MGR Nagar","Minjur","Mogappair","MKB Nagar","Mount Road","Moolakadai","Moulivakkam","Mugalivakkam","Mudichur","Mylapore","Nandanam","Nanganallur","Navalur","Neelankarai","Nemilichery","Nesapakkam","Nolambur","Noombal","Nungambakkam","Otteri","Padi","Pakkam","Palavakkam","Pallavaram","Pallikaranai","Pammal","Park Town","Parry's Corner","Pattabiram","Pattaravakkam","Pazhavanthangal","Peerkankaranai","Perambur","Peravallur","Perumbakkam","Perungalathur","Perungudi","Pozhichalur","Poonamallee","Porur","Pudupet","Purasaiwalkam","Puthagaram","Puzhal","Puzhuthivakkam","Raj Bhavan","Ramavaram","Red Hills","Royapettah","Royapuram","Saidapet","Saligramam","Santhome","Sembakkam","Selaiyur","Shenoy Nagar","Sholavaram","Sholinganallur","Sithalapakkam","Sowcarpet","St.Thomas Mount","Tambaram","Teynampet","Tharamani","T. Nagar","Thirumangalam","Thirumullaivoyal","Thiruneermalai","Thiruninravur","Thiruvanmiyur","Tiruverkadu","Thiruvotriyur","Tirusulam","Tiruvallikeni","Tiruvallur","Tondiarpet","United India Colony","Vandalur","Vadapalani","Valasaravakkam","Vallalar Nagar","Vanagaram","Velachery","Villivakkam","Virugambakkam","Vyasarpadi","Washermanpet","West Mambalam"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = new Intent();
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


        NavigationView navigationView = findViewById(R.id.nav_view);


        View nav_header = LayoutInflater.from(this).inflate(R.layout.header_nav, null);
        Bundle bundle = getIntent().getExtras();
        String stuff = bundle.getString("username");
        ((TextView) nav_header.findViewById(R.id.textView)).setText("Welcome : "+stuff);
        navigationView.addHeaderView(nav_header);
        navigationView.setNavigationItemSelectedListener(this);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mtoggle.onOptionsItemSelected(item))

        {

            return true;
        }

        return super.onOptionsItemSelected(item);


    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.track_status) {

            Intent i=new Intent(EmpDetails.this,StatusActivity.class);
            startActivity(i);

            return  true;
        }

        DrawerLayout drawer =  findViewById(R.id.drawerlayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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


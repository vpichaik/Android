package com.stats.vicky.cabtesting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by allin on 11-12-2017.
 */

public class Navigationmove {

    static Context Cont;
    static String username;

    public void move(Bundle bundle, int id, Context Cont) {
         Log.i("val","test");
        username=bundle.getString("username");
        this.Cont=Cont;
        Log.i("val","size: "+bundle.size());

        if (id == R.id.track_status) {
            Log.i("val","test1");
            Log.i("val", bundle.getString("username"));
            Intent i = new Intent(Cont, StatusActivity.class);
            i.putExtras(bundle);
            Cont.startActivity(i);
        }
        //changed code this below only--satheesh
        if (id == R.id.Book_Cab) {
            Log.i("val", bundle.getString("username"));
            Intent i = new Intent(Cont, EmpDetails.class);
            i.putExtras(bundle);
            Cont.startActivity(i);
        }
        // code edited 10dec17

        if (id == R.id.Profile_Update) {
            Log.i("val", bundle.getString("username"));
            Intent i = new Intent(Cont, Profile.class);
            i.putExtras(bundle);
            Cont.startActivity(i);
        }
        if (id == R.id.Trip_details) {

            Navigationmove Nm=new Navigationmove();

            Nm.beforeRefresh(username);
            Log.i("valp",Cont.getPackageName().getClass().toString());

           /* Intent i = new Intent(Cont, Tripdetails.class);
            bundle.putString("jsonvalue",Jsonvalue);
            i.putExtras(bundle);
            Cont.startActivity(i);*/


        }
        if (id == R.id.Cancel_cab) {
            Log.i("val", bundle.getString("username"));
            Intent i = new Intent(Cont, CancelActivity.class);
            i.putExtras(bundle);
            Cont.startActivity(i);
        }


    }

    public void beforeRefresh(String username) {
        APIConnecting Ap = new APIConnecting();
        Ap.execute("trip_dtls_n", username);
    }
    public void afterRefresh(String res) {

        String[] result_data = res.split("\\^");
        String Jsonvalues = result_data[1];
        String APi = result_data[0];
        Log.i("valres", Jsonvalues);
        Log.i("valp1",Cont.getPackageName());
        Intent i = new Intent(this.Cont, Tripdetails.class);
        Bundle bundle1=new Bundle();
        bundle1.putString("username",username);
        bundle1.putString("jsonvalue",Jsonvalues);
        i.putExtras(bundle1);
        this.Cont.startActivity(i);

    }
}

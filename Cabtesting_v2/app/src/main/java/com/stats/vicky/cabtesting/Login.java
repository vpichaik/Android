package com.stats.vicky.cabtesting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Login extends AppCompatActivity {
     EditText user;
     EditText pass;
     static String username;
     static String Password;
     static Context Cont;
     ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void signIn(View view) {
        user=findViewById(R.id.username);
        pass=findViewById(R.id.passwd);
        username = user.getText().toString();
        Password = pass.getText().toString();
        this.Cont=this;
        APIConnecting AP=new APIConnecting();
        AP.execute("LOGIN",username,Password);

        }



    public void progress()
    {
        progressDialog = ProgressDialog.show(Cont,
                "ProgressDialog",
                "Loading...");
    }



        public void redirect(String result)
        {

                // Toast.makeText(getApplicationContext(), "success",Toast.LENGTH_LONG).show();

                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                Intent i=new Intent(Cont,EmpDetails.class);
                i.putExtras(bundle);
                Cont.startActivity(i);

    }
}





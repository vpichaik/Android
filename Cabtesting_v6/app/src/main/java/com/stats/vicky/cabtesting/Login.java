package com.stats.vicky.cabtesting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


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
        if(username.isEmpty() || Password.isEmpty())
        {
            if(username.isEmpty())
            {
                Toast.makeText(this,"Username is Empty",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,"Password is Empty",Toast.LENGTH_SHORT).show();
            }
        }
        else {

            this.Cont = this;
            APIConnecting AP = new APIConnecting();
            AP.execute("LOGIN", username, Password);
        }
        }



    public void progress()
    {
        progressDialog = ProgressDialog.show(Cont,
                "ProgressDialog",
                "Loading...");
    }



        public void redirect(String result)
        {

                   Bundle bundle = new Bundle();
                   bundle.putString("username", username);
                   Intent i = new Intent(Cont, EmpDetails.class);
                   i.putExtras(bundle);
                   Cont.startActivity(i);


    }
    public void toas()
    {


        Toast.makeText(Cont,"Incorrect Username and Password",Toast.LENGTH_SHORT).show();
        if(progressDialog.isShowing()==true)
        {
            progressDialog.dismiss();
        }


    }
}





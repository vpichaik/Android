package com.stats.vicky.cabtesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText user;
    EditText pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=findViewById(R.id.username);
        pass=findViewById(R.id.passwd);
    }

    public void signIn(View view) {
       String username = user.getText().toString();
       String Password = pass.getText().toString();
       if(username.equals(Password))
       {
           Log.i(Login.this.getClass().getName(),"Successfully Logged in ");
           Intent i=new Intent(Login.this,EmpDetails.class);
           startActivity(i);
       }
       else
       {
           Log.i(Login.this.getClass().getName(),"Failed Logged in ");
       }

        }
}


package com.stats.vicky.cabtesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
public class splashActivity extends AppCompatActivity {


        Handler handler;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);


            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(splashActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }, 500);

        }}
package com.example.menu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        background.start();
    }

    Thread background = new Thread() {
        public void run() {
            try {

                sleep(1200);
                Intent i=new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
                finish();
            } catch (Exception e) {

            }
        }

    };
}
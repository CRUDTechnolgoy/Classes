package com.crud.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.crud.myfirstapp.data.DataHelper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new PrefsHelper(SplashActivity.this);
        new DataHelper(SplashActivity.this);
        new Handler().postDelayed(new Runnable() {

            // Using handler with postDelayed called runnable run method

            @Override
            public void run() {
                Intent i;
                if (PrefsHelper.getPrefsHelper().getPref(PrefsHelper.IS_LOGIN, false)) {
                    i = new Intent(SplashActivity.this, Dashboard.class);
                } else {
                    i = new Intent(SplashActivity.this, MainActivity.class);
                }
                startActivity(i);


                // close this activity
                finish();
            }
        }, 5 * 1000); // wait for 5 seconds
    }
}

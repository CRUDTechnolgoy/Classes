package com.crud.myfirstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView userName, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (TextView) findViewById(R.id.textViewUserName);
        password = (TextView) findViewById(R.id.textViewPassword);
        login = (Button) findViewById(R.id.buttonLogin);
    }
}

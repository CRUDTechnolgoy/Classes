package com.crud.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText userName, password;
    TextView buttonSignUp;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity", "onCreate");
        userName = (EditText) findViewById(R.id.textViewUserName);
        password = (EditText) findViewById(R.id.textViewPassword);
        login = (Button) findViewById(R.id.buttonLogin);
        buttonSignUp = (TextView) findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("MainActivity", "I have clicked on buttonSignUp");
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("MainActivity", "I have clicked on login");
                String vUserName = userName.getText().toString();
                String vPassword = password.getText().toString();
                if (vUserName.equals("demo") && vPassword.equals("demo")) {
                    Log.e("MainActivity", "Login successfully");
                    Intent intent = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(intent);
                } else {
                    Log.e("MainActivity", "Invalid details");
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("MainActivity", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity", "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity", "onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity", "onStart");
    }
}

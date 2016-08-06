package com.crud.myfirstapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crud.myfirstapp.data.DataHelper;

public class MainActivity extends AppCompatActivity {

    EditText userName, password;
    TextView buttonSignUp;
    Button login;
    Spinner spinnerCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity", "onCreate");
        userName = (EditText) findViewById(R.id.textViewUserName);
        password = (EditText) findViewById(R.id.textViewPassword);
        login = (Button) findViewById(R.id.buttonLogin);
        buttonSignUp = (TextView) findViewById(R.id.buttonSignUp);
        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
        String vCitys[] = getResources().getStringArray(R.array.city);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, vCitys);

        //setting adapter to spinner
        spinnerCity.setAdapter(adapter);

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
                //get the value from edittext
                String vUserName = userName.getText().toString();
                String vPassword = password.getText().toString();
                //get the value from spinner
                String vCity = spinnerCity.getSelectedItem().toString();
                //Printing the value of vCity from spinner
                Log.e("MainActity", vCity);
                if (vUserName.length() > 0) {
                    if (vPassword.length() > 0) {
                        if (DataHelper.getInstance().checkUser(vUserName, vPassword) > 0) {
                            Log.e("MainActivity", "Login successfully");
                            PrefsHelper.getPrefsHelper().savePref(PrefsHelper.IS_LOGIN, true);
                            Intent intent = new Intent(MainActivity.this, Dashboard.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Invlaid Details", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        password.setError("Password is required");
                        password.requestFocus();
                    }
                } else {
                    userName.setError("Username is required");
                    password.requestFocus();
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

    //handling backprassed
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Confermation")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        return;
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}

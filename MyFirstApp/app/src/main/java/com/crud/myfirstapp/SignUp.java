package com.crud.myfirstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crud.myfirstapp.data.DataHelper;
import com.crud.myfirstapp.data.Student;

public class SignUp extends AppCompatActivity {

    private android.widget.EditText textViewUserName;
    private android.widget.EditText textViewPassword;
    private android.widget.Spinner spinnerCity;
    private android.widget.Button buttonSignUp;
    private android.widget.TextView buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.buttonLogin = (TextView) findViewById(R.id.buttonLogin);
        this.buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        this.spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
        this.textViewPassword = (EditText) findViewById(R.id.textViewPassword);
        this.textViewUserName = (EditText) findViewById(R.id.textViewUserName);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vUname, vPwd, vCity;
                vUname = textViewUserName.getText().toString();
                vPwd = textViewPassword.getText().toString();
                vCity = spinnerCity.getSelectedItem().toString();
                Student s=new Student();
                s.setName(vUname);
                s.setPassword(vPwd);
                s.setCity(vCity);
                if (DataHelper.getInstance().insert(s) > 0) {
                    Toast.makeText(SignUp.this, "Data is save", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SignUp.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

package com.cs360.ryansantos.localcoffeeshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private EditText userEmail,userPassword, userFullName;
    private Button createAccount;
    private TextView login;
    private String username, sPassword, sFullName;

    public static final String PREFERENCE = "preference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        createAccount = (Button) findViewById(R.id.createAccount_btn);
        login = (TextView) findViewById(R.id.login_link);

        userFullName = (EditText) findViewById(R.id.fullName);
        userEmail = (EditText) findViewById(R.id.email);
        userPassword = (EditText) findViewById(R.id.password);

    }

    // Implements functionality for creating account and logging in
    public void onClick(View v) {

        if(v.getId() == R.id.createAccount_btn){
            if(validUserData()){
                SharedPreferences mSharedPreference = getSharedPreferences(PREFERENCE,Context.MODE_PRIVATE);
                sFullName = userFullName.getText().toString().trim();
                username = userEmail.getText().toString().trim();
                sPassword = userPassword.getText().toString().trim();
                SharedPreferences.Editor mEditor = mSharedPreference.edit();
                mEditor.putString("fullName", sFullName);
                mEditor.putString("name", username);
                mEditor.putString("pass", sPassword);
                mEditor.apply();
                finish();

                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
        else if (v.getId() == R.id.login_link) {
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    // Checks if any fields are empty
    private boolean validUserData() {
        sFullName = userFullName.getText().toString().trim();
        username = userEmail.getText().toString().trim();
        sPassword = userPassword.getText().toString().trim();

        if(sFullName.isEmpty()){
            userFullName.setError( "Full name is required!" );
        }
        else if(username.isEmpty()){
            userEmail.setError( "Email is required!" );
        }
        else if(sPassword.isEmpty()){
            userPassword.setError( "Password is required!" );
        }
        else {
            return true;
        }
        return false;
    }

}

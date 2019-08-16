package com.cs360.ryansantos.localcoffeeshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText userEmail, userPassword;
    private SharedPreferences mSharedPreferences;

    private String sUsername, sPassword;

    public static final String PREFERENCE = "preference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userEmail = (EditText) findViewById(R.id.email);
        userPassword = (EditText) findViewById(R.id.password);

        sUsername = userEmail.getText().toString().trim();
        sPassword = userPassword.getText().toString().trim();

    }

    // Implements button functionality for signing up, logging in, and forgot password
    public void onClick(View v) {

        if (v.getId() == R.id.signUp_button) {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.login_button) {
            mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
            if (validUserData()) {
                //if (mSharedPreferences.contains(PREF_NAME) && mSharedPreferences.contains(PREF_NAME)) {
                    SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                //}
            } else {
                Toast.makeText(getApplicationContext(), "Invalid username or password!", Toast.LENGTH_SHORT).show();
            }
        }
        else if (v.getId() == R.id.forgotPassword_button){
            Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        }

    }

    // Validates that the username and password matches what is stored in SharedPreferences
    private boolean validUserData() {

        if( isFieldEmpty() ){
            if( sUsername.equals(mSharedPreferences.getString("name","DEFAULT")) ) {
                if ( sPassword.equals(mSharedPreferences.getString("pass","DEFAULT"))) {
                    return true;
                }
            }
        }
        return false;
    }

    // Checks if the user did not enter any information in a required field
    private boolean isFieldEmpty(){
        sUsername = userEmail.getText().toString().trim();
        sPassword = userPassword.getText().toString().trim();

        if(sUsername.isEmpty()){
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

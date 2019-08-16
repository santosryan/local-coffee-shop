package com.cs360.ryansantos.localcoffeeshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    Button changePassword, cancel;
    EditText userFullName, userEmail, userPassword, newPassword;
    String sFullName, sUsername, sPassword, sNewPassword;

    private SharedPreferences mSharedPreferences;
    public static final String PREFERENCE = "preference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        changePassword = (Button) findViewById(R.id.changePassword_button);
        cancel = (Button) findViewById(R.id.cancel_button);

        userFullName = (EditText) findViewById(R.id.fullName_text);
        userEmail = (EditText) findViewById(R.id.username_text);
        userPassword = (EditText) findViewById(R.id.currentPassword_text);
        newPassword = (EditText) findViewById(R.id.newPassword_text);

        sFullName = userFullName.getText().toString().trim();
        sUsername = userEmail.getText().toString().trim();
        sPassword = userPassword.getText().toString().trim();
        sNewPassword = newPassword.getText().toString().trim();

    }

    // Implements button functions for changing password and returning to login screen
    public void buttonPressed(View v) {
        if(v.getId() == R.id.changePassword_button){
            mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
            if(validUserData()){
                sNewPassword = newPassword.getText().toString().trim();
                SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                mEditor.putString("pass", sNewPassword);
                mEditor.apply();
                finish();

                Intent intent = new Intent(ForgotPasswordActivity.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Password change successful", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Invalid information entered", Toast.LENGTH_SHORT).show();
            }
        }
        else if (v.getId() == R.id.cancel_button) {
            Intent intent = new Intent(ForgotPasswordActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private boolean validUserData() {

        if( isAllEmpty() ){
            if(sFullName.equals(mSharedPreferences.getString("fullName", "DEFAULT"))) {
                if (sUsername.equals(mSharedPreferences.getString("name", "DEFAULT"))) {
                    if (sPassword.equals(mSharedPreferences.getString("pass", "DEFAULT"))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private boolean isAllEmpty(){
        sFullName = userFullName.getText().toString().trim();
        sUsername = userEmail.getText().toString().trim();
        sPassword = userPassword.getText().toString().trim();

        if(sFullName.isEmpty()){
            userFullName.setError( "Full name is required!" );
        }
        else if(sUsername.isEmpty()){
            userEmail.setError( "Email is required!" );
        }
        else if(sPassword.isEmpty()){
            userPassword.setError( "Current password is required!" );
        }
        else {
            return true;
        }
        return false;
    }

}

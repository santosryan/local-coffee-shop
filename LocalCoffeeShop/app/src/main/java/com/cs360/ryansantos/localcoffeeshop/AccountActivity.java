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

public class AccountActivity extends AppCompatActivity {

    Button changePassword, logout;
    EditText userFullName, userEmail, userPassword, newPassword;
    String sFullName, sUsername, sPassword, sNewPassword;

    private SharedPreferences mSharedPreferences;
    public static final String PREFERENCE = "preference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        changePassword = (Button) findViewById(R.id.newPassword_btn);
        logout = (Button) findViewById(R.id.logout_btn);

        userFullName = (EditText) findViewById(R.id.userfullName);
        userEmail = (EditText) findViewById(R.id.userEmail);
        userPassword = (EditText) findViewById(R.id.currentUserPassword_text);
        newPassword = (EditText) findViewById(R.id.newUserPassword_text);

        mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);

        sFullName = mSharedPreferences.getString("fullName", "DEFAULT");
        sUsername = mSharedPreferences.getString("name", "DEFAULT");

        userFullName.setText(sFullName);
        userEmail.setText(sUsername);

    }

    public void onClick(View v) {

        //Allows user to change password
        if(v.getId() == R.id.newPassword_btn){
            mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
            if(validPassword()){
                sNewPassword = newPassword.getText().toString().trim();
                SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                mEditor.putString("pass", sNewPassword);
                mEditor.apply();
                finish();

                Toast.makeText(getApplicationContext(), "Password change successful", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_SHORT).show();
            }
        }

        // Logs user out and brings them to login screen
        else if (v.getId() == R.id.logout_btn) {
            Intent intent = new Intent(AccountActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    // Checks if the password is valid
    private boolean validPassword() {

        if( isPasswordEmpty() ){
            if (sPassword.equals(mSharedPreferences.getString("pass", "DEFAULT"))) {
                        return true;
                }
        }
        return false;
    }


    // Checks if the user did not enter their current password
    private boolean isPasswordEmpty(){

        sPassword = userPassword.getText().toString().trim();
        if(sPassword.isEmpty()){
            userPassword.setError( "Current password is required!" );
        }
        else {
            return true;
        }
        return false;
    }
}

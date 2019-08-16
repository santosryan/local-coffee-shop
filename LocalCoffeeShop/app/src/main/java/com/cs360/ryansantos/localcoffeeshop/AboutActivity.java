package com.cs360.ryansantos.localcoffeeshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

    }


    public void onClick(View v) {

        //Creates and starts Intent to open photo applications such as Gallery and Google Photos
        if (v.getId() == R.id.contactinfo_button) {

            Intent intent = new Intent(AboutActivity.this, ContactActivity.class);
            startActivity(intent);

        }
    }
}

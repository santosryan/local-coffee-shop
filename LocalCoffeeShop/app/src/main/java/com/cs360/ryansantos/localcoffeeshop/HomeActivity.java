package com.cs360.ryansantos.localcoffeeshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class HomeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    // Implements Main Menu buttons
    public void onClick(View v) {

        if (v.getId() == R.id.menuText) {
            Intent intent = new Intent(HomeActivity.this, MenuActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.findText) {
            Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.aboutText) {
            Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.orderText) {
            Intent intent = new Intent(HomeActivity.this, TrackActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.contactText) {
            Intent intent = new Intent(HomeActivity.this, ContactActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.rateText) {
            Intent intent = new Intent(HomeActivity.this, RateActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.accountText) {
            Intent intent = new Intent(HomeActivity.this, AccountActivity.class);
            startActivity(intent);
        }
    }
}

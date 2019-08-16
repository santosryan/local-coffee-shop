package com.cs360.ryansantos.localcoffeeshop;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ContactActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }


    public void onClick(View v) {

        // When Get Directions button is clicked, opens up Google Maps to with directions from "Current location" to SNHU (SNHU)
        if (v.getId() == R.id.directions_button) {
            //Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=southern+new+hampshire+university");
            Uri gmmIntentUri = Uri.parse("https://www.google.com/maps?saddr=My+Location&daddr=2500+N+River+Road+Hooksett+NH+03106");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        }
        if (v.getId() == R.id.checktwitter_button){

            Intent intent = null;
            try {
                // get the Twitter app if possible
                context.getPackageManager().getPackageInfo("com.twitter.android", 0);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=SNHU"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } catch (Exception e) {
                // no Twitter app, revert to browser
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/SNHU"));
            }
            startActivity(intent);
        }
    }

}

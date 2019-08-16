package com.cs360.ryansantos.localcoffeeshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.twitter.sdk.android.core.identity.TwitterAuthClient;

public class ShareReviewActivity extends AppCompatActivity {

    //Twitter auth client required for custom login
    private TwitterAuthClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewshare);

    }


    public void onClick(View v) {

        //Creates and starts Intent to open photo applications such as Gallery and Google Photos
        if (v.getId() == R.id.share_button) {

            Intent intent = new Intent(ShareReviewActivity.this, TweetActivity.class);
            startActivity(intent);

        }
    }

}

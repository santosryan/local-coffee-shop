package com.cs360.ryansantos.localcoffeeshop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.io.FileNotFoundException;

public class TweetActivity extends AppCompatActivity {

    ImageView pickedImageView;
    Button twitterButton;
    RateActivity reviewFetcher = new RateActivity();

    //URI of picked/captured image
    private Uri photoURI;

    //Review text
    String reviewText = reviewFetcher.getReviewText();

    //Twitter auth client to do custom Twitter login
    private TwitterAuthClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitterpost);

        pickedImageView = findViewById(R.id.picked_image_view);

    }


     // Shares image using Twitter Native Kit composer
    private void shareUsingNativeComposer(TwitterSession session) {
        Intent intent = new ComposerActivity.Builder(TweetActivity.this)
                .session(session)//Set the TwitterSession of the User to Tweet
                .image(photoURI)//Attach an image to the Tweet
                .text("This is a test!")//Text to prefill in composer
                .hashtags("#localcoffeeshop")//Hashtags to prefill in composer
                .createIntent();//finally create intent
        startActivityForResult(intent, 1);
    }

    public void shareUsingTwitterNativeComposer(View v) {
        if (photoURI != null) {
            TwitterSession session = getTwitterSession();
            //TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();//get the active session
            if (session != null) {
                //if active session is not null start sharing image
                shareUsingNativeComposer(session);
            } else {
                //if there is no active session then ask user to authenticate
                authenticateUser();

            }
        } else {
            //if not then show dialog to pick/capture image
            Toast.makeText(this, "Please select image first to share.", Toast.LENGTH_SHORT).show();
        }
    }

    public void selectPhotoFromGallery(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 0);
    }

    // Get authenticates user session
    private TwitterSession getTwitterSession() {
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();

        //NOTE : if you want to get token and secret too use uncomment the below code
        /*TwitterAuthToken authToken = session.getAuthToken();
        String token = authToken.token;
        String secret = authToken.secret;*/

        return session;
    }

    private void authenticateUser() {
        client = new TwitterAuthClient();//init twitter auth client
        client.authorize(this, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> twitterSessionResult) {
                //if user is successfully authorized start sharing image
                Toast.makeText(TweetActivity.this, "Login successful.", Toast.LENGTH_SHORT).show();
                shareUsingNativeComposer(twitterSessionResult.data);
            }

            @Override
            public void failure(TwitterException e) {
                //if user failed to authorize then show toast
                Toast.makeText(TweetActivity.this, "Failed to authenticate by Twitter. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    //get the picked image URI
                    Uri imageUri = data.getData();
                    //set the picked image URI to created variable
                    this.photoURI = imageUri;

                    //now display picked image over ImageView
                    displayImage(imageUri);
                } else {

                    //if user cancelled or failed to pick image show toast
                    Toast.makeText(this, "Failed to pick up image from gallery.", Toast.LENGTH_SHORT).show();
                }
            case 1:
                //put this here as Twitter requires to send result back to our Class
                if (client != null)
                    client.onActivityResult(requestCode, resultCode, data);
                break;
        }

    }

    private void displayImage(Uri imageUri) {
        Picasso.with(this).load(imageUri).into(pickedImageView);
    }

}

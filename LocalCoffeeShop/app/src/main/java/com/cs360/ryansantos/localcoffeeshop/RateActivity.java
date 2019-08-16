package com.cs360.ryansantos.localcoffeeshop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class RateActivity extends AppCompatActivity {

    ImageView userPhoto;
    EditText userText;
    private Uri imageURI;
    private String reviewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        userPhoto = (ImageView)findViewById(R.id.userPhoto_view);
        userText = (EditText) findViewById(R.id.review_text);
    }


    public void onClick(View v) {

        //Creates and starts Intent to open photo applications such as Gallery and Google Photos
        if (v.getId() == R.id.addPhoto_button) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, 0);
        }

        // Submit button saves review and takes you share page which allows user to share on Instagram
        else if (v.getId() == R.id.submit_button) {
            setReviewText(userText.getText().toString());
            Intent intent = new Intent(RateActivity.this, ShareReviewActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            Bitmap bitmap;

            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                userPhoto.setImageBitmap(bitmap);
                setUri(targetUri);
            } catch (FileNotFoundException e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void setUri(Uri photoUri) {
        this.imageURI = photoUri;
    }

    public Uri getUri() {
        return this.imageURI;
    }

    public void setReviewText(String text) {
        this.reviewText = text;
    }

    public String getReviewText() {
        return this.reviewText;
    }

}

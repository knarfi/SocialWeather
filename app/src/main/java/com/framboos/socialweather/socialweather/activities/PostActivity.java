package com.framboos.socialweather.socialweather.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.framboos.socialweather.socialweather.R;

/**
 * Created by mark on 10/06/16.
 */
public class PostActivity extends Activity {

    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_view);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        imageBitmap = (Bitmap) extras.get("data");

        ImageView postImage = (ImageView) findViewById(R.id.postImage);
        postImage.setImageBitmap(imageBitmap);


        postImage.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Intent intent = new Intent(PostActivity.this, PhotoContainerActivity.class);
                startActivity(intent);
            }
        });
    }


}

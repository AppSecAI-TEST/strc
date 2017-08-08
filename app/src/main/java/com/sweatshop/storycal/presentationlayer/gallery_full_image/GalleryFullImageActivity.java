package com.sweatshop.storycal.presentationlayer.gallery_full_image;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.presentationlayer.import_photo_gallery.ImageAdapter;

public class GalleryFullImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_full_image);

        Intent i = getIntent();

        int position = i.getExtras().getInt("id");
        ImageAdapter adapter = new ImageAdapter(this);

        ImageView imageview = (ImageView)findViewById(R.id.galleryFullScreen);
        imageview.setImageResource(adapter.images1[position]);
        this.getSupportActionBar().hide();
    }
}

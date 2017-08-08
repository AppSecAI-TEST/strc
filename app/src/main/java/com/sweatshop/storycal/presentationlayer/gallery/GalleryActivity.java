package com.sweatshop.storycal.presentationlayer.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.presentationlayer.gallery_full_image.GalleryFullImageActivity;
import com.sweatshop.storycal.presentationlayer.import_photo_gallery.ImageAdapter;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GridView gridView = (GridView) findViewById(R.id.gallery);
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), GalleryFullImageActivity.class);
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        this.getSupportActionBar().hide();
    }
}

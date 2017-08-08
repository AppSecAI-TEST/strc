package com.sweatshop.storycal.presentationlayer.profile_picture.popup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.sweatshop.storycal.R;

/**
 * Created by arTeam on 04/08/2017.
 */

public class PopupListActivity extends AppCompatActivity {
    ImageView camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        camera = (ImageView)findViewById(R.id.imageCamera);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        camera.setImageBitmap(bitmap);
    }
}

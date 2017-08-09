package com.sweatshop.storycal.presentationlayer.import_profile_pic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.sweatshop.storycal.R;

public class ImportProfilePictureActivity extends AppCompatActivity {
    ImageView camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_profile_picture);

        camera = (ImageView)findViewById(R.id.imageCameraProfilePic);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        camera.setImageBitmap(bitmap);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}

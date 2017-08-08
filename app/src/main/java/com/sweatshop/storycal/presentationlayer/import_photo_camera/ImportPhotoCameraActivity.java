package com.sweatshop.storycal.presentationlayer.import_photo_camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.presentationlayer.homepage.HomepageActivity;
import com.sweatshop.storycal.presentationlayer.import_photo_gallery.ImportPhotoGalleryActivity;
import com.sweatshop.storycal.presentationlayer.main_year.MainYearActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class ImportPhotoCameraActivity extends AppCompatActivity {
    ImageView camera;
    Bitmap bitmap;
    String mCurrentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_photo_camera);
        setUpActionBar();
        camera = (ImageView)findViewById(R.id.imageCamera);
        dispatchTakePictureIntent();
    }


    private void setUpActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_main);
        View view =getSupportActionBar().getCustomView();

        ImageButton leftActionBtn= (ImageButton)view.findViewById(R.id.action_left);
        leftActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageButton rightActionBtn= (ImageButton)view.findViewById(R.id.action_right);
        rightActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void goToFeed(View view) {
        Intent intent = new Intent(ImportPhotoCameraActivity.this, HomepageActivity.class);
        startActivity(intent);
    }

    public void backToMonth(View view) {
        Intent intent = new Intent(ImportPhotoCameraActivity.this, MainYearActivity.class);
        startActivity(intent);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            camera.setImageBitmap(imageBitmap);
        }
    }

    public void toGallery(View view){
        Intent intent = new Intent(ImportPhotoCameraActivity.this, ImportPhotoGalleryActivity.class);
        startActivity(intent);
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void addPicToGallery() throws IOException {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = createImageFile();
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
}

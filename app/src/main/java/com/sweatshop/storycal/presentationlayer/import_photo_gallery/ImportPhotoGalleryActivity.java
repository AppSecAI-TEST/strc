package com.sweatshop.storycal.presentationlayer.import_photo_gallery;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.presentationlayer.edit.EditPostActivity;
import com.sweatshop.storycal.presentationlayer.home.MainActivity;
import com.sweatshop.storycal.presentationlayer.homepage.HomepageActivity;
import com.sweatshop.storycal.presentationlayer.import_photo_camera.ImportPhotoCameraActivity;
import com.sweatshop.storycal.presentationlayer.main_year.MainYearActivity;

import java.util.ArrayList;
import java.util.Random;

import static android.os.Build.VERSION_CODES.M;

public class ImportPhotoGalleryActivity extends AppCompatActivity {

    private int count;
    private Bitmap[] thumbnails;
    private boolean[] thumbnailsselection;
    private String[] arrPath;
    private ImageAdapter imageAdapter;
    private static final int REQUEST_PERMISSIONS = 100;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_photo_gallery);
        setUpActionBar();
        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(ImportPhotoGalleryActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(ImportPhotoGalleryActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(ImportPhotoGalleryActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        }else {
            addImage();
        }
    }

    private void setUpActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_import_photos);
        View view = getSupportActionBar().getCustomView();

        TextView leftActionBtn= (TextView)view.findViewById(R.id.action_random);
        leftActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageAdapter.selectRandomFive();
            }
        });
    }

    public void addPhoto() {}

    public class ImageAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        ViewHolder holder;

        public ImageAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return count;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate( R.layout.activity_gallery_imageview, null);
                holder.imageview = (ImageView) convertView.findViewById(R.id.thumbImage);
                holder.checkbox = (CheckBox) convertView.findViewById(R.id.itemCheckBox);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.checkbox.setId(position);
            holder.imageview.setId(position);
            holder.checkbox.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    CheckBox cb = (CheckBox) v;
                    int id = cb.getId();
                    if (thumbnailsselection[id]){
                        cb.setChecked(false);
                        thumbnailsselection[id] = false;
                    } else {
                        cb.setChecked(true);
                        thumbnailsselection[id] = true;
                    }
                }
            });
            holder.imageview.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    int id = v.getId();
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse("file://" + arrPath[id]), "image/*");
                    startActivity(intent);
                }
            });
            holder.imageview.setImageBitmap(thumbnails[position]);
            holder.checkbox.setChecked(thumbnailsselection[position]);
            holder.id = position;
            return convertView;
        }

        public void selectRandomFive() {
            Random r = new Random();
            int[] randomIndexes = new int[5];
            for(int i = 0; i < 5; i++) {
                int thumbnailIndex = r.nextInt(count - 1);
                randomIndexes[i] = thumbnailIndex;
            }
            for(int index = 0; index < count; index++){
                for(int i = 0; i < 5; i++){
                    if(index == randomIndexes[i]) {
                        thumbnailsselection[index] = true;
                        break;
                    }
                    else{
                        thumbnailsselection[index] = false;
                    }
                }
            }
            imageAdapter.notifyDataSetChanged();
        }
    }

    class ViewHolder {
        ImageView imageview;
        CheckBox checkbox;
        int id;
    }

    public void addImage(){
        final String[] columns = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID };
        final String orderBy = MediaStore.Images.Media._ID;
        Cursor imagecursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy);
        int image_column_index = imagecursor.getColumnIndex(MediaStore.Images.Media._ID);
        this.count = imagecursor.getCount();
        this.thumbnails = new Bitmap[this.count];
        this.arrPath = new String[this.count];
        this.thumbnailsselection = new boolean[this.count];
        for (int i = 0; i < this.count; i++) {
            imagecursor.moveToPosition(i);
            int id = imagecursor.getInt(image_column_index);
            int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
            thumbnails[i] = MediaStore.Images.Thumbnails.getThumbnail(
                    getApplicationContext().getContentResolver(), id,
                    MediaStore.Images.Thumbnails.MICRO_KIND, null);
            arrPath[i]= imagecursor.getString(dataColumnIndex);
        }
        GridView imagegrid = (GridView) findViewById(R.id.import_photo_gridView);
        imageAdapter = new ImageAdapter();
        imagegrid.setAdapter(imageAdapter);
        imagecursor.close();

        final TextView selectBtn = (TextView) findViewById(R.id.action_import);
        selectBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
//                final int len = thumbnailsselection.length;
//                int cnt = 0;
//                String selectImages = "";
//                for (int i = 0; i < len; i++) {
//                    if (thumbnailsselection[i]){
//                        cnt++;
//                        selectImages = selectImages + arrPath[i] + "|";
//                    }
//                }
//                if (cnt == 0){
//                    Toast.makeText(getApplicationContext(),
//                            "Please select at least one image",
//                            Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getApplicationContext(),
//                            "You've selected Total " + cnt + " image(s).",
//                            Toast.LENGTH_LONG).show();
//                    Log.d("SelectedImages", selectImages);
//                }
//                Intent intent = new Intent(ImportPhotoGalleryActivity.this, EditPostActivity.class);
//                startActivity(intent);
                importSelectedPictures();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                for (int i = 0; i < grantResults.length; i++) {
                    addImage();
                }
            }
        }
    }

    public void toCamera(View view){
        Intent intent = new Intent(ImportPhotoGalleryActivity.this, ImportPhotoCameraActivity.class);
        startActivity(intent);
    }

    public void importSelectedPictures() {
        ArrayList<Bitmap> selectedPictures = new ArrayList<Bitmap>();
        for(int i = 0; i < count; i++) {
            if(thumbnailsselection[i]) {
                selectedPictures.add(thumbnails[i]);
            }
        }
        Intent intent = new Intent(ImportPhotoGalleryActivity.this, EditPostActivity.class);
        intent.putExtra("selectedPictures", selectedPictures);
        startActivity(intent);
    }
}

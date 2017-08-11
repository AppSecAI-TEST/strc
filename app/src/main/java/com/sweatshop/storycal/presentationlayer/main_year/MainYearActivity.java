package com.sweatshop.storycal.presentationlayer.main_year;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.applicationlayer.LocalStoreService;
import com.sweatshop.storycal.domainlayer.Album.Album;
import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.infrastructurelayer.localStore.Repositories.UserRepositoryImpl;
import com.sweatshop.storycal.presentationlayer.LogoutViewModel;
import com.sweatshop.storycal.presentationlayer.home.MainActivity;
import com.sweatshop.storycal.presentationlayer.homepage.HomepageActivity;
import com.sweatshop.storycal.presentationlayer.main_month.MainMonthActivity;

import java.util.LinkedList;
import java.util.List;

public class MainYearActivity extends AppCompatActivity {
    private RecyclerView albumView;
    private List<Album> albums;
    private User user;
    private TextView textView;

    private static final int REQUEST_PERMISSIONS = 100;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_year);
        setUpActionBar();
        setRecyclerView();
        LocalStoreService localStoreService = new LocalStoreService();
        this.user = localStoreService.getUserFromLocalStore();
        textView = (TextView) this.findViewById(R.id.profileName_lbl);
        textView.setText(this.user.getName());

        addImage();
    }

    private void addImage() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if (!((ActivityCompat.shouldShowRequestPermissionRationale(MainYearActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(MainYearActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)))) {
                ActivityCompat.requestPermissions(MainYearActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        } else {

            final String[] columns = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID };
            final String orderBy = MediaStore.Images.Media._ID;
            Cursor imagecursor = getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                    null, orderBy);
            int image_column_index = imagecursor.getColumnIndex(MediaStore.Images.Media._ID);
            this.size = imagecursor.getCount();

            imagecursor.moveToPosition(this.size - 1);
            int id = imagecursor.getInt(image_column_index);
            Bitmap thumbnail = MediaStore.Images.Thumbnails.getThumbnail(
                    getApplicationContext().getContentResolver(), id,
                    MediaStore.Images.Thumbnails.MICRO_KIND, null
            );

            ImageView profPic = (ImageView) findViewById(R.id.profileImage_Img);
            profPic.setImageBitmap(thumbnail);

            imagecursor.close();
        }
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
                addPhoto();
            }
        });

        ImageButton rightActionBtn= (ImageButton)view.findViewById(R.id.action_right);
        rightActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_LONG).show();
            }
        });

        ImageView logo = (ImageView)view.findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LogoutViewModel().logout(v.getContext());
            }
        });
    }

    private void setRecyclerView() {
        albumView = (RecyclerView) findViewById(R.id.albumList);
        albums = new LinkedList<>();

        UserRepositoryImpl repository = new UserRepositoryImpl();
        albums = repository.getAlbums(getIntent().getExtras().getLong("album_category_id"));

//        albums.add(new Album(0,0, "August"));
//        albums.add(new Album(0,0, "August"));
//        albums.add(new Album(0,0, "August"));
//        albums.add(new Album(0,0, "August"));
//        albums.add(new Album(0,0, "August"));
//        albums.add(new Album(0,0, "August"));
//        albums.add(new Album(0,0, "August"));
//        albums.add(new Album(0,0, "August"));
//        albums.add(new Album(0,0, "August"));
//        albums.add(new Album(0,0, "August"));
//        albums.add(new Album(0,0, "August"));
//        albums.add(new Album(0,0, "August"));
//        albums.add(new Album(0,0, "August"));
//        albums.add(new Album(0,0, "August"));
//        albums.add(new Album(0,0, "August"));

        if(albums != null) {
            albumView.setAdapter(new AlbumRecyclerAdapter(this, albums));

            GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);

            albumView.setLayoutManager(manager);
            albumView.setVerticalScrollBarEnabled(true);
        }

    }

    public void goToFeed(View view) {
        Intent intent = new Intent(MainYearActivity.this, HomepageActivity.class);
        startActivity(intent);
    }

    public void expandMonth(View view) {
        Intent intent = new Intent(MainYearActivity.this, MainMonthActivity.class);
        startActivity(intent);
    }

    public void backToYear(View view) {
        Intent intent = new Intent(MainYearActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void addPhoto() {}


    @Override
    public void onBackPressed() {
        this.finish();
    }
}

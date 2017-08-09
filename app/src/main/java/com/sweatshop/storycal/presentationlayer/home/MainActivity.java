package com.sweatshop.storycal.presentationlayer.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.applicationlayer.UserServiceImpl;
import com.sweatshop.storycal.databinding.ActivityMainBinding;
import com.sweatshop.storycal.domainlayer.Album.AlbumCategory;
import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.infrastructurelayer.localStore.Repositories.PostRepositoryImpl;
import com.sweatshop.storycal.infrastructurelayer.localStore.Repositories.UserRepositoryImpl;
import com.sweatshop.storycal.presentationlayer.LogoutViewModel;
import com.sweatshop.storycal.presentationlayer.gallery.GalleryActivity;
import com.sweatshop.storycal.presentationlayer.homepage.HomepageActivity;
import com.sweatshop.storycal.presentationlayer.import_photo_gallery.ImportPhotoGalleryActivity;
import com.sweatshop.storycal.presentationlayer.import_photo_gallery.ImportPhotoGalleryViewModel;
import com.sweatshop.storycal.presentationlayer.main_year.MainYearActivity;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView albumCategoryView;
    private GridLayoutManager gridLayoutManager;
    private List<AlbumCategory> albumCategories;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMain(mainViewModel = new MainViewModel(this, new User(),new UserServiceImpl(new UserRepositoryImpl(), new PostRepositoryImpl())));

        setUpActionBar();
        setRecyclerView();
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

        view.setBackgroundColor(0x00FFFF00 );
    }

    private void setRecyclerView() {
        albumCategoryView = (RecyclerView) findViewById(R.id.albumCategory);
        albumCategories = new LinkedList<>();

        UserRepositoryImpl repository = new UserRepositoryImpl();
        
        if(mainViewModel.GetUser() != null) {
            albumCategories = repository.findAlbumCategory(mainViewModel.GetUser().getId());
        }
//        albumCategories.add(new AlbumCategory(0, 0, "2017"));
//        albumCategories.add(new AlbumCategory(0, 0, "2016"));
//        albumCategories.add(new AlbumCategory(0, 0, "2015"));
//        albumCategories.add(new AlbumCategory(0, 0, "2014"));
//        albumCategories.add(new AlbumCategory(0, 0, "2013"));
//        albumCategories.add(new AlbumCategory(0, 0, "2012"));
//        albumCategories.add(new AlbumCategory(0, 0, "2011"));
//        albumCategories.add(new AlbumCategory(0, 0, "2010"));

        albumCategoryView.setAdapter(new AlbumCategoryRecyclerAdapter(this, albumCategories));

        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        albumCategoryView.setLayoutManager(manager);
        albumCategoryView.setVerticalScrollBarEnabled(true);
    }


    public void goToFeed(View view) {
        Intent intent = new Intent(MainActivity.this, HomepageActivity.class);
        startActivity(intent);
    }

    public void expandYear(View view) {
        Intent intent = new Intent(MainActivity.this, MainYearActivity.class);
        startActivity(intent);
    }

    public void openHome(View view) {
        Intent intent = new Intent(MainActivity.this, HomepageActivity.class);
        startActivity(intent);


    }

    public void tempAlbumGallery(View view){
        Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
        startActivity(intent);

    }

    public void tempImportGallery(View view){
        Intent intent = new Intent(MainActivity.this, ImportPhotoGalleryViewModel.class);
        startActivity(intent);

    }

    public void addPhoto() {
        Intent intent = new Intent(MainActivity.this, ImportPhotoGalleryActivity.class);
        startActivity(intent);
    }

    public void toFaceDetection(View view) {
//        Intent intent = new Intent(MainActivity.this, FaceDetectionActivity.class);
//        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

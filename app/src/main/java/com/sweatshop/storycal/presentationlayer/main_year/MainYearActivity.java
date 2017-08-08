package com.sweatshop.storycal.presentationlayer.main_year;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.applicationlayer.LocalStoreService;
import com.sweatshop.storycal.domainlayer.Album.Album;
import com.sweatshop.storycal.domainlayer.User.User;
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
    }

    private void setRecyclerView() {
        albumView = (RecyclerView) findViewById(R.id.albumList);
        albums = new LinkedList<>();

        albums.add(new Album(0,0, "August"));
        albums.add(new Album(0,0, "August"));
        albums.add(new Album(0,0, "August"));
        albums.add(new Album(0,0, "August"));
        albums.add(new Album(0,0, "August"));
        albums.add(new Album(0,0, "August"));
        albums.add(new Album(0,0, "August"));
        albums.add(new Album(0,0, "August"));
        albums.add(new Album(0,0, "August"));
        albums.add(new Album(0,0, "August"));
        albums.add(new Album(0,0, "August"));
        albums.add(new Album(0,0, "August"));
        albums.add(new Album(0,0, "August"));
        albums.add(new Album(0,0, "August"));
        albums.add(new Album(0,0, "August"));


        albumView.setAdapter(new AlbumRecyclerAdapter(this, albums));

        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);

        albumView.setLayoutManager(manager);
        albumView.setVerticalScrollBarEnabled(true);

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
    }

    public void addPhoto() {}
}

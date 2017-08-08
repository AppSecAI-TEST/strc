package com.sweatshop.storycal.presentationlayer.main_month;

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
import android.widget.TextView;
import android.widget.Toast;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.applicationlayer.LocalStoreService;
import com.sweatshop.storycal.applicationlayer.UserServiceImpl;
import com.sweatshop.storycal.databinding.ActivityMainMonthBinding;
import com.sweatshop.storycal.domainlayer.Album.AlbumCategory;
import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.infrastructurelayer.localStore.Repositories.PostRepositoryImpl;
import com.sweatshop.storycal.infrastructurelayer.localStore.Repositories.UserRepositoryImpl;
import com.sweatshop.storycal.presentationlayer.LogoutViewModel;
import com.sweatshop.storycal.presentationlayer.homepage.HomepageActivity;
import com.sweatshop.storycal.presentationlayer.main_year.MainYearActivity;

import java.util.List;

public class MainMonthActivity extends AppCompatActivity {
    private RecyclerView albumCategoryView;
    private GridLayoutManager gridLayoutManager;
    private List<AlbumCategory> albumCategories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_month);

        ActivityMainMonthBinding activityMainMonthBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_month);
        activityMainMonthBinding.setMain(new MainMonthViewModel(this, new UserRepositoryImpl().get(0), new UserServiceImpl(new UserRepositoryImpl(), new PostRepositoryImpl())));
        setUpActionBar();
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

    public void goToFeed(View view) {
        Intent intent = new Intent(MainMonthActivity.this, HomepageActivity.class);
        startActivity(intent);
    }

    public void backToMonth(View view) {
        Intent intent = new Intent(MainMonthActivity.this, MainYearActivity.class);
        startActivity(intent);
    }

    public void addPhoto() {}
}

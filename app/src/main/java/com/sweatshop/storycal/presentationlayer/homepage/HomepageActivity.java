package com.sweatshop.storycal.presentationlayer.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.domainlayer.Post.Post;
import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.presentationlayer.create_post.CreatePostActivity;
import com.sweatshop.storycal.presentationlayer.home.MainActivity;
import com.sweatshop.storycal.presentationlayer.posts.PostAdapter;

import java.util.ArrayList;

public class HomepageActivity extends AppCompatActivity {

    ArrayList<Post> postArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        postArrayList = new ArrayList<>();
        Post p = new Post(0, "hahaha");
        p.setUser(new User("Jonathan Smith", "@fido", "password123", "jonathan.smith@gmail.com"));
        postArrayList.add(p);
        p = new Post(0, "hahaha");
        p.setUser(new User("Jonathan Smith", "@fido", "password123", "jonathan.smith@gmail.com"));
        postArrayList.add(p);
        p = new Post(0, "hahaha");
        p.setUser(new User("Jonathan Smith", "@fido", "password123", "jonathan.smith@gmail.com"));
        postArrayList.add(p);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view_posts);
        PostAdapter postAdapter = new PostAdapter(postArrayList);

        rv.setAdapter(postAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        setUpActionBar();
    }

    private void setUpActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_homepage);
        View view =getSupportActionBar().getCustomView();

        ImageButton leftActionBtn= (ImageButton)view.findViewById(R.id.action_left);
        leftActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();
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

    public void createPost(View view) {
        Intent intent = new Intent(HomepageActivity.this, CreatePostActivity.class);
        startActivity(intent);
    }

    private void goToProfile() {
        Intent intent = new Intent(HomepageActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

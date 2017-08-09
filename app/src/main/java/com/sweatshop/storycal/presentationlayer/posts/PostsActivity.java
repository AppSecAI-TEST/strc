package com.sweatshop.storycal.presentationlayer.posts;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.databinding.ActivityPostsBinding;
import com.sweatshop.storycal.domainlayer.Post.Post;
import com.sweatshop.storycal.domainlayer.User.User;

import java.util.ArrayList;

public class PostsActivity extends AppCompatActivity {

    private ArrayList<Post> postArrayList;
    private ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPostsBinding activityPostsBinding = DataBindingUtil.setContentView(this, R.layout.activity_posts);
//
//        Post p = new Post(0, "hahaha");
//        p.setUser(new User("Jonathan Smith", "@fido", "password123", "jonathan.smith@gmail.com"));
//        postArrayList.add(p);
//
//        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view_posts);
//        PostAdapter postAdapter = new PostAdapter(postArrayList);
//
//        rv.setAdapter(postAdapter);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        rv.setLayoutManager(llm);
//
//        activityPostsBinding.setPosts(new PostsViewModel(this));
    }


    @Override
    public void onBackPressed() {
        this.finish();
    }
}

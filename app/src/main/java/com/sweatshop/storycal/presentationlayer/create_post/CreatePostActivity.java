package com.sweatshop.storycal.presentationlayer.create_post;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.presentationlayer.homepage.HomepageActivity;

public class CreatePostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        setUpActionBar();
    }

    private void setUpActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_createpost);
        View view =getSupportActionBar().getCustomView();

        TextView leftActionBtn = (TextView)view.findViewById(R.id.action_left);
        leftActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFeed();
            }
        });

        TextView rightActionBtn = (TextView)view.findViewById(R.id.action_right);
        rightActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Posted",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void goToFeed() {
        Intent intent = new Intent(CreatePostActivity.this, HomepageActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}

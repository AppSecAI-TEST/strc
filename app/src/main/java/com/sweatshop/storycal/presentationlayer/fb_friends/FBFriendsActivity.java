package com.sweatshop.storycal.presentationlayer.fb_friends;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.databinding.ActivityFbFriendsBinding;
import com.sweatshop.storycal.presentationlayer.profile_picture.ProfilePictureActivity;

public class FBFriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpActionBar();
        ActivityFbFriendsBinding activityFbFriendsBinding = DataBindingUtil.setContentView(this, R.layout.activity_fb_friends);
        activityFbFriendsBinding.setFBFriendsViewModel(new FBFriendsViewModel(this));
    }

    private void setUpActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_signup);
        View view =getSupportActionBar().getCustomView();

        TextView leftActionBtn= (TextView)view.findViewById(R.id.action_left_signup);
        leftActionBtn.setTextColor(Color.WHITE);
        leftActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        TextView rightActionBtn= (TextView)view.findViewById(R.id.action_right_signup);
        rightActionBtn.setText(R.string.skip_action_bar);
        rightActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToContactFriends();
            }
        });
    }


    public void goToContactFriends() {
        Intent intent = new Intent(FBFriendsActivity.this, ProfilePictureActivity.class);
        startActivity(intent);
    }

    public void cancel() { }


    @Override
    public void onBackPressed() {
        this.finish();
    }

}

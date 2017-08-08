package com.sweatshop.storycal.presentationlayer.profile_picture;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.databinding.ActivityProfilePictureBinding;
import com.sweatshop.storycal.presentationlayer.home.MainActivity;
import com.sweatshop.storycal.presentationlayer.profile_picture.popup.PopupDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfilePictureActivity extends AppCompatActivity {
    ImageView camera;
    PopupDialog.Builder singleBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpActionBar();
        ActivityProfilePictureBinding activityProfilePictureBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile_picture);

        ButterKnife.bind(this);

        activityProfilePictureBinding.setProfilePictureViewModel(new ProfilePictureViewModel(this));
    }

    private void setUpActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_contact_friends);
        View view =getSupportActionBar().getCustomView();

        TextView leftActionBtn= (TextView)view.findViewById(R.id.action_left_signup);
        leftActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        TextView rightActionBtn= (TextView)view.findViewById(R.id.action_right_signup);
        rightActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToContactFriends();
            }
        });
    }


    public void goToContactFriends() {
        Intent intent = new Intent(ProfilePictureActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void cancel() { }


    @Override
    protected void onPause() {
        super.onPause();
        if (singleBuilder != null)
            singleBuilder.close();
    }

    @OnClick(R.id.profilePicBtn)
    public void simpleClicked() {
        singleBuilder = new PopupDialog.Builder(this);
        singleBuilder.display();
    }

}

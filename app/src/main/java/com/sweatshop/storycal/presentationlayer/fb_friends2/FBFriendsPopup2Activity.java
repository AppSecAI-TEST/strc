package com.sweatshop.storycal.presentationlayer.fb_friends2;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.databinding.ActivityFbFriendsPopup2Binding;
import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.presentationlayer.fb_friends.FBFriendsActivity;
import com.sweatshop.storycal.presentationlayer.home.MainActivity;

import java.util.ArrayList;

public class FBFriendsPopup2Activity extends AppCompatActivity {
    private ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpActionBar();
        ActivityFbFriendsPopup2Binding activityFbFriendsPopup2Binding = DataBindingUtil.setContentView(this, R.layout.activity_fb_friends_popup2);

        userArrayList = new ArrayList<>();
        userArrayList.add(new User("Alvin Yacat", "alvinyacat", "password123", "alvin.yacat@gmail.com"));
        userArrayList.add(new User("Jeffrey Hamilton", "jeffreyham", "password123", "alvin.yacat@gmail.com"));
        userArrayList.add(new User("Wei Ling", "weiling", "password123", "alvin.yacat@gmail.com"));
        userArrayList.add(new User("Kirsten Stock", "kirsten", "password123", "alvin.yacat@gmail.com"));
        userArrayList.add(new User("Veronica Jones", "vivijones", "password123", "alvin.yacat@gmail.com"));

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
        UserAdapter userAdapter = new UserAdapter(userArrayList);

        rv.setAdapter(userAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        activityFbFriendsPopup2Binding.setFBFriendsPopup2ViewModel(new FBFriendsPopup2ViewModel(this));
    }

    private void setUpActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_signup);
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
        Intent intent = new Intent(FBFriendsPopup2Activity.this, MainActivity.class);
        startActivity(intent);
    }

    public void cancel() {
        Intent intent = new Intent(FBFriendsPopup2Activity.this, FBFriendsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

}

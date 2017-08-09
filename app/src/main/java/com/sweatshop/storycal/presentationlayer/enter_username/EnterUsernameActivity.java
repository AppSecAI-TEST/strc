package com.sweatshop.storycal.presentationlayer.enter_username;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.applicationlayer.UserService;
import com.sweatshop.storycal.applicationlayer.UserServiceImpl;
import com.sweatshop.storycal.databinding.ActivityEnterUsernameBinding;
import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.infrastructurelayer.localStore.Repositories.PostRepositoryImpl;
import com.sweatshop.storycal.infrastructurelayer.localStore.Repositories.UserRepositoryImpl;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseAdapter.LocalDbAdapter;
import com.sweatshop.storycal.presentationlayer.enter_email.EnterEmailActivity;
import com.sweatshop.storycal.presentationlayer.enter_info.EnterInfoActivity;

public class EnterUsernameActivity extends AppCompatActivity {
    private EnterUsernameViewModel enterUsernameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEnterUsernameBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_enter_username);
        LocalDbAdapter.init(this);
        User user = new User();
        UserService service = new UserServiceImpl(new UserRepositoryImpl(), new PostRepositoryImpl());
        loginBinding.setReg(enterUsernameViewModel = new EnterUsernameViewModel(this, this.getIntent(), service));
        setUpActionBar();
    }

    private void setUpActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_main);
        View view =getSupportActionBar().getCustomView();

        ImageButton leftActionBtn= (ImageButton)view.findViewById(R.id.action_left);
        leftActionBtn.setBackgroundResource(R.drawable.icon_back);
        leftActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
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

    public void back(){
        Intent intent = new Intent(EnterUsernameActivity.this, EnterInfoActivity.class);
        intent.putExtra("email", getIntent().getExtras().getString("email"));
        intent.putExtra("name", getIntent().getExtras().getString("name"));
        intent.putExtra("password", getIntent().getExtras().getString("password"));
        intent.putExtra("username", enterUsernameViewModel.getUsername());
        startActivity(intent);
        finish();
    }

}

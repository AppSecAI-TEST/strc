package com.sweatshop.storycal.presentationlayer.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.applicationlayer.UserService;
import com.sweatshop.storycal.applicationlayer.UserServiceImpl;
import com.sweatshop.storycal.databinding.ActivityLoginBinding;
import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.infrastructurelayer.localStore.Repositories.PostRepositoryImpl;
import com.sweatshop.storycal.infrastructurelayer.localStore.Repositories.UserRepositoryImpl;
import com.sweatshop.storycal.infrastructurelayer.localStore.localDb.databaseAdapter.LocalDbAdapter;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LocalDbAdapter.init(this);
        User user = new User();
        UserService service = new UserServiceImpl(new UserRepositoryImpl(), new PostRepositoryImpl());
        loginBinding.setLoginViewModel(new LoginViewModel(user, this, service));
        LocalDbAdapter.init(this);
    }

//    @Override
//    public void onBackPressed() {
//        moveTaskToBack(true);
//    }
}

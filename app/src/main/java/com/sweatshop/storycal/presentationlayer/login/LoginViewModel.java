package com.sweatshop.storycal.presentationlayer.login;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.sweatshop.storycal.applicationlayer.LocalStoreService;
import com.sweatshop.storycal.applicationlayer.UserService;
import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.presentationlayer.fb_popup.FBPopupActivity;
import com.sweatshop.storycal.presentationlayer.home.MainActivity;
import com.sweatshop.storycal.presentationlayer.register.RegisterActivity;

/**
 * Created by kevin on 25/07/2017.
 */

public class LoginViewModel extends BaseObservable {
    private User user;
    private Context context;
    private UserService userService;
    private String username;
    private String password;

    public LoginViewModel(User user, Context context, UserService userService) {
        this.user = user;
        this.context = context;
        this.userService = userService;
    }

    @Bindable
    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);

    }

    @Bindable
    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public void login() {
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = userService.login(user);
        if(user == null) {
            Log.e("User login: ", "user does not exist");
            Toast.makeText(context, "Incorrect username or password", Toast.LENGTH_LONG).show();
        }else {
            Log.i("User login: ", "user exists");
            LocalStoreService localStoreService = new LocalStoreService();
            localStoreService.addLoginUserToLocalStore(user);
            Intent intent = new Intent(context, MainActivity.class);
            Log.e("lol", "" + user.toString());
            intent.putExtra("login_user_id", Long.toString(user.getId()));
            context.startActivity(intent);
        }
    }

    public void signup() { context.startActivity(new Intent(context, RegisterActivity.class)); }

    public void logInFacebook() { context.startActivity(new Intent(context, FBPopupActivity.class)); }
}

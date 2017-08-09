package com.sweatshop.storycal.presentationlayer.enter_username;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.widget.Toast;

import com.sweatshop.storycal.BR;
import com.sweatshop.storycal.applicationlayer.UserService;
import com.sweatshop.storycal.domainlayer.User.User;
import com.sweatshop.storycal.presentationlayer.login.LoginActivity;

/**
 * Created by arTeam on 28/07/2017.
 */

public class EnterUsernameViewModel extends BaseObservable {
    private String username = "";
    private Context context;
    private Intent intent;
    private UserService userService;

    public EnterUsernameViewModel(Context context, Intent intent, UserService userService) {
        this.context = context;
        this.intent = intent;
        this.userService = userService;
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            username = bundle.getString("username");
        }
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    public void next() {
        if(!username.equals("")) {
            if (userService.getUserByUsername(username) == null) {
                Bundle bundle = intent.getExtras();
                userService.register(new User(
                        bundle.getString("name"),
                        username,
                        bundle.getString("password"),
                        bundle.getString("email")
                ));
                context.startActivity(new Intent(context, LoginActivity.class));
            } else {
                Toast.makeText(context, "Username already exists", Toast.LENGTH_LONG).show();
            }
        }
    }
}

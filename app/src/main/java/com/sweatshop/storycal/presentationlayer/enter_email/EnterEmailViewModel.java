package com.sweatshop.storycal.presentationlayer.enter_email;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.widget.Toast;

import com.sweatshop.storycal.BR;
import com.sweatshop.storycal.applicationlayer.UserService;
import com.sweatshop.storycal.presentationlayer.enter_info.EnterInfoActivity;

/**
 * Created by arTeam on 28/07/2017.
 */

public class EnterEmailViewModel extends BaseObservable {
    private String email = "";
    private String name;
    private String password;
    private String username;
    private Context context;
    private UserService userService;
    private EnterEmailActivity enterEmailActivity;

    public EnterEmailViewModel( EnterEmailActivity enterEmailActivity, UserService userService) {
        this.context = context;
        this.userService = userService;
        this.enterEmailActivity = enterEmailActivity;
        Bundle bundle = this.enterEmailActivity.getIntent().getExtras();
        if(bundle != null) {
            email = bundle.getString("email");
            name = bundle.getString("name");
            password  = bundle.getString("password");
            username = bundle.getString("username");
        }
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    public void next() {
        if(!email.equals("")) {
            if (userService.getUserByEmail(email) == null) {
                Intent intent = new Intent(enterEmailActivity, EnterInfoActivity.class);
                intent.putExtra("email", email);
                if(name != null) {
                    intent.putExtra("name", name);
                }
                if(password != null) {
                    intent.putExtra("password", password);
                }
                if(username != null) {
                    intent.putExtra("username", username);
                }
                enterEmailActivity.startActivity(intent);
            } else {
                Toast.makeText(enterEmailActivity,"Email already exists", Toast.LENGTH_LONG).show();
            }
        }
    }
}

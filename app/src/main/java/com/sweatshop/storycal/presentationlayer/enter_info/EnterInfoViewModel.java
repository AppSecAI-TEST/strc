package com.sweatshop.storycal.presentationlayer.enter_info;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;

import com.sweatshop.storycal.BR;
import com.sweatshop.storycal.presentationlayer.enter_username.EnterUsernameActivity;

/**
 * Created by arTeam on 07/08/2017.
 */

public class EnterInfoViewModel extends BaseObservable {
    private String email = "";
    private String name = "";
    private String password = "";
    private String username = "";
    private Context context;
    private  EnterInfoActivity enterInfoActivity;

    public EnterInfoViewModel(EnterInfoActivity enterInfoActivity , Intent intent) {
        final Bundle bundle = intent.getExtras();
        email = bundle.getString("email");
        String tempName = bundle.getString("name");
        String tempPassword = bundle.getString("password");
        String tempUsername = bundle.getString("username");
        name = tempName != null ? tempName : "";
        password = tempPassword != null ? tempPassword : "";
        username = tempUsername != null ? tempUsername : "";
        this.enterInfoActivity = enterInfoActivity;
    }

    public String getUsername() { return username; }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public void next() {
        if(!name.equals("") && !password.equals("")) {
            Intent intent = new Intent(enterInfoActivity, EnterUsernameActivity.class);
            intent.putExtra("email", email);
            intent.putExtra("name", name);
            intent.putExtra("password", password);
            intent.putExtra("username", username);
            enterInfoActivity.startActivity(intent);

        }
    }
}

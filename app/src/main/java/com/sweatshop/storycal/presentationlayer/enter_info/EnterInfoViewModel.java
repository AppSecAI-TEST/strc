package com.sweatshop.storycal.presentationlayer.enter_info;

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
    private final Context context;

    public EnterInfoViewModel(Context context, Intent intent) {
        this.context = context;
        final Bundle bundle = intent.getExtras();
        email = bundle.getString("email");
    }

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
            Intent intent = new Intent(context, EnterUsernameActivity.class);
            intent.putExtra("email", email);
            intent.putExtra("name", name);
            intent.putExtra("password", password);
            context.startActivity(intent);
        }
    }
}

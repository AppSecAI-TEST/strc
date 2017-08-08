package com.sweatshop.storycal.presentationlayer.enter_email;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.Toast;

import com.sweatshop.storycal.BR;
import com.sweatshop.storycal.applicationlayer.UserService;
import com.sweatshop.storycal.presentationlayer.enter_info.EnterInfoActivity;

/**
 * Created by arTeam on 28/07/2017.
 */

public class EnterEmailViewModel extends BaseObservable {
    private String email = "";
    private Context context;
    private UserService userService;

    public EnterEmailViewModel( Context context, UserService userService) {
        this.context = context;
        this.userService = userService;
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
                Intent intent = new Intent(context, EnterInfoActivity.class);
                intent.putExtra("email", email);
                context.startActivity(intent);
            } else {
                Toast.makeText(context,"Email already exists", Toast.LENGTH_LONG).show();
            }
        }
    }
}

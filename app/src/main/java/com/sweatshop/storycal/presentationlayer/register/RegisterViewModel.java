package com.sweatshop.storycal.presentationlayer.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;

import com.sweatshop.storycal.presentationlayer.enter_email.EnterEmailActivity;
import com.sweatshop.storycal.presentationlayer.fb_popup.FBPopupActivity;
import com.sweatshop.storycal.presentationlayer.login.LoginActivity;

/**
 * Created by kevin on 25/07/2017.
 */

public class RegisterViewModel extends BaseObservable {
    private Context context;

    public RegisterViewModel(Context context) {
        this.context = context;
    }

    public void register() {
        context.startActivity(new Intent(context, EnterEmailActivity.class));
        ((Activity) context).finish();
    }

    public void logInWithFacebook() {
        context.startActivity(new Intent(context, FBPopupActivity.class));
        ((Activity) context).finish();
    }

    public void signIn() {
        context.startActivity(new Intent(context, LoginActivity.class));
        ((Activity) context).finish();
    }
}

package com.sweatshop.storycal.presentationlayer.fb_popup;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;

import com.sweatshop.storycal.presentationlayer.fb_friends2.FBFriendsPopup2Activity;
import com.sweatshop.storycal.presentationlayer.login.LoginActivity;


/**
 * Created by arTeam on 28/07/2017.
 */

public class FBPopupViewModel extends BaseObservable {
    private Context context;

    public FBPopupViewModel(Context context) {
        this.context = context;
    }

    public void connectToFacebook() { context.startActivity(new Intent(context, FBFriendsPopup2Activity.class)); }

    public void notNow() { context.startActivity(new Intent(context, LoginActivity.class)); }
}

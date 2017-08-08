package com.sweatshop.storycal.presentationlayer.fb_friends;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;

import com.sweatshop.storycal.presentationlayer.fb_popup.FBPopupActivity;

/**
 * Created by arTeam on 28/07/2017.
 */

public class FBFriendsViewModel extends BaseObservable {
    private Context context;

    public FBFriendsViewModel(Context context) {
        this.context = context;
    }

    public void connectToFacebook() { context.startActivity(new Intent(context, FBPopupActivity.class)); }
}

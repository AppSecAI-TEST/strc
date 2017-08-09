package com.sweatshop.storycal.presentationlayer.profile_picture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;

import com.sweatshop.storycal.presentationlayer.login.LoginActivity;

/**
 * Created by arTeam on 02/08/2017.
 */

public class ProfilePictureViewModel extends BaseObservable {
    private Context context;

    public ProfilePictureViewModel(Context context) {
        this.context = context;
    }

    public void takePhoto() {
        context.startActivity(new Intent(context, LoginActivity.class));
        ((Activity) context).finish();
    }

    public void importFromGallery() {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public void importFromWeb() { context.startActivity(new Intent(context, LoginActivity.class)); }
}

package com.sweatshop.storycal.presentationlayer.contact_friends;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;

import com.sweatshop.storycal.presentationlayer.enter_info.EnterInfoActivity;

/**
 * Created by arTeam on 28/07/2017.
 */

public class ContactFriendsViewModel extends BaseObservable {
    private Context context;

    public ContactFriendsViewModel(Context context) {
        this.context = context;
    }

    public void connectToFacebook() { context.startActivity(new Intent(context, EnterInfoActivity.class)); }
}

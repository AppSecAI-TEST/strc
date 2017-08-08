package com.sweatshop.storycal.presentationlayer;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.sweatshop.storycal.applicationlayer.LocalStoreService;
import com.sweatshop.storycal.presentationlayer.login.LoginActivity;

/**
 * Created by kevin on 08/08/2017.
 */

public class LogoutViewModel {

    public void logout(Context context) {
        LocalStoreService localStoreService = new LocalStoreService();
        localStoreService.RemoveUserFromLocalStore();
        if(localStoreService.getUserFromLocalStore() == null)
            Toast.makeText(context, "logout", Toast.LENGTH_LONG).show();
         context.startActivity(new Intent(context, LoginActivity.class));
    }
}

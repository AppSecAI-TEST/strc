package com.sweatshop.storycal.presentationlayer.main_month;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.widget.Toast;

import com.sweatshop.storycal.BR;
import com.sweatshop.storycal.applicationlayer.LocalStoreService;
import com.sweatshop.storycal.applicationlayer.UserService;
import com.sweatshop.storycal.domainlayer.User.User;

/**
 * Created by arTeam on 08/08/2017.
 */

public class MainMonthViewModel extends BaseObservable {
    private User user;
    private MainMonthActivity mainMonthActivity;
    private UserService userService;

    public MainMonthViewModel(MainMonthActivity mainMonthActivity, User user, UserService userService) {
        this.mainMonthActivity = mainMonthActivity;
        this.user = user;
        this.userService = userService;
        LocalStoreService localStoreService = new LocalStoreService();
        Bundle bundle = mainMonthActivity.getIntent().getExtras();
        if(bundle != null) {
            this.user = localStoreService.getUserFromLocalStore(Long.parseLong(bundle.getString("login_user_id")));
            Toast.makeText(mainMonthActivity,"User: " + user.getUsername(),Toast.LENGTH_LONG).show();
        }
    }

    @Bindable
    public String getName() {
        return user.getName();
    }

    public void setName(String name) {
        this.user.setName(name);
        notifyPropertyChanged(BR.name);
    }


    public void setNumStories(String numStories) {
        this.user.setNumOfStories(Integer.parseInt(numStories));
        notifyPropertyChanged(BR.numStories);
    }

    @Bindable
    public String getNumStories() {
        return Integer.toString(user.getNumOfStories());
    }
}

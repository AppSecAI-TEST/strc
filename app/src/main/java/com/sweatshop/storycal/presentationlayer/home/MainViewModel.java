package com.sweatshop.storycal.presentationlayer.home;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;

import com.sweatshop.storycal.BR;
import com.sweatshop.storycal.applicationlayer.LocalStoreService;
import com.sweatshop.storycal.applicationlayer.UserService;
import com.sweatshop.storycal.domainlayer.User.User;

/**
 * Created by arTeam on 05/08/2017.
 */

public class MainViewModel extends BaseObservable {
    private User user;
    private MainActivity mainActivity;
    private UserService userService;

    public MainViewModel(MainActivity mainActivity, User user, UserService userService) {
        this.mainActivity = mainActivity;
        this.user = user;
        this.userService = userService;;
        LocalStoreService localStoreService = new LocalStoreService();
        this.userService = userService;;
        this.user = localStoreService.getUserFromLocalStore();
    }

    public User GetUser() { return user; }

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

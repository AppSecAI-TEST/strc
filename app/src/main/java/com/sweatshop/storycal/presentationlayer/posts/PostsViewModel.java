package com.sweatshop.storycal.presentationlayer.posts;

import android.content.Context;
import android.databinding.BaseObservable;

/**
 * Created by arTeam on 31/07/2017.
 */

public class PostsViewModel extends BaseObservable {
    private Context context;

    public PostsViewModel(Context context) {
        this.context = context;
    }
}

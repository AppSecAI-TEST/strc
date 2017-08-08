package com.sweatshop.storycal.presentationlayer.import_profile_pic;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;

import com.sweatshop.storycal.BR;

/**
 * Created by arTeam on 04/08/2017.
 */

public class ImportProfilePictureViewModel extends BaseObservable {
    private Context context;
    private Bitmap bitmap;

    public ImportProfilePictureViewModel(Context context) {
        this.context = context;
    }

    @Bindable
    public Bitmap getBitmap() { return this.bitmap; }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        notifyPropertyChanged(BR.bitmap);
    }

}

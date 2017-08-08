package com.sweatshop.storycal.presentationlayer.import_photo_camera;


import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.sweatshop.storycal.domainlayer.Picture.Picture;
import com.sweatshop.storycal.presentationlayer.import_photo_gallery.ImportPhotoGalleryActivity;

/**
 * Created by arTeam on 03/08/2017.
 */

public class ImportPhotoCameraViewModel extends BaseObservable {
    private Context context;
    private Picture picture;

    public ImportPhotoCameraViewModel(Context context) {
        this.context = context;
    }

    @Bindable
    public Picture getPicture() { return this.picture; }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public void toGallery() { context.startActivity(new Intent(context, ImportPhotoGalleryActivity.class)); }
}

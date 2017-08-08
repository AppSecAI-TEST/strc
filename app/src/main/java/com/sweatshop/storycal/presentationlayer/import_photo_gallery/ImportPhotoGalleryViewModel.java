package com.sweatshop.storycal.presentationlayer.import_photo_gallery;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;

import com.sweatshop.storycal.presentationlayer.import_photo_camera.ImportPhotoCameraActivity;

/**
 * Created by arTeam on 03/08/2017.
 */

public class ImportPhotoGalleryViewModel extends BaseObservable {
    private Context context;

    public ImportPhotoGalleryViewModel(Context context) {
        this.context = context;
    }

    public void toCamera() { context.startActivity(new Intent(context, ImportPhotoCameraActivity.class)); }
}
